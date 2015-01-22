package test.milkyway;

import junit.framework.Assert;
import junit.framework.TestCase;
import milkyway.excel.ExcelBuilder;
import milkyway.excel.FormData;
import milkyway.excel.Settings;
import org.junit.Test;

public class ExcelBuilderTest extends TestCase {
    @Test
    public void testMakeExcel()throws Exception{
        String settingsJson = "{\"chocolate-control-column\":{\"weight\":\"0\",\"key\":\"chocolate-control-column\",\"width\":\"24\"},\"numattachments\":{\"weight\":\"2\",\"key\":\"numattachments\",\"width\":\"120\"}}";
        Settings settings = new Settings(settingsJson);
        String objectJson = "{\"17211\":{\"id\":\"17211\",\"chocolate-control-column\":\"тест1\",\"numattachments\":\"24\"},\"9870\":{\"id\":\"9870\",\"chocolate-control-column\":\"выфвыф\",\"numattachments\":\"120\"}}";
        FormData data = new FormData(objectJson);
        ExcelBuilder excelBuilder  = new ExcelBuilder();
        byte[] buffer =excelBuilder.makeExcel(data, settings);
        Assert.assertEquals(buffer.length, 13824);
    }
}
