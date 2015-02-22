package test.milkyway;

import junit.framework.TestCase;
import milkyway.connection.WebServiceAccessor;
import milkyway.excel.DocBuilder;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Text;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.List;

public class DocBuilderTest extends TestCase {


    @Test
    public void test_Tag_Replacing() throws Docx4JException, FileNotFoundException {

        WordprocessingMLPackage template = WordprocessingMLPackage.createPackage();

        MainDocumentPart document = template.getMainDocumentPart();

        //Add test tags.
        document.addParagraphOfText("[a]");
        document.addParagraphOfText("[b]");
        document.addParagraphOfText("[Договор,  #Цена_м2_Общая_проектная, СуммаПрописьюБухФорматЛСТ#]");

        DocBuilder docBuilder = new DocBuilder();

        //Replace tags.
        docBuilder.replacePlaceholder(template, "[a]", "1");
        docBuilder.replacePlaceholder(template, "[b]", "2");
        docBuilder.replacePlaceholder(template, "[Договор,  #Цена_м2_Общая_проектная, СуммаПрописьюБухФорматЛСТ#]", "Сто рублей, 00 копеек.");

        //Check results.
        List<Object> texts = docBuilder.getAllElementFromObject(document, Text.class);
        assertEquals("После замены трех тегов, ожидалось три текстовых элемента.",3,texts.size());
        assertEquals("1", ((Text) texts.get(0)).getValue());
        assertEquals("2",((Text)texts.get(1)).getValue());
        assertEquals("Сто рублей, 00 копеек.",((Text)texts.get(2)).getValue());

        //Save to file for manual check.
        template.save(new java.io.File("helloworld.docx"));
    }

    @Test
    public void test_Template_To_Result_For_Manual_Check() throws Exception {

        DocBuilder docBuilder = new DocBuilder();

        WordprocessingMLPackage template = docBuilder.getTemplate("template.docx");
        docBuilder.replacePlaceholder(template, "[a_tag]", "Текст вместо a_tag");
        docBuilder.replacePlaceholder(template, "[b_tag]", "Текст вместо b_tag");
        docBuilder.replacePlaceholder(template, "[c_tag]", "Текст вместо c_tag");
        docBuilder.replacePlaceholder(template, "[Договор,#Сумма#]", "Текст вместо Договор,#Сумма#");
        docBuilder.replacePlaceholder(template, "[ВсеПлановыеПлатежи]", "График платежей");

        //225323 - Рекламная планировка квартиры
        //223740 - Планировка с осями

        WebServiceAccessor webServiceAccessor = new WebServiceAccessor();
        docBuilder.addImageToPackage(template, webServiceAccessor.FileGet("test6543210", 225323));
        template.getMainDocumentPart().addParagraphOfText("100% оплата/ипотека - 5 650 120 р. 3 этаж.");
        docBuilder.addImageToPackage(template, webServiceAccessor.FileGet("test6543210", 223740));
        template.getMainDocumentPart().addParagraphOfText("100% оплата/ипотека - 1 650 120 р. 2 этаж.");

        //Save to file for manual check.
        template.save(new java.io.File("result.docx"));
    }








}
