package milkyway.excel;

public class ColumnSettings {
    private String key;
    private Integer width;
    private Integer weight;
    public ColumnSettings(String key, Integer width, Integer weight) {
        this.key = key;
        this.width = width;
        this.weight = weight;
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
