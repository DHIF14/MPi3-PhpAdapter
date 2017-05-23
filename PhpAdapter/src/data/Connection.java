package data;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by feba6481 on 23.05.17.
 */
public class Connection extends Thread {
    private final Socket socket;
    private final int sessionID;
    private final String username, password;
    private Date lastCommunicated;
    private final String authLine;
    private final BufferedWriter bw;
    private final BufferedReader br;

    public Connection(Socket socket, String authLine) throws IOException {
        //setDaemon(true);
        this.socket = socket;
        this.authLine = authLine;
        String[] authParts = authLine.split("#");
        sessionID = Integer.parseInt(authParts[0]);
        username = authParts[1];
        password = authParts[2];
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void start() {
        super.start();
    }

    @Override
    public void run() {
        try {
            while (isAlive()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) throws InterruptedException, IOException {
        bw.write(command);
        bw.newLine();
        bw.flush();
        return br.readLine();
    }

    public String toString() {
        return authLine;
    }
}
