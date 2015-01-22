package milkyway.excel;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class Settings {
    private HashMap<String, ColumnSettings> map;

    public Settings(String json) {
        this.parse(json);
    }

    private void parse(String json) {
        HashMap<String, ColumnSettings> map = new HashMap<String, ColumnSettings>();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonObject jsonSetting = entry.getValue().getAsJsonObject();
            String key = jsonSetting.get("key").getAsString();
            Integer width = jsonSetting.get("width").getAsInt();
            Integer weight = jsonSetting.get("weight").getAsInt();
            ColumnSettings columnSettings = new ColumnSettings(key, width, weight);
            map.put(key, columnSettings);
        };
        this.map = map;
    }

    public HashMap<String, ColumnSettings> getMap() {
        return map;
    }

    public ColumnSettings getColumnSettings(String key) {
        return this.getMap().get(key);
    }
}
