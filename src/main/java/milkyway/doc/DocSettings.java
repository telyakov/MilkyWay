package milkyway.doc;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DocSettings {

    private HashMap<String, String> map;

    public DocSettings(String json) {
        this.parse(json);
    }

    private void parse(String json) {

        HashMap<String, String> map = new HashMap<String, String>();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().getAsString();
            map.put(key, value);
        }
        this.map = map;
    }

    public String get(String key)
    {
        return map.get(key);
    }

}
