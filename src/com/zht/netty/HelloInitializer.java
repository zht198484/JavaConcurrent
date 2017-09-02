package com.zht.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zht198484 on 2017/8/31.
 * my channel initializer
 */
public class HelloInitializer extends ChannelInitializer<SocketChannel> {
    private LinkedHashMap<String, ChannelHandler> handlerMap;
    private volatile boolean initialized = false;

    HelloInitializer(LinkedHashMap<String, ChannelHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        if (!initialized) {
            for (Map.Entry<String, ChannelHandler> channelHandlerEntry : handlerMap.entrySet()) {
                pipeline.addLast(channelHandlerEntry.getKey(), channelHandlerEntry.getValue());
            }
            initialized = true;
        }
    }
}
