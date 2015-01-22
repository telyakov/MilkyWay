package milkyway;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import milkyway.connection.Connection;
import milkyway.connection.WebServiceAccessor;
import milkyway.dto.DTO;
import milkyway.dto.FileDTO;
import milkyway.dto.MultiplyExecDTO;
import milkyway.files.BlobWorker;
import milkyway.files.MetaDataWorker;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Launcher {

    public static void main(String[] args) throws InterruptedException {

        Configuration config = new Configuration();
        config.setHostname("192.168.0.34");
        config.setMaxFramePayloadLength(10000000);
        config.setMaxHttpContentLength(10000000);
        config.setPort(3000);
        final Connection conn = new WebServiceAccessor();
        final SocketIOServer server = new SocketIOServer(config);
        Launcher.addRequestEventListener(server, conn);
        Launcher.addExecMultiplyEventListener(server, conn);
        Launcher.addFileUploadEventListener(server, conn);
        Launcher.addFileRequestEventListener(server, conn);
        Launcher.addXmlRequestEventListener(server, conn);

        try {
            server.start();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            server.stop();
        }

    }

    private static void addRequestEventListener(SocketIOServer server, final Connection conn) {
        server.addEventListener("request", DTO.class, new DataListener<DTO>() {
            @Override
            public void onData(SocketIOClient client, DTO data, AckRequest ackRequest) {
                DTO response = new DTO();
                try {
                    response.setId(data.getId());
                    response.setType(data.getType());
                    LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(data.getKey(), data.getQuery(), data.getIsCache());
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    String json = gson.toJson(result);
                    response.setData(json);
                    response.setIsCache(data.getIsCache());
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("response", response);
                }
            }
        });
    }

    private static void addExecMultiplyEventListener(SocketIOServer server, final Connection conn) {
        server.addEventListener("execMultiply", MultiplyExecDTO.class, new DataListener<MultiplyExecDTO>() {
            @Override
            public void onData(SocketIOClient client, MultiplyExecDTO data, AckRequest ackRequest) {
                MultiplyExecDTO response = new MultiplyExecDTO();
                try {
                    response.setId(data.getId());
                    response.setType(data.getType());
                    Boolean success = conn.ExecMultiply(data.getKey(), data.getSqlList());
                    response.setData(success.toString());
                    if (!success) {
                        response.setError("Unknown server error");
                    }
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("response", response);
                }
            }
        });
    }

    private static void addFileUploadEventListener(SocketIOServer server, final Connection conn) {
        server.addEventListener("fileUpload", FileDTO.class, new DataListener<FileDTO>() {
            @Override
            public void onData(SocketIOClient client, FileDTO data, AckRequest ackRequest) {
                DTO response = new DTO();
                try {
                    response.setId(data.getName());
                    response.setType(data.getType());
                    response.setData("true");
                    conn.AttachmentIns(data.getKey(), data.getSql(), data.getData());
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("response", response);
                }
            }
        });
    }

    private static void addFileRequestEventListener(SocketIOServer server, final Connection conn) {
        server.addEventListener("fileRequest", FileDTO.class, new DataListener<FileDTO>() {
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
    }

    private static void addXmlRequestEventListener(SocketIOServer server, final Connection conn) {
        server.addEventListener("xmlRequest", FileDTO.class, new DataListener<FileDTO>() {
            @Override
            public void onData(SocketIOClient client, FileDTO data, AckRequest ackRequest) {
                FileDTO response = new FileDTO();
                try {
                    response.setName(data.getName());
                    response.setType(data.getType());
                    String sql = "core.XmlFileGet '" + data.getName() + "'";
                    LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(data.getKey(), sql, false);
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
    }



}
