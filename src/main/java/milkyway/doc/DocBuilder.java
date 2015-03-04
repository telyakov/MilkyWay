package milkyway.doc;

import milkyway.connection.Connection;
import milkyway.exceptions.ExcelBuilderException;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.Text;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

import javax.xml.bind.JAXBElement;
import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class DocBuilder {

    public static final String securityKey = "test6543210";
    Connection connection = null;


    public DocBuilder (Connection conn)
    {
        this.connection = conn;
    }

    public class DocBuilderResult {
        public byte[] getResult() {
            return Result;
        }

        public void setResult(byte[] result) {
            Result = result;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        private byte[] Result = null;
        private String Name = "";
    }


    public DocBuilderResult make(DocSettings docSettings) throws ExcelBuilderException {

        DocBuilderResult result = new DocBuilderResult();
        result.setName("document.docx");
        try {

            //TODO Получить планировку по FlatID
            //TODO Удалить все не подставленные теги.

            //Шаблон по Гатчине в формате docx
            //int templateID = 256207;


            //FlatID колпино 14127.
            //int flatImageID = 223740;

            String flatID = docSettings.get("flatID");

            if (flatID.length()==0)
            {
                throw  new ExcelBuilderException("Ключ помещение пустой. FlatID=''.");
            }

            String sql = "select isnull(o.ParentID,o.id) as MainObjectID from directory.vFlats f (nolock) " +
                          "inner join directory.Objects o (nolock) on o.id = f.ObjectParentID where f.id ="  + flatID;

            String mainObjectID = this.connection.ExecScalar(securityKey, sql, "9");

            if (mainObjectID.length()==0)
            {
                throw  new ExcelBuilderException("Для попещения с ключом='" + flatID + "', не найден базовый объект. Sql='" + sql + "'");
            }

            String sqlFormtemplateID = "select directory.ParameterValueGet (22,123," + mainObjectID + ")";
            String templateID = connection.ExecScalar(securityKey, sqlFormtemplateID, "9");

            if (templateID.length()==0)
            {
                throw new ExcelBuilderException("Для объекта с ключом='" + mainObjectID + "', не найден шаблон.sql='" + sqlFormtemplateID + "'");
            }

            String sqlForTemplateFileID = "select ea.FilesID from dbo.EntityAttachments ea (nolock) where ea.EntityTypeID=6 and ea.EntityId=" + templateID;
            String templateFileID = connection.ExecScalar(securityKey,sqlForTemplateFileID,"9");

            String sqlForFlatImage =
                        "SELECT ea.FilesID FROM dbo.EntityAttachments ea (nolock) INNER JOIN dbo.Files f (nolock) ON ea.FilesId= f.id WHERE ea.EntityTypeID = 4 AND ea.EntityID=" + flatID  + " AND f.FilesTypesId=11 AND ea.Removed IS null";

            String flatImageID = connection.ExecScalar(securityKey,sqlForFlatImage,"9");

            if(flatImageID.length()==0)
            {
                throw new ExcelBuilderException("Файл с планировкой не найден. Ключом квартиры='" + flatID + "'. sql='" + sqlForFlatImage + "'");
            }

            byte[] buf = connection.FileGet(securityKey, Integer.valueOf(templateFileID));

            ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
            WordprocessingMLPackage template = WordprocessingMLPackage.load(byteStream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


            String flatPrice = connection.ExecScalar(securityKey, "SELECT cast(v.CostCurrent AS decimal(14,2)) FROM directory.vFlats v WHERE v.ID = " + flatID, "9");
            if(flatPrice.length()>0)
            {
                String flatFloor  =  connection.ExecScalar(securityKey,"SELECT v.floor FROM directory.vFlats v WHERE v.ID = " + flatID, "9");
                flatPrice=flatPrice.replace(',','.');

                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                String flatDesc ="100% оплата/ипотека – " +  formatter.format((Float.valueOf(flatPrice))) + ", " + flatFloor + " этаж.";

                //[Квартира,  #Цена_100%# #Этаж# ]
                //[Квартира,  #Цена_Рассрочка#]

                replacePlaceholder(template, "[Квартира, #Цена_100%# #Этаж#]",flatDesc);

                String sqlForBaseCost = "SELECT UtilsDecimal.Format(NullIf(f.BaseCost, 0)) AS BaseCost FROM directory.vFlats AS f WHERE f.ID= " + flatID;

                String baseCost = connection.ExecScalar(securityKey,sqlForBaseCost, "9");

                String baseCostDesc = "";

                if (baseCost.length()>0)
                {
                    baseCostDesc = "Базовая цена – " +  baseCost;
                }

                replacePlaceholder(template, "[Квартира, #Цена_Рассрочка#]",baseCostDesc);
                addImage(template, "Картинка","[Квартира_Планировка, Картинка]",connection.FileGet(securityKey, Integer.valueOf(flatImageID)));


            }

            template.save(outputStream);
            result.setResult(outputStream.toByteArray());

        } catch (Exception e) {
            throw new ExcelBuilderException(e.getMessage(), e);
        }

        return result;
    }

    public void replacePlaceholder(WordprocessingMLPackage template, String placeholder, String name) {
        List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
        String tag = "";

        ArrayList<Text> tags = new ArrayList<Text>();

        for (Object text : texts) {
            Text textElement = (Text) text;

            if (textElement.getValue().equalsIgnoreCase(placeholder)) {
                textElement.setValue(name);



            } else if (textElement.getValue().startsWith("[")  ) {
                tag = textElement.getValue();
                tags.add(textElement);


            }   else if (textElement.getValue().endsWith("]")) {
                tags.add(textElement);

                tag += textElement.getValue();

                if (tag.equals(placeholder)) {

                    for (Text t : tags) {
                        t.setValue("");
                    }

                    tags.get(0).setValue(name);


                    tags.clear();
                    break;

                } else {
                    tag = "";
                    tags.clear();
                }

            } else if (tag.length() > 0) {
                tag += textElement.getValue();
                tags.add(textElement);
            }

        }
    }

    public Text getPlaceholder(WordprocessingMLPackage template, String placeholder, String name) {
        Text result = null;
        List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
        String tag = "";

        ArrayList<Text> tags = new ArrayList<Text>();

        for (Object text : texts) {
            Text textElement = (Text) text;

            if (textElement.getValue().equalsIgnoreCase(placeholder)) {
                textElement.setValue(name);
            } else if (textElement.getValue().equals("[")) {
                tag = "[";
                tags.add(textElement);

            } else if (textElement.getValue().equals("]")) {
                tags.add(textElement);

                tag += "]";

                if (tag.equals(placeholder)) {

                    for (Text t : tags) {
                        t.setValue("");
                    }


                    result = tags.get(0);
                    //.get(0).setValue(name);

                    // tags.clear();
                    break;

                } else {
                    tag = "";
                }

            } else if (tag.length() > 0) {
                tag += textElement.getValue();
                tags.add(textElement);
            }

        }

        return result;
    }

    public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement) obj = ((JAXBElement<?>) obj).getValue();

        if (obj.getClass().equals(toSearch))
            result.add(obj);
        else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }

        }
        return result;
    }

    public WordprocessingMLPackage getTemplate(String name) throws Docx4JException, FileNotFoundException {
        return WordprocessingMLPackage.load(new FileInputStream(new File(name)));
    }


    //TODO  Оставить вместо tagName и fullTagName один тег tagName.
    public void addImage(WordprocessingMLPackage wordMLPackage, String tagName,String fullTagName, byte[] bytes) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;
        Inline inline = imagePart.createImageInline("Filename hint", "Alternative text", docPrId, cNvPrId, false);

        P paragraph = addInlineImageToParagraph(inline);


        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();


        String xpath = "//w:r[w:t[contains(text(),'" + tagName + "')]]";

        List<Object> lst = documentPart.getJAXBNodesViaXPath(xpath, false);

        if (lst.size() > 0) {
            org.docx4j.wml.R r = (org.docx4j.wml.R) lst.get(0);

            org.docx4j.wml.P parent = (org.docx4j.wml.P) r.getParent();

            int index = documentPart.getContent().indexOf(parent);

            if(index != -1)
            {
                //documentPart.getContent().remove(parent);
                documentPart.getContent().add(index, paragraph);
                this.replacePlaceholder(wordMLPackage,fullTagName,"");
            }

        }


    }

    /**
     * We create an object factory and use it to create a paragraph and a run.
     * Then we add the run to the paragraph. Next we create a drawing and
     * add it to the run. Finally we add the inline object to the drawing and
     * return the paragraph.
     *
     * @param inline The inline object containing the image.
     * @return the paragraph containing the image
     */
    private P addInlineImageToParagraph(Inline inline) {
        // Now add the in-line image to a paragraph
        ObjectFactory factory = new ObjectFactory();
        P paragraph = factory.createP();
        R run = factory.createR();
        paragraph.getContent().add(run);
        Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);
        return paragraph;
    }

}
