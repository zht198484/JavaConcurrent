package com.zht.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zht198484 on 2017/8/25.
 * blocking io server
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        int port = 8081;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Bio server is started at port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new BioServerHandler(socket));
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

}
