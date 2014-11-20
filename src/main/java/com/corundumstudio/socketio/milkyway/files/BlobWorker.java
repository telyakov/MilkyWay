package com.corundumstudio.socketio.milkyway.files;

import com.corundumstudio.socketio.milkyway.FileDTO;
import com.corundumstudio.socketio.milkyway.connection.Connection;

public class BlobWorker implements Runnable {
    private FileDTO dto;
    private Connection conn;
    private FileDTO request;

    public BlobWorker(FileDTO dto, Connection conn, FileDTO request) {
        this.dto = dto;
        this.conn = conn;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            byte[] result = conn.FileGet(this.request.getId(), this.request.getKey());
            this.dto.setData(result);
        }
        catch (Exception e) {
            this.dto.setError(e.getMessage());
        }
    }
}
