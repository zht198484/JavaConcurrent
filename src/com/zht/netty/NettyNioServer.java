package com.zht.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.LinkedHashMap;

/**
 * Created by zht198484 on 2017/8/30.
 * Nio server by netty
 */
public class NettyNioServer {
    private LinkedHashMap<String, ChannelHandler> handlerMap;

    public NettyNioServer(LinkedHashMap<String, ChannelHandler> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public void bind() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new HelloInitializer(handlerMap));
            ChannelFuture channelFuture = serverBootstrap.bind(8082).sync();
            System.out.println("Server is started!");
            channelFuture.channel().closeFuture().sync();
            System.out.println("Server is closed!");
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
