package com.corundumstudio.socketio.milkyway;

public class TransportObject {

    private String userName;
    private String message;

    public TransportObject() {
    }

    public TransportObject(String userName, String message) {
        super();
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
