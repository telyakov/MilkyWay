package com.corundumstudio.socketio.milkyway.connection;

import com.corundumstudio.socketio.milkyway.soap.*;

import java.util.*;

public class WebService implements Connection {
    final static public String ID_COLUMN = "id";
    final static public String NULL_MARK = "NULL";
    final static public String TRUE_MARK = "True";
    final static public String FALSE_MARK = "False";
    private DirectorySoap _conn = null;

    private DirectorySoap getConn() {
        if (this._conn == null) {
            this._conn = new Directory().getDirectorySoap();
        }
        return this._conn;
    }

    @Override
    public LinkedHashMap<String, HashMap<String, String>> Exec(String sql, String key) throws ConnectionException {
        try {
            ArrayOfString data = this.getConn().exec2(key, sql, "", "", "", "", "");
            return WebService.parse(data);
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    @Override
    public HashMap<String, String> getRow(LinkedHashMap<String, HashMap<String, String>> recordset, int index) {
        Iterator<HashMap<String, String>> iterator = recordset.values().iterator();
        HashMap<String, String> current;
        int counter = 0;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (counter == index) {
                return current;
            }
            counter += 1;
        }
        return null;
    }

    @Override
    public byte[] FileGet(int id, String key) throws ConnectionException {
        try {
            List<FileModel> list = this.getConn().fileGet(key, id).getFileModel();
            if (list.isEmpty()) {
                return null;
            } else {
                return list.get(0).getFileData();
            }

        } catch (Exception e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public boolean ExecMultiply(String[] sqlList, String key) throws ConnectionException {
        try {
            ArrayOfString array = new ArrayOfString();
            for (int i = 0; i < sqlList.length; i++) {
                array.getString().add(sqlList[i]);
            }
            return this.getConn().execMultiply(key, array);
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    private static String getID(HashMap<String, String> row) {
        String id;
        if (row.containsKey(WebService.ID_COLUMN)) {
            id = row.get(WebService.ID_COLUMN);
        } else {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    private static LinkedHashMap<String, HashMap<String, String>> parse(ArrayOfString data) {
        List<String> list = data.getString();
        int columnsCount = Integer.parseInt(list.remove(0));
        String[] columns = new String[columnsCount];
        list.remove(0);
        int metaCount = Integer.parseInt(list.remove(0));
        int metaLength = columnsCount * metaCount;
        int i = 0;
        int columnIndex = 0;
        while (i < metaLength) {
            if (i % metaCount == 0) {
                columns[columnIndex] = list.get(0);
                columnIndex++;
            }
            list.remove(0);
            i++;
        }
        int key = 0;
        int index;
        LinkedHashMap<String, HashMap<String, String>> result = new LinkedHashMap<String, HashMap<String, String>>();
        HashMap<String, String> row = new HashMap<String, String>(columnsCount);
        for (String value : list) {
            index = key % columnsCount;
            if (index == 0 && !row.isEmpty()) {
                result.put(WebService.getID(row), row);
                row = new HashMap<String, String>(columnsCount);
            }
            if (value.equals(WebService.NULL_MARK)) {
                value = null;
            } else if (value.equals(WebService.TRUE_MARK)) {
                value = "true";
            } else if (value.equals(WebService.FALSE_MARK)) {
                value = "false";
            }
            row.put(columns[index], value);
            key++;
        }
        if (!row.isEmpty()) {
            result.put(WebService.getID(row), row);
        }
        return result;
    }
}
