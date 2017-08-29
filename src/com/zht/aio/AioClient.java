package com.zht.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zht198484 on 2017/8/29.
 * Aio client based on asynchronous socket channel
 */
public class AioClient implements CompletionHandler<Void, AioClient> {
    private AsynchronousSocketChannel asynchronousSocketChannel;
    private CountDownLatch countDownLatch;
    private int port;

    private AioClient(int port) {
        this.port = port;
        try {
            asynchronousSocketChannel = AsynchronousSocketChannel.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() {
        countDownLatch = new CountDownLatch(1);
        asynchronousSocketChannel.connect(new InetSocketAddress(port), this, this);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            asynchronousSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AioClient attachment) {
        byte[] req = "client sends request".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                if (attachment.hasRemaining()) {
                    asynchronousSocketChannel.write(attachment, attachment, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    asynchronousSocketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            byte[] bytes = new byte[attachment.remaining()];
                            attachment.get(bytes);
                            System.out.println("Received server response " + new String(bytes));
                            countDownLatch.countDown();
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            onFailed(exc);
                        }
                    });
                }

            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                onFailed(exc);
            }
        });

    }

    private void onFailed(Throwable exc) {
        exc.printStackTrace();
        try {
            asynchronousSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
    }

    @Override
    public void failed(Throwable exc, AioClient attachment) {
        onFailed(exc);

    }

    public static void main(String[] args) {
        new AioClient(8081).start();
    }
}
