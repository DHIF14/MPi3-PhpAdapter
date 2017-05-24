package ui;

import connection.Server;
import data.Connection;

import java.net.Socket;

/**
 * Created by feba6481 on 23.05.17.
 */
public class TestiMain {
    public static void main(String[] args) throws Exception{

        while(true) {
            try {
                Server server = new Server(7778);
                server.start();
                server.join();
            }catch(Exception e){}
        }
         /*Socket socket = new Socket("localhost", 7777);
        Connection con = new Connection(socket, "7#mutter#lel");
        Thread.sleep(500);
        con.start();
        System.out.println(con.sendCommand("lel"));
        System.out.println(con.sendCommand("so"));
        System.out.println(con.sendCommand("viel"));
        System.out.println(con.sendCommand("spass"));*/
    }
}
