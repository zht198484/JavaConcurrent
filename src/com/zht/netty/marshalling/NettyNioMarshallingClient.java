package com.zht.netty.marshalling;

import com.zht.netty.NettyNioClient;
import com.zht.netty.string.NettyNioClientStringHandler;
import io.netty.channel.ChannelHandler;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/9/2.
 * Netty nio client using message pack encoder and decoder
 */
public class NettyNioMarshallingClient {
    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, ChannelHandler> handlerMap = new LinkedHashMap<>();

        handlerMap.put("marshalling decoder", MarshallingCodeCFactory.buildMarshallingDecoder());
        handlerMap.put("marshalling encoder", MarshallingCodeCFactory.buildMarshallingEncoder());
        handlerMap.put("handler", new NettyNioClientStringHandler());

        new NettyNioClient(handlerMap).bind();
    }
}
