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
    private String command;

    public Connection(Socket socket, String authLine) {
        this.socket = socket;
        this.authLine = authLine;
        String[] authParts = authLine.split("#");
        sessionID = Integer.parseInt(authParts[0]);
        username = authParts[1];
        password = authParts[2];
    }

    public void start() {
        super.start();
    }

    @Override
    public void run() {
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                wait();
            }
        } catch (Exception e) {

        }
    }

    public boolean sendCommand(String command) {
        interrupt();
        this.command = command;
        return true;
    }

    public String toString() {
        return authLine;
    }
}
