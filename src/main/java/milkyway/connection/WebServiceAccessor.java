package milkyway.connection;

import milkyway.exceptions.ConnectionException;
import milkyway.soap.ArrayOfString;
import milkyway.soap.Directory;
import milkyway.soap.DirectorySoap;
import milkyway.soap.FileModel;

import java.util.*;

public class WebServiceAccessor implements Connection {
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
    public LinkedHashMap<String, HashMap<String, String>> Exec(String key, String sql, Boolean isCache) throws ConnectionException {
        try {
            ArrayOfString data;
            if(isCache){
                data = this.getConn().exec2Immutable(key, sql, "", "", "", "", "");
            }else{
                data = this.getConn().exec2(key, sql, "", "", "", "", "");
            }
            return parse(data);
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage(), e);
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
    public byte[] FileGet(String key, int id) throws ConnectionException {
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

    public boolean ExecMultiply(String key, String[] sqlList) throws ConnectionException {
        try {
            ArrayOfString array = new ArrayOfString();
            for (String aSqlList : sqlList) {
                array.getString().add(aSqlList);
            }
            return this.getConn().execMultiply(key, array);
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public String ExecScalar(String key, String sql, String userID) throws ConnectionException{
        try {
            return this.getConn().execScalar(key, sql, userID);
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    public void AttachmentIns(String key, String sql, byte[] data) throws ConnectionException{
        try {
            this.getConn().attachmentIns(key, sql, data);
        } catch (Exception e) {
            throw new ConnectionException(e.getMessage());
        }
    }
    private static String getID(HashMap<String, String> row) {
        String id;
        if (row.containsKey(WebServiceAccessor.ID_COLUMN)) {
            id = row.get(WebServiceAccessor.ID_COLUMN);
        } else {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    private static LinkedHashMap<String, HashMap<String, String>> parse(ArrayOfString data) {

        LinkedHashMap<String, HashMap<String, String>> result = new LinkedHashMap<String, HashMap<String, String>>();

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

        HashMap<String, String> row = new HashMap<String, String>(columnsCount);
        for (String value : list) {
            index = key % columnsCount;
            if (index == 0 && !row.isEmpty()) {
                result.put(WebServiceAccessor.getID(row), row);
                row = new HashMap<String, String>(columnsCount);
            }
            if (value.equals(WebServiceAccessor.NULL_MARK)) {
                value = null;
            } else if (value.equals(WebServiceAccessor.TRUE_MARK)) {
                value = "true";
            } else if (value.equals(WebServiceAccessor.FALSE_MARK)) {
                value = "false";
            }
            row.put(columns[index], value);
            key++;
        }
        if (!row.isEmpty()) {
            result.put(WebServiceAccessor.getID(row), row);
        }
        return result;
    }
}
