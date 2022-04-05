package com.example;

import com.example.mapper.PublicMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author ljc
 * @version 2022/4/6
 */
@SpringBootTest
public class TestAi {
    @Resource
    PublicMapper publicMapper;
    @Test
    public void testTest(){
        System.out.println(publicMapper.selectId("443808626@qq.com"));
    }
}
