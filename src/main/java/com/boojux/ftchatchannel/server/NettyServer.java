package com.boojux.ftchatchannel.server;
import com.boojux.ftchatchannel.dispatcher.WebSocketFrameDispatcher;
import com.boojux.ftchatchannel.handler.WebSocketFrameHandler;
import com.boojux.ftchatchannel.handler.YourCustomHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NettyServer {
    private static final int PORT = 8080;

    @Resource
    private ApplicationContext applicationContext;

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) {
                            List<WebSocketFrameHandler> handlers = new ArrayList<>();
                            // Here you can add more handlers to the pipeline
                            channel.pipeline()
//                                    .addLast(new JwtAuthHandler())
                                    .addLast(new HttpServerCodec())
                                    .addLast(new HttpObjectAggregator(65536))
                                    .addLast(new WebSocketServerProtocolHandler("/websocket"))
//                                    .addLast(new YourCustomHandler())
                                    .addLast(applicationContext.getBean(YourCustomHandler.class))
                                    .addLast(new WebSocketFrameDispatcher(handlers))
                            ;
                        }
                    });

            bootstrap.bind(PORT).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
