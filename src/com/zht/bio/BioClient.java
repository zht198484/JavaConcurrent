package com.zht.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by zht198484 on 2017/8/25.
 * Bio Client demo
 */
public class BioClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("127.0.0.1", 8081);
             Scanner scanner = new Scanner(socket.getInputStream());
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)
        ) {
            pw.println("client request");
            System.out.println("Send client request");
            System.out.println(scanner.nextLine());
        }
    }
}
