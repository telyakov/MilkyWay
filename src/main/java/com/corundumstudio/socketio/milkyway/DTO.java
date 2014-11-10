package com.corundumstudio.socketio.milkyway;

public class DTO {

    private String data;
    private String id;
    private String type;
    private String query;
    private String error;
    private String key;

    public DTO() {
    }

    public DTO(String id, String type, String query, String data, String error, String key) {
        super();
        this.id = id;
        this.type = type;
        this.data = data;
        this.query = query;
        this.error = error;
        this.key = key;
    }

    private String convertToUTF8(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8" );
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
        this.query = this.convertToUTF8(query);
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
