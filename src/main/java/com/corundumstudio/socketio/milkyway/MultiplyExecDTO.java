package com.corundumstudio.socketio.milkyway;


public class MultiplyExecDTO {

    private String data;
    private String id;
    private String type;
    private String[] sqlList;
    private String error;
    private String key;

    public MultiplyExecDTO() {
    }

    public MultiplyExecDTO(String id, String type, String[] sqlList, String data, String error, String key) {
        super();
        this.id = id;
        this.type = type;
        this.data = data;
        this.sqlList = sqlList;
        this.error = error;
        this.key = key;
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

    public String[] getSqlList() {
        return sqlList;
    }

    public void setSqlList(String[] sqlList) {
        String[] prepareData = new String[sqlList.length];
        for(int i = 0; i< sqlList.length; i++){
            prepareData[i] = DTO.convertToUTF8(sqlList[i]);
        }
        this.sqlList = prepareData;
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
