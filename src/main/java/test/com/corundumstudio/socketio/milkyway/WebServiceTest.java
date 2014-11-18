package test.com.corundumstudio.socketio.milkyway;

import com.corundumstudio.socketio.milkyway.connection.Connection;
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
    Connection conn = new WebService();
    String oneRowSelect = "select 1 as id";
    LinkedHashMap<String, HashMap<String,String>> result = conn.Exec(oneRowSelect, "test6543210");

    Gson gson = new GsonBuilder().serializeNulls().create();
    String json = gson.toJson(result);
    Assert.assertEquals("{\"1\":{\"id\":\"1\"}}", json);
}
    @Test
    public void testExecTwoRow() throws Exception {
        Connection conn = new WebService();
        String twoRowSelect = "Select 33 as ID, 1 as Name UNION SELECT 44, 2";
        LinkedHashMap<String, HashMap<String,String>> result = conn.Exec(twoRowSelect, "test6543210");

        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        Assert.assertEquals("{\"33\":{\"id\":\"33\",\"name\":\"1\"},\"44\":{\"id\":\"44\",\"name\":\"2\"}}", json);
    }



}
