package com.zht.netty.string;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * Created by zht198484 on 2017/8/30.
 * netty nio server handler
 */
class NettyNioServerStringHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
        ctx.writeAndFlush("Received your message : " + msg + "\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " is active!");
        ctx.writeAndFlush("Welcome to " + InetAddress.getLocalHost() + " service\n");
        super.channelActive(ctx);
    }
}
