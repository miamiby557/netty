package com.szcinda.express.controller.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
//    @Override
//    protected void initChannel(Channel ch) throws Exception {
//        // ChannelOutboundHandler，依照逆序执行
//        ch.pipeline().addLast("encoder", new StringEncoder());
//
//        // 属于ChannelInboundHandler，依照顺序执行
//        ch.pipeline().addLast("decoder", new StringDecoder());
//        ChannelPipeline pipeline = ch.pipeline();
//        // 处理粘包问题
//        ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(2048,delimiter));
//        /**
//         * 自定义ChannelInboundHandlerAdapter
//         */
//        ch.pipeline().addLast(new NettyChannelInboundHandlerAdapter());
//
//    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // ChannelOutboundHandler，依照逆序执行
        ch.pipeline().addLast("encoder", new StringEncoder(StandardCharsets.UTF_8));
        // 属于ChannelInboundHandler，依照顺序执行
        ch.pipeline().addLast("decoder", new StringDecoder(StandardCharsets.UTF_8));
        ChannelPipeline pipeline = ch.pipeline();
        // 请求路径
//        pipeline.addLast(new HttpRequestHandler("/chrome"));
//        pipeline.addLast(new WebSocketServerProtocolHandler("/chrome"));
        // 处理粘包问题
//        ByteBuf delimiter = Unpooled.copiedBuffer("$$".getBytes());
//        pipeline.addLast(new DelimiterBasedFrameDecoder(2048,delimiter));
        /**
         * 自定义ChannelInboundHandlerAdapter
         */
        ch.pipeline().addLast(new NettyChannelInboundHandlerAdapter());
    }
}
