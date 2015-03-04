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
import milkyway.doc.DocBuilder;
import milkyway.doc.DocSettings;
import milkyway.dto.DTO;
import milkyway.dto.DocDTO;
import milkyway.dto.FileDTO;
import milkyway.dto.MultiplyExecDTO;
import milkyway.excel.*;
import milkyway.files.BlobWorker;
import milkyway.files.MetaDataWorker;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @see 'https://github.com/mrniko/netty-socketio-demo'
 */
public class Launcher {

    private static final String hostName = "192.168.0.34";

    public static void main(String[] args) throws InterruptedException {


        Configuration config = new Configuration();
        config.getSocketConfig().setReuseAddress(true);
        config.setHostname(hostName);
        System.out.printf("Host name = '{0}'", hostName);
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
        Launcher.addExportToExcelEventListener(server);
        Launcher.addDocumentBuilderEventListener(server, conn);

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
            public void onData(SocketIOClient client, DTO request, AckRequest ackRequest) {
                DTO response = new DTO();
                try {

                    response.setId(request.getId());
                    response.setType(request.getType());

                    LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(request.getKey(), request.getQuery(), request.getIsCache());
                    Gson gson = new GsonBuilder().serializeNulls().create();
                    String json = gson.toJson(result);

                    response.setData(json);
                    response.setIsCache(request.getIsCache());
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
            public void onData(SocketIOClient client, MultiplyExecDTO rquest, AckRequest ackRequest) {
                MultiplyExecDTO response = new MultiplyExecDTO();
                try {
                    response.setId(rquest.getId());
                    response.setType(rquest.getType());

                    Boolean success = conn.ExecMultiply(rquest.getKey(), rquest.getSqlList());
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
            public void onData(SocketIOClient client, FileDTO request, AckRequest ackRequest) {
                DTO response = new DTO();
                try {
                    response.setId(request.getName());
                    response.setType(request.getType());
                    response.setData("true");

                    conn.AttachmentIns(request.getKey(), request.getSql(), request.getData());
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
            public void onData(SocketIOClient client, FileDTO request, AckRequest ackRequest) {
                FileDTO response = new FileDTO();
                try {
                    response.setId(request.getId());
                    response.setFileID(request.getFileID());
                    response.setType(request.getType());

                    Thread blobThread = new Thread(new BlobWorker(response, conn, request));
                    Thread metaThread = new Thread(new MetaDataWorker(response, conn, request));
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
            public void onData(SocketIOClient client, FileDTO request, AckRequest ackRequest) {
                FileDTO response = new FileDTO();
                try {
                    response.setId(request.getId());
                    response.setName(request.getName());
                    response.setType(request.getType());

                    String sql = "core.XmlFileGet '" + request.getName() + "'";
                    LinkedHashMap<String, HashMap<String, String>> result = conn.Exec(request.getKey(), sql, false);
                    HashMap<String, String> row = conn.getRow(result, 0);

                    request.setFileID(row.get("id"));
                    response.setFileID(request.getFileID());

                    Thread blobThread = new Thread(new BlobWorker(response, conn, request));
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

    private static void addExportToExcelEventListener(SocketIOServer server) {
        final ExcelBuilder excelBuilder = new ExcelBuilder();
        server.addEventListener("exportToExcel", DocDTO.class, new DataListener<DocDTO>() {
            @Override
            public void onData(SocketIOClient client, DocDTO request, AckRequest ackRequest) {
                FileDTO response = new FileDTO();
                try {
                    response.setId(request.getId());
                    response.setName(request.getName());
                    response.setType(request.getType());

                    FormData formData = new FormData(request.getData());
                    Settings settings = new Settings(request.getSettings());
                    byte[] buffer = excelBuilder.makeExcel(formData, settings);

                    response.setData(buffer);
                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("fileResponse", response);
                }
            }
        });
    }

    private static void addDocumentBuilderEventListener(SocketIOServer server,  final Connection conn) {
        server.addEventListener("documentBuilder", DocDTO.class, new DataListener<DocDTO>() {
            @Override
            public void onData(SocketIOClient client, DocDTO request, AckRequest ackRequest) {
                FileDTO response = new FileDTO();
                try {
                    response.setId(request.getId());
                    response.setType(request.getType());

                    DocSettings docSettings = new DocSettings(request.getData());

                    DocBuilder docBuilder = new DocBuilder(conn);
                    DocBuilder.DocBuilderResult docBuilderResult = docBuilder.make(docSettings);

                    response.setName(docBuilderResult.getName());
                    response.setData(docBuilderResult.getResult());

                } catch (Exception e) {
                    response.setError(e.getMessage());

                } finally {
                    client.sendEvent("fileResponse", response);
                }
            }
        });
    }

}
