package data;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Random;

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
        System.out.println(authLine);
        String[] authParts = authLine.split("#");
        sessionID = Integer.parseInt(authParts[0].trim());
        username = authParts[1].trim();
        password = authParts[2].trim();
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
                sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command) throws IOException {
        bw.write(command.trim());
        bw.newLine();
        bw.flush();
        return br.readLine().trim();
    }

    public String toString() {
        return authLine;
    }

    public int getSessionID(){
        return sessionID;
    }
}
