package com.boojux.ftchatchannel.handler.impl;

import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.bean.ContactAddDTO;
import com.boojux.ftchatchannel.conf.WebSocketConnectionManager;
import com.boojux.ftchatchannel.enums.WebSocketFrameTypeEnum;
import com.boojux.ftchatchannel.handler.WebSocketFrameHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Objects;

@Component
public class ContactAddHandler implements WebSocketFrameHandler {

    @Resource
    private Gson gson;
    @Resource
    private WebSocketConnectionManager webSocketConnectionManager;
    @Override
    public boolean canHandle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        String jsonString = ((TextWebSocketFrame) frame).text();
        BaseWebSocketFrame webSocketFrame = gson.fromJson(jsonString, BaseWebSocketFrame.class);
        return WebSocketFrameTypeEnum.CONTACT_ADD.getType().equals(webSocketFrame.getType());
    }

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        String jsonString = ((TextWebSocketFrame) frame).text();
        Type type = new TypeToken<BaseWebSocketFrame<ContactAddDTO>>() {}.getType();
        BaseWebSocketFrame<ContactAddDTO> contactAddDTO = gson.fromJson(jsonString, type);
        String token = contactAddDTO.getToken();
        String userId = contactAddDTO.getData().getTargetId();
        ChannelHandlerContext connection = webSocketConnectionManager.getConnection(userId);
        if (!Objects.isNull(connection)) {
            connection.writeAndFlush(new TextWebSocketFrame(gson.toJson(contactAddDTO)));
        }
    }
}
