package com.boojux.ftchatchannel.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public interface WebSocketFrameHandler {
    boolean canHandle(ChannelHandlerContext channelHandlerContext,WebSocketFrame frame);
    void handle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame);
}
