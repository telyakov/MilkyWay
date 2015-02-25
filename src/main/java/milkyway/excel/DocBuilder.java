package milkyway.excel;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import milkyway.connection.WebServiceAccessor;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DocBuilder {

    public byte[] make(FormData data, Settings settings) throws ExcelBuilderException {

        byte[] result = null;
        try {
            //TODO Передать TemplateID или ObjectID - для получения TemplateID.
            //TODO Передать FlatIDList по каждой квартире получить планировку, цену и этаж.
            //TODO Удалить все не подставленные теги.

            //Шаблон по Гатчине в формате docx
            int templateID = 256207;

            int flatImageID = 223740;

            String fileName = "kp.docx";
            WebServiceAccessor webServiceAccessor = new WebServiceAccessor();
            Path file = Paths.get(fileName);
            byte[] buf = webServiceAccessor.FileGet("test6543210", templateID);
            Files.write(file, buf);

            DocBuilder docBuilder = new DocBuilder();
            WordprocessingMLPackage template = docBuilder.getTemplate(fileName);

            docBuilder.addImageToPackage(template,"Квартира_Планировка", webServiceAccessor.FileGet("test6543210", flatImageID));

            template.save(new java.io.File("kp_result.docx"));

            Path resultPath = Paths.get("kp_result.docx");
            result = Files.readAllBytes(resultPath);


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
        WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(name)));
        return template;
    }

    /**
     *  Docx4j contains a utility method to create an image part from an array of
     *  bytes and then adds it to the given package. In order to be able to add this
     *  image to a paragraph, we have to convert it into an inline object. For this
     *  there is also a method, which takes a filename hint, an alt-text, two ids
     *  and an indication on whether it should be embedded or linked to.
     *  One id is for the drawing object non-visual properties of the document, and
     *  the second id is for the non visual drawing properties of the picture itself.
     *  Finally we add this inline object to the paragraph and the paragraph to the
     *  main document of the package.
     *
     *  @param wordMLPackage The package we want to add the image to
     *  @param bytes         The bytes of the image
     *  @throws Exception    Sadly the createImageInline method throws an Exception
     *                       (and not a more specific exception type)
     */
    public void addImageToPackage(WordprocessingMLPackage wordMLPackage, byte[] bytes) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;
        Inline inline = imagePart.createImageInline("Filename hint", "Alternative text", docPrId, cNvPrId, false);

        P paragraph = addInlineImageToParagraph(inline);

        wordMLPackage.getMainDocumentPart().addObject(paragraph);
    }

    public void addImageToPackage(WordprocessingMLPackage wordMLPackage, String tagName, byte[] bytes) throws Exception {
        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        int docPrId = 1;
        int cNvPrId = 2;
        Inline inline = imagePart.createImageInline("Filename hint", "Alternative text", docPrId, cNvPrId, false);

        P paragraph = addInlineImageToParagraph(inline);


        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();


        String xpath = "//w:r[w:t[contains(text(),'" + tagName  + "')]]";

        List<Object> lst = documentPart.getJAXBNodesViaXPath(xpath, false);

        if(lst.size()>0)
        {
            org.docx4j.wml.R r = (org.docx4j.wml.R) lst.get(0);

            org.docx4j.wml.P parent = (org.docx4j.wml.P) r.getParent();

            int index = documentPart.getContent().indexOf(parent);

            documentPart.getContent().remove(parent);
            documentPart.getContent().add(index, paragraph);

        }



    }

    /**
     *  We create an object factory and use it to create a paragraph and a run.
     *  Then we add the run to the paragraph. Next we create a drawing and
     *  add it to the run. Finally we add the inline object to the drawing and
     *  return the paragraph.
     *
     * @param   inline The inline object containing the image.
     * @return  the paragraph containing the image
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
