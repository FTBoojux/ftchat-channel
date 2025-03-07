package com.boojux.ftchatchannel.utils;

import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.bean.DTO.messageSend.MessageSendDTO;
import com.boojux.ftchatchannel.bean.VO.BasicMessage;
import com.boojux.ftchatchannel.enums.StringEnums;
import com.boojux.ftchatchannel.message.producer.MessageProducer;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Slf4j
public class CtxHelper {
    @Resource
    private Gson gson;
    @Resource
    private MessageProducer messageProducer;
    private static final AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");
    private static final AttributeKey<String> USER_TOKEN = AttributeKey.valueOf("userToken");
    public void setUserId(ChannelHandlerContext ctx, String userId) {
        ctx.channel().attr(USER_ID).set(userId);
    }
    public void setUserToken(ChannelHandlerContext ctx, String userToken) {
        ctx.channel().attr(USER_TOKEN).set(userToken);
    }
    public String getUserId(ChannelHandlerContext ctx) {
        return ctx.channel().attr(USER_ID).get();
    }
    public String getUserToken(ChannelHandlerContext ctx) {
        return ctx.channel().attr(USER_TOKEN).get();
    }
    public void removeUserId(ChannelHandlerContext ctx) {
        ctx.channel().attr(USER_ID).remove();
    }
    public void removeUserToken(ChannelHandlerContext ctx) {
        ctx.channel().attr(USER_TOKEN).remove();
    }
    public  void  sendMsg(List<ChannelHandlerContext> ctxes, Object msg) {
        if(CollectionUtils.isEmpty(ctxes)) {
            log.info("用户不在线");
            messageProducer.sendMessage(StringEnums.RABBIT_MQ_EXCHANGE.getValue(),
                    StringEnums.RABBIT_MQ_ROUTING_KEY.getValue(), msg);
        }else {
            for(ChannelHandlerContext ctx : ctxes) {
                ctx.writeAndFlush(new TextWebSocketFrame(gson.toJson(msg)));
            }
        }
    }
}
