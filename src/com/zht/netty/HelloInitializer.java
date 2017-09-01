package com.zht.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by zht198484 on 2017/8/31.
 * my channel initializer
 */
public class HelloInitializer extends ChannelInitializer<SocketChannel> {
    private SimpleChannelInboundHandler simpleChannelInboundHandler;

    HelloInitializer(SimpleChannelInboundHandler simpleChannelInboundHandler) {
        this.simpleChannelInboundHandler = simpleChannelInboundHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast("framer", new FixedLengthFrameDecoder(8192);
        pipeline.addLast("framer", new LineBasedFrameDecoder(8192));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", simpleChannelInboundHandler);
    }
}
