package com.corundumstudio.socketio.milkyway;

public class TransportObject {

    private String data;
    private String id;
    private String type;
    private String query;
    private String error;

    public TransportObject() {
    }

    public TransportObject(String id , String type, String query, String data, String error) {
        super();
        this.id = id;
        this.type = type;
        this.data = data;
        this.query = query;
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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
