package test.milkyway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import milkyway.connection.WebServiceAccessor;
import milkyway.exceptions.ConnectionException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class WebServiceAccessorTest {
    final static String testKey = "test6543210";
    private WebServiceAccessor webServiceAccessor = null;

    @Before
    public void before() throws Exception {
        this.webServiceAccessor = new WebServiceAccessor();
    }

    @After
    public void after() throws Exception {
        this.webServiceAccessor = null;
    }

    @Test
    public void testExecOneRow() throws Exception {
        WebServiceAccessor conn = new WebServiceAccessor();
        String oneRowSelect = "select 'привет world!' as id";
        LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(testKey, oneRowSelect, false);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        Assert.assertEquals("{\"привет world!\":{\"id\":\"привет world!\"}}", json);
    }

    @Test
    public void testExecTwoRow() throws Exception {
        WebServiceAccessor conn = new WebServiceAccessor();
        String twoRowSelect = "Select 33 as ID, 'шоколад' as Name UNION SELECT 44, 'kis'";
        LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(testKey, twoRowSelect, false);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        Assert.assertEquals("{\"33\":{\"id\":\"33\",\"name\":\"шоколад\"},\"44\":{\"id\":\"44\",\"name\":\"kis\"}}", json);
    }
    @Test
    public void testJsonParse() throws Exception {
        WebServiceAccessor conn = new WebServiceAccessor();
        String twoRowSelect = "Select 33 as ID, 'шоколад' as Name UNION SELECT 44, 'kis'";
        LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(testKey, twoRowSelect, false);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        JsonObject s1 = object.get("44").getAsJsonObject();
        Integer id = s1.get("id").getAsInt();
        String name = s1.get("name").getAsString();
        Assert.assertEquals(name, "kis");
        Assert.assertEquals(id.toString(), "44");
    }

    @Test
    public void testGetRow() throws Exception {
        WebServiceAccessor webServiceAccessor = new WebServiceAccessor();
        String sql = "Select 'test' as name";
        LinkedHashMap<String, HashMap<String, String>> result = webServiceAccessor.Exec(testKey, sql, false);
        HashMap<String, String> firstRow = webServiceAccessor.getRow(result, 0);
        HashMap<String, String> secondRow = webServiceAccessor.getRow(result, 1);
        Assert.assertEquals(firstRow.get("name"), "test");
        Assert.assertNull(secondRow);
    }

    @Test
    public void testMultiplyExec() throws Exception {
        WebServiceAccessor webServiceAccessor = new WebServiceAccessor();
        String[] sqlList = new String[]{"select 1", "select 2"};
        Boolean success = webServiceAccessor.ExecMultiply(testKey,sqlList);
        Assert.assertTrue(success);
    }

    @Test
    public void testFileGet() throws Exception {
        try {
            WebServiceAccessor webServiceAccessor = new WebServiceAccessor();
            webServiceAccessor.FileGet(testKey, 255091);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testHandleException() throws Exception {
        try {
            WebServiceAccessor webServiceAccessor = new WebServiceAccessor();
            webServiceAccessor.Exec(testKey, "select 1/0", false);
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("Divide by zero error encountered"));
        }
    }

    @Test
    public void testExecScalar() throws ConnectionException {
        Assert.assertEquals("3",this.webServiceAccessor.ExecScalar(testKey,"select 1 + 2","1"));
    }
}
