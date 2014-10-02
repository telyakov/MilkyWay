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
public void testExec() throws Exception {
    String incorrectSql = "select 1 as id";
    HashMap<String, HashMap<String,String>> result = WebService.Exec(incorrectSql);

    Gson gson = new GsonBuilder().serializeNulls().create();
    String json = gson.toJson(result);
    Assert.assertEquals("{\"1\":{\"id\":\"1\"}}", json);
}



} 
