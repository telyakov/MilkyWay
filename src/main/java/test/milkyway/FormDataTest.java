package test.milkyway;

import junit.framework.TestCase;
import milkyway.excel.FormData;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class FormDataTest extends TestCase {
    @Test
    public void testFormData()throws Exception{
        String json = "{\"17211\":{\"id\":\"17211\",\"key\":\"chocolate-control-column\",\"width\":\"24\"},\"9870\":{\"id\":\"9870\",\"key\":\"numattachments\",\"width\":\"120\"}}";
        FormData data = new FormData(json);
        HashMap<String, String> row = data.getRow("9870");
        Assert.assertEquals(row.get("key"), "numattachments");
    }
}
