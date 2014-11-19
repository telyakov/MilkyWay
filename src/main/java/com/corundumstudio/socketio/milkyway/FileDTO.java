package com.corundumstudio.socketio.milkyway;

public class FileDTO {
    private String data;
    private int id;
    private String error;
    private String key;

    public FileDTO() {
    }

    public FileDTO(int id, String data, String error, String key) {
        super();
        this.id = id;
        this.data = data;
        this.error = error;
        this.key = key;
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

    public String getData() {

        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
