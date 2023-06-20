package com.boojux.ftchatchannel;

import com.boojux.ftchatchannel.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FtchatChannelApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FtchatChannelApplication.class, args);
		try {
			context.getBean(NettyServer.class).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
