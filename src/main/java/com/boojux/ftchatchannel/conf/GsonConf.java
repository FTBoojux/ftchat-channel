package com.boojux.ftchatchannel.conf;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConf {
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
