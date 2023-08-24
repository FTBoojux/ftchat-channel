package com.boojux.ftchatchannel.handler.impl;

import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.conf.WebSocketConnectionManager;
import com.boojux.ftchatchannel.enums.WebSocketFrameTypeEnum;
import com.boojux.ftchatchannel.handler.WebSocketFrameHandler;
import com.boojux.ftchatchannel.message.producer.MessageProducer;
import com.boojux.ftchatchannel.utils.CtxHelper;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversationMessageHandler implements WebSocketFrameHandler {
    @Resource
    private Gson gson;
    @Resource
    private WebSocketConnectionManager webSocketConnectionManager;
    @Resource
    private CtxHelper ctxHelper;
    @Resource
    private MessageProducer messageProducer;
    private static final Logger logger = LoggerFactory.getLogger(ConversationMessageHandler.class);
    @Override
    public boolean canHandle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        String jsonString = ((TextWebSocketFrame) frame).text();
        BaseWebSocketFrame webSocketFrame = gson.fromJson(jsonString, BaseWebSocketFrame.class);
        return WebSocketFrameTypeEnum.CONVERSATION_MESSAGE.getType().equals(webSocketFrame.getType());
    }

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {

    }
}
