package com.zht.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zht198484 on 2017/8/26.
 * Bio Server using thread pool
 */
public class BioServerByThreadPool {
    public static void main(String[] args) throws IOException {
        {
            int port = 8081;
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("Bio server with thread pool is started at port " + port);
                ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                        50, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));
                while (true) {
                    try {
                        try (Socket socket = serverSocket.accept()) {
                            executorService.submit(new BioServerHandler(socket));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
    }
}
