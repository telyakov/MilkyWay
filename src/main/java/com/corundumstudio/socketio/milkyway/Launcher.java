package com.corundumstudio.socketio.milkyway;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
public class Launcher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("10.0.5.2");
        config.setPort(3000);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("request", TransportObject.class, new DataListener<TransportObject>() {
            @Override
            public void onData(SocketIOClient client, TransportObject data, AckRequest ackRequest) {
                //todo: fetch data from sql
                TransportObject response = new TransportObject();
                response.setUserName(data.getUserName());
                response.setMessage("testovoe soobshenie ру");
                client.sendEvent("response", response);
                // broadcast messages to all clients
//                server.getBroadcastOperations().sendEvent("query", data);
            }
        });
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();
    }

}
