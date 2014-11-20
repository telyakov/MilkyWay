package com.corundumstudio.socketio.milkyway.connection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface Connection {
    public LinkedHashMap<String, HashMap<String,String>> Exec(String sql, String key) throws ConnectionException;
    public byte[] FileGet(int id, String key) throws ConnectionException;
    public HashMap<String, String> getRow(LinkedHashMap<String, HashMap<String,String>> recordset, int index);
}
