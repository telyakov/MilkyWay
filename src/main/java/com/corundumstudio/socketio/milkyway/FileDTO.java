package com.corundumstudio.socketio.milkyway;

public class FileDTO {
    private byte[] data;
    private int id;
    private String error;
    private String key;
    private String name;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FileDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileDTO(int id, byte[] data, String error, String key, String name) {
        super();
        this.id = id;
        this.data = data;
        this.error = error;
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public byte[] getData() {

        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
