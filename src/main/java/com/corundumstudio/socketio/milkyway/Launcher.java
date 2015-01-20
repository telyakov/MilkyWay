package com.corundumstudio.socketio.milkyway;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.milkyway.connection.Connection;
import com.corundumstudio.socketio.milkyway.connection.WebService;
import com.corundumstudio.socketio.milkyway.files.BlobWorker;
import com.corundumstudio.socketio.milkyway.files.MetaDataWorker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("192.168.0.34");
        config.setMaxFramePayloadLength(10000000);
        config.setMaxHttpContentLength(10000000);
        config.setPort(3000);
        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("request", DTO.class, new DataListener<DTO>() {
            Connection conn = new WebService();

            @Override
            public void onData(SocketIOClient client, DTO data, AckRequest ackRequest) {
                DTO response = new DTO();
                try {
                    response.setId(data.getId());
                    response.setType(data.getType());
                    LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(data.getQuery(), data.getKey());
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    String json = gson.toJson(result);
                    response.setData(json);
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("response", response);
                }
            }
        });

        server.addEventListener("execMultiply", MultiplyExecDTO.class, new DataListener<MultiplyExecDTO>() {
            Connection conn = new WebService();

            @Override
            public void onData(SocketIOClient client, MultiplyExecDTO data, AckRequest ackRequest) {
                MultiplyExecDTO response = new MultiplyExecDTO();
                try {
                    response.setId(data.getId());
                    response.setType(data.getType());
                    Boolean success = conn.ExecMultiply(data.getSqlList(), data.getKey());
                    response.setData( success.toString());
                    if(!success){
                        response.setError("Unknown server error");
                    }
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("response", response);
                }
            }
        });
        server.addEventListener("fileUpload", FileDTO.class, new DataListener<FileDTO>() {
            Connection conn = new WebService();
            @Override
            public void onData(SocketIOClient client, FileDTO data, AckRequest ackRequest) {
                DTO response = new DTO();
                try {
                    response.setType(data.getType());
                    response.setData("true");
                    conn.AttachmentIns(data.getSql(), data.getKey(), data.getData());
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("response", response);
                }
            }
        });

        server.addEventListener("fileRequest", FileDTO.class, new DataListener<FileDTO>() {
            Connection conn = new WebService();

            @Override
            public void onData(SocketIOClient client, FileDTO data, AckRequest ackRequest) {
                FileDTO response = new FileDTO();
                try {
                    Thread blobThread = new Thread(new BlobWorker(response, conn, data));
                    Thread metaThread = new Thread(new MetaDataWorker(response, conn, data));
                    blobThread.start();
                    metaThread.start();
                    blobThread.join();
                    metaThread.join();
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("fileResponse", response);
                }
            }
        });
        server.addEventListener("xmlRequest", FileDTO.class, new DataListener<FileDTO>() {
            Connection conn = new WebService();

            @Override
            public void onData(SocketIOClient client, FileDTO data, AckRequest ackRequest) {
                FileDTO response = new FileDTO();
                try {
                    response.setName(data.getName());
                    response.setType(data.getType());
                    String sql = "core.XmlFileGet '" + data.getName() + "'";
                    LinkedHashMap<String,HashMap<String,String>> result = conn.Exec(sql, data.getKey());
                    HashMap<String, String> row = conn.getRow(result, 0);
                    response.setId(data.getId());
                    data.setId(Integer.parseInt(row.get("id")));
                    Thread blobThread = new Thread(new BlobWorker(response, conn, data));
                    blobThread.start();
                    blobThread.join();
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("xmlResponse", response);
                }
            }
        });
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();

    }

}
