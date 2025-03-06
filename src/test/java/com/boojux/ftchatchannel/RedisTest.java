package com.boojux.ftchatchannel;

import com.boojux.ftchatchannel.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest(classes = FtchatChannelApplication.class)
@Slf4j
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;
    @Test
    public void testScanKeys(){
        Set<String> strings = redisUtils.scanKeys("offline:message:*");
        assert strings != null;
        log.info(strings.toString());
    }
}
