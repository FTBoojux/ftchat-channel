package com.boojux.ftchatchannel.conf;

import com.boojux.ftchatchannel.utils.CtxHelper;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketConnectionManager {
    @Resource
    private CtxHelper ctxHelper;
    private final Map<String, Set<ChannelHandlerContext>> connectionMap = new ConcurrentHashMap<>();
    public void addConnection(String userId, ChannelHandlerContext ctx) {
//        connectionMap.put(userToken, ctx);
        Set<ChannelHandlerContext> ctxSet = connectionMap.get(userId);
        if(ctxSet == null) {
            ctxSet = ConcurrentHashMap.newKeySet();
            connectionMap.put(userId, ctxSet);
        }
        ctxSet.add(ctx);
//        ctxHelper.setUserToken(ctx, userToken);
    }
    public void removeConnection(ChannelHandlerContext ctx) {
        String userToken = ctxHelper.getUserToken(ctx);
        Set<ChannelHandlerContext> ctxSet = connectionMap.get(userToken);
        if(ctxSet != null) {
            ctxSet.remove(ctx);
        }
//        connectionMap.remove(userToken);

    }
    public List<ChannelHandlerContext> getConnection(String userId) {
//        return connectionMap.get(userId);
//        List<ChannelHandlerContext> contextList = connectionMap.values().stream().filter(ctx -> ctxHelper.getUserId(ctx).equals(userId)).collect(Collectors.toList());
        Set<ChannelHandlerContext> channelHandlerContexts = connectionMap.get(userId);
        if(channelHandlerContexts == null) {
            return null;
        }
        return new ArrayList<>(channelHandlerContexts);
    }
}
