package test.com.corundumstudio.socketio.milkyway.connection;

import com.corundumstudio.socketio.milkyway.connection.WebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class WebServiceTest {
    final String testKey = "test6543210";

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testExecOneRow() throws Exception {
        WebService conn = new WebService();
        String oneRowSelect = "select 'привет world!' as id";
        LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(testKey, oneRowSelect, false);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        Assert.assertEquals("{\"привет world!\":{\"id\":\"привет world!\"}}", json);
    }

    @Test
    public void testExecTwoRow() throws Exception {
        WebService conn = new WebService();
        String twoRowSelect = "Select 33 as ID, 'шоколад' as Name UNION SELECT 44, 'kis'";
        LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(testKey, twoRowSelect, false);
        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        Assert.assertEquals("{\"33\":{\"id\":\"33\",\"name\":\"шоколад\"},\"44\":{\"id\":\"44\",\"name\":\"kis\"}}", json);
    }

    @Test
    public void testGetRow() throws Exception {
        WebService webService = new WebService();
        String sql = "Select 'test' as name";
        LinkedHashMap<String, HashMap<String, String>> result = webService.Exec(testKey, sql, false);
        HashMap<String, String> firstRow = webService.getRow(result, 0);
        HashMap<String, String> secondRow = webService.getRow(result, 1);
        Assert.assertEquals(firstRow.get("name"), "test");
        Assert.assertNull(secondRow);
    }

    @Test
    public void testMultiplyExec() throws Exception {
        WebService webService = new WebService();
        String[] sqlList = new String[]{"select 1", "select 2"};
        Boolean success = webService.ExecMultiply(testKey,sqlList);
        Assert.assertTrue(success);
    }

    @Test
    public void testFileGet() throws Exception {
        try {
            WebService webService = new WebService();
            webService.FileGet(testKey, 255091);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testHandleException() throws Exception {
        try {
            WebService webService = new WebService();
            webService.Exec(testKey, "select 1/0", false);
            Assert.assertTrue(false);
        } catch (Exception e) {
            Assert.assertTrue(e.getMessage().contains("Divide by zero error encountered"));
        }
    }
}
