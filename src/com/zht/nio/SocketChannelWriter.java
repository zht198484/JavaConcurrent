package com.zht.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by zht198484 on 2017/8/27.
 * Util to read and write socket channel
 */
public class SocketChannelWriter {
    public static void write(Selector selector, SocketChannel socketChannel) throws IOException {
        socketChannel.register(selector, SelectionKey.OP_READ);
        byte[] requestBytes = "Send client request".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(requestBytes.length);
        writeBuffer.put(requestBytes);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("Send client request succeed.");
        }
    }

}
