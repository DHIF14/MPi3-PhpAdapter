package connection;

import data.Connection;
import model.CurrConnections;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by feba6481 on 23.05.17.
 */
public class Server extends Thread {

    private final ServerSocket ss;
    private final CurrConnections connectionsModel;

    public Server(int port) throws IOException{
        setDaemon(true);
        ss = new ServerSocket(port);
        connectionsModel = new CurrConnections();
    }

    public void start(){
        super.start();
    }

    @Override
    public void run(){
        System.out.println("server started");
        while(true){
            try {
                Socket client = ss.accept();
                System.out.println("accepted");
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                String authLine = br.readLine();
                Connection con=null;
                if(authLine.equals("GIMME_SESSION_PLZ")){
                    System.out.println("new session.");
                    int id = connectionsModel.createSessionID();
                    System.out.println("id: "+id);
                    bw.write(id+"");
                    bw.newLine();
                    bw.flush();
                    System.out.println(br.readLine());

                    //problem when first getting id and then establishing connection
                }else {
                    try {
                        System.out.println(authLine);
                        con = connectionsModel.getConnectionWith(authLine);
                        System.out.println("match found");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println("new incoming");
                        con = new Connection(new Socket("localhost", 7777), authLine);
                        connectionsModel.add(con);
                    } finally {
                        try {
                            String response = con.sendCommand("lel");
                            bw.write(response);
                            bw.newLine();
                            bw.flush();
                            System.out.println("wrote response: " + response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        client.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
