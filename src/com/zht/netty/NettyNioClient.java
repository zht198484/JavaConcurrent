package com.zht.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/8/31.
 * netty nio client
 */
public class NettyNioClient {
    private LinkedHashMap<String, ChannelHandler> handlerMap;

    public NettyNioClient(LinkedHashMap<String, ChannelHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public void bind() throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new HelloInitializer(handlerMap));
            Channel channel = bootstrap.connect("127.0.0.1", 8082).sync().channel();
            channel.writeAndFlush("send client request\n");
            Thread.sleep(10000);

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
