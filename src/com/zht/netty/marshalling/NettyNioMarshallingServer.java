package com.zht.netty.marshalling;

import com.zht.netty.NettyNioServer;
import com.zht.netty.string.NettyNioServerStringHandler;
import io.netty.channel.ChannelHandler;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/9/2.
 * Netty nio server using message pack encoder and decoder
 */
public class NettyNioMarshallingServer {
    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, ChannelHandler> handlerMap = new LinkedHashMap<>();

        handlerMap.put("marshalling decoder", MarshallingCodeCFactory.buildMarshallingDecoder());
        handlerMap.put("marshalling encoder", MarshallingCodeCFactory.buildMarshallingEncoder());
        handlerMap.put("handler", new NettyNioServerStringHandler());

        new NettyNioServer(handlerMap).bind();
    }
}
