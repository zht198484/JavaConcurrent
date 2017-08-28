package com.zht.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zht198484 on 2017/8/28.
 * Aio server demo based on nio.2
 */
public class AioServer {
    private int port;
    private CountDownLatch countDownLatch;
    private AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    private AioServer(int port) throws IOException {
        this.port = port;
        asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
        asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
    }

    private void start() {
        countDownLatch = new CountDownLatch(1);
        asynchronousServerSocketChannel.accept(this, new CompletionHandler<AsynchronousSocketChannel, AioServer>() {
            @Override
            public void completed(AsynchronousSocketChannel result, AioServer attachment) {
                attachment.asynchronousServerSocketChannel.accept(attachment, this);
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                result.read(byteBuffer, byteBuffer, new ReadCompletionHandler(result));
            }

            @Override
            public void failed(Throwable exc, AioServer attachment) {
                attachment.countDownLatch.countDown();
            }
        });
        System.out.println("Server started at port " + port);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new AioServer(8081).start();
    }
}
