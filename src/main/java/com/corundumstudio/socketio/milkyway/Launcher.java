package com.corundumstudio.socketio.milkyway;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("10.0.5.2");
        config.setPort(3000);
        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("request", TDO.class, new DataListener<TDO>() {
            @Override
            public void onData(SocketIOClient client, TDO data, AckRequest ackRequest) {
                TDO response = new TDO();
                try{
                    HashMap<String, HashMap<String,String>> result = WebService.Exec(data.getQuery());
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
                // broadcast messages to all clients
//                server.getBroadcastOperations().sendEvent("query", data);
            }
        });

        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }

}
