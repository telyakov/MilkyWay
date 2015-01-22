package milkyway.excel;

public class ColumnSettings {
    private String key;
    private Integer width;
    private Integer weight;
    private String caption;

    public ColumnSettings(String key, Integer width, Integer weight, String caption) {
        this.key = key;
        this.width = width;
        this.weight = weight;
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public String getKey() {
        return key;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getWeight() {
        return weight;
    }

}
