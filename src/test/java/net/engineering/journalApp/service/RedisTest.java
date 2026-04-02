package net.engineering.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedisWrite() {
        System.out.println("TEST STARTED");

        redisTemplate.opsForValue().set("email", "gmail@email.com");

        String value = redisTemplate.opsForValue().get("email");
        System.out.println("VALUE = " + value);
    }
}