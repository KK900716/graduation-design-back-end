package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TestRedis {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Test
    public void testRedisTemplate(){
        redisTemplate.opsForValue().set("abc","132",60*3, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("abc"));
    }
}
