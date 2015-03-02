package test.milkyway;

import junit.framework.TestCase;
import milkyway.doc.DocSettings;
import org.junit.Test;

public class DocSettingsTest extends TestCase {

    @Test
    public void testDocSettings()throws Exception{
        String json ="{\"flatID\":12321412,\"templateID\":98}";
        DocSettings docSettings = new DocSettings(json);
        assertEquals("12321412",docSettings.get("flatID"));
        assertEquals("98",docSettings.get("templateID"));
    }
}
