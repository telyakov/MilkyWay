package test.com.corundumstudio.socketio.milkyway;

import com.corundumstudio.socketio.milkyway.WebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

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
    String oneRowSelect = "select 1 as id";
    HashMap<String, HashMap<String,String>> result = WebService.Exec(oneRowSelect);

    Gson gson = new GsonBuilder().serializeNulls().create();
    String json = gson.toJson(result);
    Assert.assertEquals("{\"1\":{\"id\":\"1\"}}", json);
}
    @Test
    public void testExecTwoRow() throws Exception {
        String twoRowSelect = "Select 33 as ID, 1 as Name UNION SELECT 44, 2";
        HashMap<String, HashMap<String,String>> result = WebService.Exec(twoRowSelect);

        Gson gson = new GsonBuilder().serializeNulls().create();
        String json = gson.toJson(result);
        Assert.assertEquals("{\"44\":{\"id\":\"44\",\"name\":\"2\"},\"33\":{\"id\":\"33\",\"name\":\"1\"}}", json);
    }


}
