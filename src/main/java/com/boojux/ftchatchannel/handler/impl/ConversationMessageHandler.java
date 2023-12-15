package com.boojux.ftchatchannel.handler.impl;

import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.bean.DTO.ConversationMessageDTO;
import com.boojux.ftchatchannel.bean.DTO.messageSend.MessageSendDTO;
import com.boojux.ftchatchannel.conf.WebSocketConnectionManager;
import com.boojux.ftchatchannel.enums.WebSocketFrameTypeEnum;
import com.boojux.ftchatchannel.handler.WebSocketFrameHandler;
import com.boojux.ftchatchannel.message.producer.MessageProducer;
import com.boojux.ftchatchannel.repository.ParticipantRepository;
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

@Component
public class ConversationMessageHandler implements WebSocketFrameHandler {
    @Resource
    private Gson gson;
    @Resource
    private WebSocketConnectionManager webSocketConnectionManager;
    @Resource
    private CtxHelper ctxHelper;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private ParticipantRepository participantRepository;
    private static final Logger logger = LoggerFactory.getLogger(ConversationMessageHandler.class);
    @Override
    public boolean canHandle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        String jsonString = ((TextWebSocketFrame) frame).text();
        BaseWebSocketFrame webSocketFrame = gson.fromJson(jsonString, BaseWebSocketFrame.class);
        return WebSocketFrameTypeEnum.CONVERSATION_MESSAGE.getType().equals(webSocketFrame.getType());
    }

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, WebSocketFrame frame) {
        String jsonString = ((TextWebSocketFrame) frame).text();
        Type type = new TypeToken<BaseWebSocketFrame<MessageSendDTO>>() {}.getType();
        BaseWebSocketFrame<MessageSendDTO> conversationMessage = gson.fromJson(jsonString, type);
        String token = conversationMessage.getToken();
        String userId = ctxHelper.getUserId(channelHandlerContext);
        if(userId == null){
            logger.error("userId is null");
            return;
        }
        String conversationId = conversationMessage.getData().getConversationId();
        if(conversationId == null){
            logger.error("conversationId is null");
            return;
        }
        // 查询会话中的所有成员，除了当前用户
        participantRepository.findAllByConversation(Integer.parseInt(conversationId)).stream()
//                .filter(participant -> !participant.getUser().equals(userId))
                .forEach(participant -> {
                    List<ChannelHandlerContext> connection = webSocketConnectionManager.getConnection(participant.getUser());
                    ctxHelper.sendMsg(connection, conversationMessage);
//                    ChannelHandlerContext connection = webSocketConnectionManager.getConnection(participant.getUser());
//                    if(connection != null){
//                        connection.writeAndFlush(new TextWebSocketFrame(gson.toJson( jsonString)));
//                    }
                });
    }
}
