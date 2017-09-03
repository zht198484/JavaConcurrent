package com.zht.netty.msgpack;

import com.zht.netty.NettyNioServer;
import com.zht.netty.string.NettyNioServerStringHandler;
import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/9/2.
 * Netty nio server using message pack encoder and decoder
 */
public class NettyNioMsgpackServer {
    public static void main(String[] args) throws InterruptedException {
        LinkedHashMap<String, ChannelHandler> handlerMap = new LinkedHashMap<>();

        handlerMap.put("frame decoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
        handlerMap.put("msgpack decoder", new MsgpackDecoder());
        handlerMap.put("frame ecoder", new LengthFieldPrepender(2));
        handlerMap.put("msgpack encoder", new MsgpackEncoder());
        handlerMap.put("handler", new NettyNioServerStringHandler());

        new NettyNioServer(handlerMap).bind();
    }
}
