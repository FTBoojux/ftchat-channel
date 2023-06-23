package com.boojux.ftchatchannel.handler;
import com.boojux.ftchatchannel.bean.BaseWebSocketFrame;
import com.boojux.ftchatchannel.conf.WebSocketConnectionManager;
import com.boojux.ftchatchannel.enums.WebSocketFrameTypeEnum;
import com.boojux.ftchatchannel.utils.CtxHelper;
import com.boojux.ftchatchannel.utils.JwtUtils;
import com.boojux.ftchatchannel.utils.RedisUtils;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class YourCustomHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Resource
    private Gson gson;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private CtxHelper ctxHelper;
    @Resource
    private WebSocketConnectionManager webSocketConnectionManager;

    private static final Logger logger = LoggerFactory.getLogger(YourCustomHandler.class);
    private static final AttributeKey<String> USER_ID = AttributeKey.valueOf("USER_ID");
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {
        if (webSocketFrame instanceof TextWebSocketFrame) {
            String message = ((TextWebSocketFrame) webSocketFrame).text();
            System.out.println("message:" + message);
            BaseWebSocketFrame webSocketFrame1 = gson.fromJson(message, BaseWebSocketFrame.class);
            if (WebSocketFrameTypeEnum.AUTHORIZATION.getType().equals(webSocketFrame1.getType())) {
                String userId = jwtUtils.getValueFromJwt(webSocketFrame1.getToken(), "user_id");
                if (null != userId && redisUtils.checkToken(webSocketFrame1.getToken())) {
                    System.out.println("token验证成功");
                    channelHandlerContext.writeAndFlush(new TextWebSocketFrame("token验证成功"));
                    ctxHelper.setUserId(channelHandlerContext, userId);
                    webSocketConnectionManager.addConnection(userId, channelHandlerContext);
                    logger.info("用户{}连接成功", userId);
                } else {
                    message = "unsupported frame type: " + webSocketFrame.getClass().getName();
                    throw new UnsupportedOperationException(message);
                }
            }else{
                ReferenceCountUtil.retain(webSocketFrame);
                channelHandlerContext.fireChannelRead(webSocketFrame);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String userId = ctxHelper.getUserId(ctx);
        webSocketConnectionManager.removeConnection(userId);
        logger.info("用户{}断开连接", userId);
        super.channelInactive(ctx);
    }
}
