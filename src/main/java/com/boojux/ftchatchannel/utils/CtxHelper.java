package com.boojux.ftchatchannel.utils;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.springframework.stereotype.Component;

@Component
public class CtxHelper {
    private static final AttributeKey<String> USER_ID = AttributeKey.valueOf("userId");
    public void setUserId(ChannelHandlerContext ctx, String userId) {
        ctx.channel().attr(USER_ID).set(userId);
    }
    public String getUserId(ChannelHandlerContext ctx) {
        return ctx.channel().attr(USER_ID).get();
    }
    public void removeUserId(ChannelHandlerContext ctx) {
        ctx.channel().attr(USER_ID).remove();
    }
}
