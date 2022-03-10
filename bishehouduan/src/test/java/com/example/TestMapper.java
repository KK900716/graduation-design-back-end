package com.example;

import com.example.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TestMapper {
    @Resource
    private LoginMapper login;
    @Test
    void testLogin_selectUserInfo() {
        System.out.println(login.selectUserInfo());
    }

}
