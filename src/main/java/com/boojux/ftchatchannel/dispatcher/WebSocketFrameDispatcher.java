package com.boojux.ftchatchannel.dispatcher;

import com.boojux.ftchatchannel.handler.WebSocketFrameHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

public class WebSocketFrameDispatcher extends SimpleChannelInboundHandler<WebSocketFrame>{

    private final List<WebSocketFrameHandler> handlers;

    public WebSocketFrameDispatcher(List<WebSocketFrameHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {
        for(WebSocketFrameHandler handler : handlers) {
            if (handler.canHandle(webSocketFrame)) {
                handler.handle(webSocketFrame);
                return;
            }
        }
    }
}
