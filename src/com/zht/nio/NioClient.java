package com.zht.nio;

import com.zht.nio.input.handler.NioClientInputHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * Created by zht198484 on 2017/8/27.
 * Nio client which will send request to nio server.
 */
public class NioClient {
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private NioSelectorHandler nioSelectorHandler;


    private NioClient(int port) throws IOException {
        this.port = port;
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        nioSelectorHandler = new NioSelectorHandler();
    }

    private void start() throws IOException {
        doConnect();

        nioSelectorHandler.select(selector, NioClientInputHandler::hanldeInput);
    }


    private void doConnect() throws IOException {
        if (socketChannel.connect(new InetSocketAddress("127.0.0.1", port))) {
            SocketChannelWriter.write(selector, socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    public static void main(String[] args) throws IOException {
        new NioClient(8081).start();
    }


}
