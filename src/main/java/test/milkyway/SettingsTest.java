package test.milkyway;

import junit.framework.TestCase;
import milkyway.excel.ColumnSettings;
import milkyway.excel.Settings;
import org.junit.Assert;
import org.junit.Test;

public class SettingsTest extends TestCase {

    @Test
    public void testSettings()throws Exception{
        String json = "{\"chocolate-control-column\":{\"weight\":\"0\",\"caption\":\"cap1\",\"key\":\"chocolate-control-column\",\"width\":\"24\"},\"numattachments\":{\"weight\":\"2\",\"caption\":\"cap2\", \"key\":\"numattachments\",\"width\":\"120\"}}";
        Settings settings = new Settings(json);
        ColumnSettings columnSettings = settings.getColumnSettings("numattachments");
        Assert.assertNotNull(columnSettings);
        Assert.assertEquals("numattachments", columnSettings.getKey());
        Assert.assertEquals(columnSettings.getWeight().toString(), "2");
        Assert.assertEquals(columnSettings.getWidth().toString(), "120");
    }
}
