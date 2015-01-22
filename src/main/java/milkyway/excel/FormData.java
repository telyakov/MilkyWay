package milkyway.excel;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FormData {
    LinkedHashMap<String, HashMap<String, String>> map;
    public FormData(String json) {
        this.parse(json);
    }
    private void parse(String json){
        LinkedHashMap<String, HashMap<String, String>> map = new LinkedHashMap<String, HashMap<String, String>>();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            HashMap<String, String> row = new HashMap<String, String>();
            JsonObject rowJsonObject= entry.getValue().getAsJsonObject();
            for (Map.Entry<String, JsonElement> rowJson : rowJsonObject.entrySet()) {
                row.put(rowJson.getKey(), rowJson.getValue().getAsString());
            }
            map.put(key, row);
        };
        this.map = map;
    }

    public LinkedHashMap<String, HashMap<String, String>> getMap() {
        return map;
    }
    public HashMap<String, String> getRow(String key){
        return this.map.get(key);
    }
}
