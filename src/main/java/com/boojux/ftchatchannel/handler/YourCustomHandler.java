package com.boojux.ftchatchannel.handler;
import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.utils.RedisUtils;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.CharsetUtil;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class YourCustomHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Resource
    private Gson gson;
    @Resource
    private RedisUtils redisUtils;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {
        if (webSocketFrame instanceof TextWebSocketFrame) {
            String message = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println("message:" + message);
            BaseWebSocketFrame webSocketFrame1 = gson.fromJson(message, BaseWebSocketFrame.class);
            if (redisUtils.checkToken(webSocketFrame1.getToken())) {
                System.out.println("token验证成功");
                channelHandlerContext.writeAndFlush(new TextWebSocketFrame("token验证成功"));
            } else {
                message = "unsupported frame type: " + webSocketFrame.getClass().getName();
                throw new UnsupportedOperationException(message);
            }
            System.out.println("WebSocketFrame");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
