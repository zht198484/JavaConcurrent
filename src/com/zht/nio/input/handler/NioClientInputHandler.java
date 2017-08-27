package com.zht.nio.input.handler;

import com.zht.nio.SocketChannelWriter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by zht198484 on 2017/8/27.
 * Nio server input handler which is function interface
 */
public class NioClientInputHandler {
    public static void hanldeInput(Selector selector, SelectionKey selectionKey) throws IOException {
        if (selectionKey.isValid()) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            if (selectionKey.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    SocketChannelWriter.write(selector, socketChannel);
                }
            }
            if (selectionKey.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    System.out.println("Receive response " + new String(bytes, "UTF-8"));
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
