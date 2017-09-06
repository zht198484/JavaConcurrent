package com.zht.netty.httpxml;

import com.zht.netty.NettyNioServer;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/9/2.
 * Netty nio server using message pack encoder and decoder
 */
public class NettyNioHttpXmlServer {

    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, ChannelHandler> handlerMap = new LinkedHashMap<>();

        handlerMap.put("http-decoder", new HttpRequestDecoder());
        handlerMap.put("http-aggregator", new HttpObjectAggregator(65536));
        handlerMap.put("http-encoder", new HttpRequestEncoder());
        handlerMap.put("http-xml-handler", new HttpXmlServerHandler());


        new NettyNioServer(handlerMap).bind();
    }
}
