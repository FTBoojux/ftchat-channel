package com.boojux.ftchatchannel;

import com.boojux.ftchatchannel.server.NettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class FtchatChannelApplication {
	private static final Logger logger = LoggerFactory.getLogger(FtchatChannelApplication.class.getName());
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FtchatChannelApplication.class, args);
		try {
			context.getBean(NettyServer.class).start();
		} catch (Exception e) {
			logger.error("Netty server start error: {} ", e.getMessage());
		}
	}

}
