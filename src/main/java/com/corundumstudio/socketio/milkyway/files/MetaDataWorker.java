package com.corundumstudio.socketio.milkyway.files;

import com.corundumstudio.socketio.milkyway.FileDTO;
import com.corundumstudio.socketio.milkyway.connection.Connection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class MetaDataWorker  implements Runnable{
    private FileDTO dto;
    private Connection conn;
    private FileDTO request;

    public MetaDataWorker(FileDTO dto, Connection conn, FileDTO request) {
        this.dto = dto;
        this.conn = conn;
        this.request = request;

    }

    @Override
    public void run() {
        try {
            String sql = "Attachments.MetaDataGet " + this.request.getId();
            LinkedHashMap<String,HashMap<String,String>> result = conn.Exec(sql, this.request.getKey());
            Iterator<HashMap<String,String>> iterator = result.values().iterator();
            HashMap<String, String> row =conn.getRow(result, 0);
            this.dto.setName(row.get("name"));
            this.dto.setId(request.getId());
        }
        catch (Exception e) {
            this.dto.setError(e.getMessage());
        }
    }
}
