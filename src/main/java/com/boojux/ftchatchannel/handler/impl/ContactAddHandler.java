package com.boojux.ftchatchannel.handler.impl;

import cn.hutool.core.date.DateUtil;
import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.bean.DTO.ContactAddDTO;
import com.boojux.ftchatchannel.bean.VO.BasicMessage;
import com.boojux.ftchatchannel.bean.VO.ContactAddVO;
import com.boojux.ftchatchannel.bean.domain.FriendRequest;
import com.boojux.ftchatchannel.conf.WebSocketConnectionManager;
import com.boojux.ftchatchannel.enums.MessageStatusTypeEnum;
import com.boojux.ftchatchannel.enums.MessageTypeEnum;
import com.boojux.ftchatchannel.enums.StringEnums;
import com.boojux.ftchatchannel.enums.WebSocketFrameTypeEnum;
import com.boojux.ftchatchannel.handler.WebSocketFrameHandler;
import com.boojux.ftchatchannel.message.producer.MessageProducer;
import com.boojux.ftchatchannel.repository.FriendRequestRepository;
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
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class ContactAddHandler implements WebSocketFrameHandler {

    @Resource
    private Gson gson;
    @Resource
    private WebSocketConnectionManager webSocketConnectionManager;

    @Resource
    private CtxHelper ctxHelper;
    @Resource
    private MessageProducer messageProducer;
    private static final Logger logger = LoggerFactory.getLogger(ContactAddHandler.class);
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
        String targetId = contactAddDTO.getData().getTargetId();
        FriendRequest friendRequest = new FriendRequest();
        String userId = ctxHelper.getUserId(channelHandlerContext);
        friendRequest.setRequestee(targetId);
        friendRequest.setRequester(userId);
        friendRequest.setMessage(contactAddDTO.getData().getMessage());
        friendRequest.setTimestamp(DateUtil.now());
        friendRequest.setStatus(MessageStatusTypeEnum.UNREAD.getStatus());
//        messageProducer.sendMessage(StringEnums.RABBIT_MQ_EXCHANGE.getValue(),
//                StringEnums.RABBIT_MQ_ROUTING_KEY.getValue(),
//                gson.toJson(friendRequest));
//        ChannelHandlerContext connection = webSocketConnectionManager.getConnection(targetId);
        List<ChannelHandlerContext> connections = webSocketConnectionManager.getConnection(targetId);
        BasicMessage message = new BasicMessage();
        message.setTimestamp(DateUtil.now());
        message.setType(MessageTypeEnum.FRIEND_REQUEST.getType());
//        if (!CollectionUtils.isEmpty(connections)) {
////            connection.writeAndFlush(new TextWebSocketFrame(gson.toJson(message)));
//            connections.forEach(connection -> {
//                connection.writeAndFlush(new TextWebSocketFrame(gson.toJson(message)));
//            });
//        }
        ctxHelper.sendMsg(connections, message);
    }
}
