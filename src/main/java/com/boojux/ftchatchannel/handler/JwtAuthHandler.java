package com.boojux.ftchatchannel.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.boojux.ftchatchannel.utils.JwtUtils;
import com.boojux.ftchatchannel.utils.RedisUtils;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.annotation.Value;

public class JwtAuthHandler extends ChannelInboundHandlerAdapter {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.header-prefix}")
    private String headerPrefix;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String token = ctx.channel().attr(AttributeKey.valueOf("Authorization")).get().toString();
        if(token != null && token.startsWith(headerPrefix)) {
            token = token.substring(headerPrefix.length());
            String userId = JwtUtils.getValueFromJwt(token, "user_id");
            if(userId != null && RedisUtils.checkToken(userId, token)) {
                super.channelRead(ctx, msg);
            }else {
                sendAuthError(ctx);
            }
        }else {
            sendAuthError(ctx);
        }
    }

    private void sendAuthError(ChannelHandlerContext ctx) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED, Unpooled.EMPTY_BUFFER);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
