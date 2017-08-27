package com.zht.nio.input.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * Created by zht198484 on 2017/8/27.
 * Nio server input handler which is function interface
 */
public class NioServerInputHandler {
    public static void hanldeInput(Selector selector, SelectionKey selectionKey) throws IOException {
        {
            if (selectionKey.isValid()) {
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = socketChannel.read(readBuffer);
                    if (readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        System.out.println("Receive request " + new String(bytes, "UTF-8"));
                        String response = "Response from nio server\n";
                        byte[] responseBytes = response.getBytes();
                        ByteBuffer writeBuffer = ByteBuffer.allocate(responseBytes.length);
                        writeBuffer.put(responseBytes);
                        writeBuffer.flip();
                        socketChannel.write(writeBuffer);
                        System.out.println("Send back " + response);
                    } else if (readBytes < 0) {
                        selectionKey.cancel();
                        socketChannel.close();
                    } else {
                        //ignore
                        System.out.println("0 bytes are read");
                    }
                }
            }
        }
    }
}
