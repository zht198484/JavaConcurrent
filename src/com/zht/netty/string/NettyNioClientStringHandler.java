package com.zht.netty.string;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by zht198484 on 2017/8/30.
 * netty nio server handler
 */
@Sharable
public class NettyNioClientStringHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Server Say : " + msg);
    }

}
