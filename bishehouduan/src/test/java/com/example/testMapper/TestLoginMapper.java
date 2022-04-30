package com.example.testMapper;

import com.example.mapper.LoginMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TestLoginMapper {
    @Test
    public void test(){
        System.out.println("abc");
    }
//    @Resource
//    private LoginMapper login;
//    private String account="443808626@qq.com";
//    @Test
//    void testLogin_selectUserInfo() {
//        System.out.println(login.selectUserLogin(account));
//    }
}
