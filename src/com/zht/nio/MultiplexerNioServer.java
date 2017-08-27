package com.zht.nio;

import com.zht.nio.input.handler.NioServerInputHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;

/**
 * Created by zht198484 on 2017/8/23.
 * Nio Server which is based on multiplexer selector
 */
public class MultiplexerNioServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private NioSelectorHandler nioSelectorHandler;

    private MultiplexerNioServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            nioSelectorHandler = new NioSelectorHandler();
            System.out.println("MultiplexerNioServer is started at port " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }


    private void start() {
        nioSelectorHandler.select(selector, NioServerInputHandler::hanldeInput);
    }

    public static void main(String[] args) {
        new MultiplexerNioServer(8081).start();
    }


}
