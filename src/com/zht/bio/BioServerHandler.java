package com.zht.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by zht198484 on 2017/8/25.
 * Bio Server handler which implements runnable
 */
public class BioServerHandler implements Runnable {
    private Socket socket;

    BioServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try (Scanner scanner = new Scanner(this.socket.getInputStream());
             PrintWriter pw = new PrintWriter(this.socket.getOutputStream(), true)
        ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String content = "Received " + line;
                System.out.println(content);
                pw.println(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (this.socket != null) {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.socket = null;
        }
    }
}
