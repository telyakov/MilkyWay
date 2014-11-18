package com.corundumstudio.socketio.milkyway;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.milkyway.connection.Connection;
import com.corundumstudio.socketio.milkyway.connection.WebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("192.168.0.34");
        config.setPort(3000);
        final SocketIOServer server = new SocketIOServer(config);

        server.addEventListener("request", DTO.class, new DataListener<DTO>() {
            Connection conn = new WebService();
            @Override
            public void onData(SocketIOClient client, DTO data, AckRequest ackRequest) {

                DTO response = new DTO();
                try{
                    LinkedHashMap<String, HashMap<String,String>> result = conn.Exec(data.getQuery(), data.getKey());
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    String json = gson.toJson(result);
                    response.setData(json);
                    response.setId(data.getId());
                    response.setType(data.getType());
                }catch (Exception e){
                    response.setError(e.getMessage());

                }
                finally {
                    client.sendEvent("response", response);
                }
            }
        });

        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }

}
