package com.zht.netty.httpfile;

import com.zht.netty.NettyNioServer;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/9/2.
 * Netty nio server using message pack encoder and decoder
 */
public class NettyNioHttpFileServer {
    private static final String URL = "/src";

    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, ChannelHandler> handlerMap = new LinkedHashMap<>();

        handlerMap.put("http-decoder", new HttpRequestDecoder());
        handlerMap.put("http-aggregator", new HttpObjectAggregator(65536));
        handlerMap.put("http-encoder", new HttpRequestEncoder());
        handlerMap.put("http-chunked", new ChunkedWriteHandler());
        handlerMap.put("http-file-handler", new HttpFileServerHandler(URL));


        new NettyNioServer(handlerMap).bind();
    }
}
