package com.boojux.ftchatchannel.conf;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketConnectionManager {
    private final Map<String, ChannelHandlerContext> connectionMap = new ConcurrentHashMap<>();
    public void addConnection(String userId, ChannelHandlerContext ctx) {
        connectionMap.put(userId, ctx);
    }
    public void removeConnection(String userId) {
        connectionMap.remove(userId);
    }
    public ChannelHandlerContext getConnection(String userId) {
        return connectionMap.get(userId);
    }
}
