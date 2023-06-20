package com.boojux.ftchatchannel.handler;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public interface WebSocketFrameHandler {
    boolean canHandle(WebSocketFrame frame);
    void handle(WebSocketFrame frame);
}
