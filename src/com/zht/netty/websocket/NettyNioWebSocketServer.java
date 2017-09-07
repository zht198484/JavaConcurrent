package com.zht.netty.websocket;

import com.zht.netty.NettyNioServer;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/9/2.
 * Netty nio server using message pack encoder and decoder
 */
public class NettyNioWebSocketServer {

    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, ChannelHandler> handlerMap = new LinkedHashMap<>();

        handlerMap.put("http-codec", new HttpServerCodec());
        handlerMap.put("http-aggregator", new HttpObjectAggregator(65536));
        handlerMap.put("http-trunked", new ChunkedWriteHandler());
        handlerMap.put("web-socket-handler", new WebSocketServerHandler());


        new NettyNioServer(handlerMap).bind();
    }
}
