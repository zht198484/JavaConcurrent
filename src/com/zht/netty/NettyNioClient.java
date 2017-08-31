package com.zht.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by zht198484 on 2017/8/31.
 * netty nio client
 */
public class NettyNioClient {
    private void bind() throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new HelloInitializer(new NettyNioClientHandler()));
            Channel channel = bootstrap.connect("127.0.0.1", 8082).sync().channel();
            channel.writeAndFlush("send client request\n");
            Thread.sleep(5000);

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyNioClient().bind();
    }
}
