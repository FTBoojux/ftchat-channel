package com.boojux.ftchatchannel.utils;

import com.boojux.ftchatchannel.bean.DTO.backend.TokenResp;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.logging.Logger;

@Component
public class FtchatClient {
    @Resource
    private WebClient.Builder webClientBuilder;
    @Value("${ftchat-backend.token-retry}")
    private Integer tokenRetry;

    private String token;

    private static String TOKEN_URL = "http://localhost:8000/account/login_inner/";

    private static final Logger logger = Logger.getLogger(FtchatClient.class.getName());
    @PostConstruct
    public void init(){
        this.token = getToken();
    }

    private String getToken(){
        int retry = tokenRetry;
        while (retry > 0){
            logger.info("第" + (tokenRetry - retry + 1) + "次获取token");
            String token = getTokenFromBackend().getData().getAccess_token();
            if(token != null){
                logger.info("获取token成功: " + token);
                return token;
            }
            logger.info("获取token失败,重试");
            retry--;
        }
        logger.info("获取token失败");
        return null;
    }

    private TokenResp getTokenFromBackend(){

        return  webClientBuilder.build()
                .post()
                .uri(TOKEN_URL)
                // 携带body参数
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"email\":\"comn\",\"password\":\"admin2023\"}")
                .retrieve()
                .bodyToMono(TokenResp.class)
                .block();
    }
}
