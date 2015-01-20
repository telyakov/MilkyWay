package test.com.corundumstudio.socketio.milkyway.connection;

import com.corundumstudio.socketio.milkyway.connection.WebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class WebServiceTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: Exec(String sql) 
* 
*/ 
@Test
public void testExecOneRow() throws Exception {
    WebService conn = new WebService();
    String oneRowSelect = "select 1 as id";
    LinkedHashMap<String, HashMap<String,String>> result = conn.Exec(oneRowSelect, "test6543210");

    Gson gson = new GsonBuilder().serializeNulls().create();
    String json = gson.toJson(result);
    Assert.assertEquals("{\"1\":{\"id\":\"1\"}}", json);
}
    @Test
    public void testExecTwoRow() throws Exception {
        WebService conn = new WebService();


        String twoRowSelect = "Select 33 as ID, 1 as Name UNION SELECT 44, 2";
        LinkedHashMap<String, HashMap<String,String>> result = conn.Exec(twoRowSelect, "test6543210");

        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        Assert.assertEquals("{\"33\":{\"id\":\"33\",\"name\":\"1\"},\"44\":{\"id\":\"44\",\"name\":\"2\"}}", json);
    }
    @Test
    public void testGetRow() throws Exception {
        WebService webService = new WebService();
        String sql = "Select 'test' as name";
        LinkedHashMap<String,HashMap<String,String>> result = webService.Exec(sql, "test6543210");
        HashMap<String, String> firstRow = webService.getRow(result, 0);
        HashMap<String, String> secondRow = webService.getRow(result, 1);
        Assert.assertEquals(firstRow.get("name"), "test");
        Assert.assertNull(secondRow);

    }
    @Test
    public void testMultiplyExec() throws Exception {
        WebService webService = new WebService();
        String [] sqlList = new String[]{"select 1", "select 2"};
        Boolean success = webService.ExecMultiply(sqlList, "test6543210");
        Assert.assertTrue(success);

    }
    @Test
    public void testFileGet() throws Exception {
        try {
        WebService webService = new WebService();
//        String [] sqlList = new String[]{"select 1", "select 2"};
       webService.FileGet(255091, "test6543210");
        Assert.assertTrue(true);
        }catch (Exception e){
        Assert.assertTrue(false);
        }
    }
}
