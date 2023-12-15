package com.boojux.ftchatchannel.handler.impl;

import cn.hutool.core.date.DateUtil;
import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.bean.DTO.GroupJoinRequestDTO;
import com.boojux.ftchatchannel.bean.VO.BasicMessage;
import com.boojux.ftchatchannel.conf.WebSocketConnectionManager;
import com.boojux.ftchatchannel.enums.WebSocketFrameTypeEnum;
import com.boojux.ftchatchannel.handler.WebSocketFrameHandler;
import com.boojux.ftchatchannel.utils.CtxHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class GroupJoinHandler implements WebSocketFrameHandler {
    @Resource
    private Gson gson;
    @Resource
    private WebSocketConnectionManager webSocketConnectionManager;
    @Resource
    private CtxHelper ctxHelper;
    private static final Logger logger = LoggerFactory.getLogger(ContactAddHandler.class);
    @Override
    public boolean canHandle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        String jsonString = ((TextWebSocketFrame) frame).text();
        BaseWebSocketFrame webSocketFrame = gson.fromJson(jsonString, BaseWebSocketFrame.class);
        return WebSocketFrameTypeEnum.GROUP_JOIN_REQUEST.getType().equals(webSocketFrame.getType());
    }

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        String jsonString = ((TextWebSocketFrame) frame).text();
        Type type = new TypeToken<BaseWebSocketFrame<GroupJoinRequestDTO>>() {}.getType();
        BaseWebSocketFrame<GroupJoinRequestDTO> groupJoinRequestDTO = gson.fromJson(jsonString, type);
        if(groupJoinRequestDTO.getData().getGroupId() == null){
            logger.error("groupId is null");
            return;
        }
        Boolean agree = groupJoinRequestDTO.getData().getAgree();
        if(!Objects.isNull(agree) && agree){
            BasicMessage message = new BasicMessage();
            message.setTimestamp(DateUtil.now());
            message.setType(WebSocketFrameTypeEnum.GROUP_JOIN_REQUEST.getType());
            message.setReceiver(groupJoinRequestDTO.getData().getRequester());
            List<ChannelHandlerContext> connection = webSocketConnectionManager.getConnection(groupJoinRequestDTO.getData().getRequester());
//            connection.writeAndFlush(new TextWebSocketFrame(gson.toJson(message)));
            ctxHelper.sendMsg(connection, message);
        }
    }
}
