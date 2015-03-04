package milkyway.dto;

public class DocDTO {
    private String id;
    private String data;
    private String settings;
    private String type;
    private String name;
    private String error;

    public DocDTO() {

    }

    public DocDTO(String id, String data, String settings, String type, String error, String name) {
        this.id = id;
        this.data = data;
        this.settings = settings;
        this.type = type;
        this.name = name;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
