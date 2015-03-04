package milkyway.files;

import milkyway.connection.Connection;
import milkyway.dto.FileDTO;

import java.util.HashMap;
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
            String sql = "Attachments.MetaDataGet " + this.request.getFileID();
            LinkedHashMap<String,HashMap<String,String>> result = conn.Exec(this.request.getKey(), sql, false);
            HashMap<String, String> row = conn.getRow(result, 0);
            this.dto.setName(row.get("name"));
        }
        catch (Exception e) {
            this.dto.setError(e.getMessage());
        }
    }
}
