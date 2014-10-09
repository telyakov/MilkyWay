package com.corundumstudio.socketio.milkyway;

import com.corundumstudio.socketio.milkyway.soap.ArrayOfString;
import com.corundumstudio.socketio.milkyway.soap.Directory;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class WebService {
    final static public String KEY = "test6543210";
    final static public String ID_COLUMN = "id";
    final static public String NULL_MARK = "NULL";
    final static public String TRUE_MARK = "True";
    final static public String FALSE_MARK = "False";

    public static HashMap<String, HashMap<String,String>> Exec(String sql) throws WebServiceException {
        try{
            ArrayOfString data = new Directory().getDirectorySoap().exec2(WebService.KEY, sql, "", "", "", "", "");
            return WebService.parse(data);
        }catch (Exception e){
            throw new WebServiceException(e.getMessage());
        }
    }

    private static String getID(HashMap<String,String> row){
        String id;
        if(row.containsKey(WebService.ID_COLUMN)){
            id = row.get(WebService.ID_COLUMN);
        }else{
            id =  UUID.randomUUID().toString();
        }
        return id;
    }
    private static HashMap<String, HashMap<String,String>>  parse(ArrayOfString data){
        List<String> list = data.getString();
        int columnsCount = Integer.parseInt(list.remove(0));
        String[] columns = new String[columnsCount];
        list.remove(0);
        int metaCount = Integer.parseInt(list.remove(0));
        int metaLength = columnsCount * metaCount;
        int i =0;
        int columnIndex = 0;
        while (i < metaLength){
            if(i % metaCount == 0){
                columns[columnIndex] = list.get(0);
                columnIndex++;
            }
            list.remove(0);
            i++;
        }
        int key = 0;
        int index;
        HashMap<String, HashMap<String,String>> result =new HashMap<String, HashMap<String,String>>();
        HashMap<String,String> row = new HashMap<String, String>(columnsCount);
        for(String value : list){
            index = key % columnsCount;
            if(index ==0 && !row.isEmpty()){
                result.put(WebService.getID(row), row);
                row = new HashMap<String, String>(columnsCount);
            }
            if(value.equals(WebService.NULL_MARK)){
                value = null;
            }else if(value.equals(WebService.TRUE_MARK)){
                value = "true";
            }else if(value.equals(WebService.FALSE_MARK)){
                value = "false";
            }
            row.put(columns[index], value);
            key++;
        }
        if(!row.isEmpty()){
            result.put(WebService.getID(row), row);
        }
        return result;
    }
}
