/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticktacktoeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 *
 * @author hp
 */
public class ServerHomePageController {
    
    Button btnStartServer;
    Button brnStopServer;
    Text txtServerStatus;
    boolean isServerRun;
    Thread serverThread;
    ServerSocket serverSocket;

    ServerHomePageController(Button btnStartServer, Button brnStopServer, Text txtServerStatus) {
      this.btnStartServer=btnStartServer;
      this.brnStopServer=brnStopServer;
      this.txtServerStatus=txtServerStatus;
      isServerRun=false;
      btnStartServer.setOnAction(( event) -> {
           startServer();
           System.out.println("Start");
        });
      brnStopServer.setOnAction(( event) -> {
           stopServer();
           System.out.println("stop");
        });
    
}
    public void startServer() {
        try {
            serverSocket = new ServerSocket(5005);
        } catch (IOException ex) {
            System.out.println("Server socketException");
        }
        serverThread = new Thread() {
            public void run() {
                while (isServerRun=false) {
                    Socket s;
                    try {
                        
                        s = serverSocket.accept();
                        System.out.println("sdasd");
                        new ClientHandler(s);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
            }
        };
        isServerRun = true;
        serverThread.start();
        
    }


public void stopServer() {
        if (isServerRun==true) {
            serverThread.interrupt();
           
            try {
                serverSocket.close();// Close the server socket
                System.out.println(serverSocket.isClosed());
            } catch (IOException ex) {
                System.out.println("Error closing server socket");
            }
            isServerRun = false;
   
        }
    }
}
