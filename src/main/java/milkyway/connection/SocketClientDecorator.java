package milkyway.connection;


import com.corundumstudio.socketio.SocketIOClient;
import milkyway.exceptions.ConnectionException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SocketClientDecorator {
    private SocketIOClient client;
    private Connection conn;

    public SocketClientDecorator(Connection conn) {
        this.conn = conn;
    }

    public boolean isLogin() {

        Object login = client.get("login");
        return login != null && Boolean.parseBoolean(login.toString());
    }

    public void setClient(SocketIOClient client) {
        this.client = client;
    }

    public boolean authenticate(String login, String identity) throws NoSuchAlgorithmException, UnsupportedEncodingException, ConnectionException {
        //todo: delete key
        LinkedHashMap<String, HashMap<String, String>> data = conn.Exec("test6543210", "core.fastLogin '" + login + "'", false);
        HashMap<String, String> storeIdentity = conn.getRow(data, 0);
        String expectedIdentity = this.hashIdentity(login, storeIdentity.get("password"));
        return expectedIdentity.equals(identity);
    }

    private String hashIdentity(String login, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        try {
            String identity = login + '&' + password;
            String hexIdentity = this.getHash(identity);

            String fullIdentity = hexIdentity + "&" + client.getSessionId();
            return this.getHash(fullIdentity);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String getHash(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) stringBuilder.append('0');
                stringBuilder.append(hex);
            }
            return stringBuilder.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
