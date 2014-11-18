package com.corundumstudio.socketio.milkyway.connection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface Connection {
    public LinkedHashMap<String, HashMap<String,String>> Exec(String sql, String key) throws ConnectionException;
}
