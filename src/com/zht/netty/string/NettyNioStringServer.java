package com.zht.netty.string;

import com.zht.netty.NettyNioServer;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/9/2.
 * Netty nio server using string channel handler
 */
public class NettyNioStringServer {
    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, ChannelHandler> handlerMap = new LinkedHashMap<>();

        handlerMap.put("framer", new LineBasedFrameDecoder(8192));
        handlerMap.put("decoder", new StringDecoder());
        handlerMap.put("encoder", new StringEncoder());
        handlerMap.put("handler", new NettyNioServerStringHandler());

        new NettyNioServer(handlerMap).bind();
    }
}
