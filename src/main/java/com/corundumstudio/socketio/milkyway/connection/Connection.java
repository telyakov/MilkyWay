package com.corundumstudio.socketio.milkyway.connection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface Connection {
    public LinkedHashMap<String, HashMap<String,String>> Exec(String key, String sql, Boolean isCache) throws ConnectionException;
    public byte[] FileGet(String key, int id) throws ConnectionException;
    public HashMap<String, String> getRow(LinkedHashMap<String, HashMap<String,String>> recordset, int index);
    public boolean ExecMultiply(String key, String[] sqlList) throws ConnectionException;
    public void AttachmentIns(String key, String sql, byte[] data) throws ConnectionException;
}
