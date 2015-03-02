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
import java.util.ArrayList;
import java.util.List;

public class DocBuilder {

    public static final String TEST_6543210 = "6543210";
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
            int flatImageID = 223740;



            String flatID = docSettings.get("flatID");

            if (flatID.length()==0)
            {
                throw  new ExcelBuilderException("Ключ помещение пустой. FlatID=''.");
            }


            String sql = "select isnull(o.ParentID,o.id) as Result from directory.vFlats f " +
                          "inner join directory.Objects o on o.id = f.ObjectParentID where f.id ="  + flatID;

            String mainObjectID = this.connection.ExecScalar(TEST_6543210, sql, "9");

            if (mainObjectID.length()==0)
            {
                throw  new ExcelBuilderException("Для попещения с ключом='" + flatID + "', не найден ключ базового объекта.");
            }


            String templateID = this.connection.ExecScalar(TEST_6543210, "select directory.ParameterValueGet (22,123," + mainObjectID + ")", "9");
            //String templateID ="266756";


            String sqlForTemplateFileID = "select ea.FilesID from dbo.EntityAttachments ea where ea.EntityTypeID=6 and ea.EntityId=" + templateID;
            String templateFileID = this.connection.ExecScalar(TEST_6543210,sqlForTemplateFileID,"9");

            byte[] buf = this.connection.FileGet("6543210", Integer.valueOf(templateFileID));


            ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
            WordprocessingMLPackage template = WordprocessingMLPackage.load(byteStream);

            addImage(template, "Квартира_Планировка", this.connection.FileGet(TEST_6543210, flatImageID));
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            template.getMainDocumentPart().addParagraphOfText("100% оплата/ипотека - 1 650 120 р. 2 этаж.");

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


                    tags.get(0).setValue(name);

                    tags.clear();
                    break;

                } else {
                    tag = "";
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


    public void addImage(WordprocessingMLPackage wordMLPackage, String tagName, byte[] bytes) throws Exception {
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

            documentPart.getContent().remove(parent);
            documentPart.getContent().add(index, paragraph);

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
