package com.example.testMapper;

import com.example.mapper.Page1Mapper;
import com.example.pojo.dao.Page1UpdateDomain;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestPage1Mapper {
    @Resource
    Page1Mapper page1Mapper;
    @Test
    public void testUpdateUserInfo(){
        System.out.println(page1Mapper.updateUserInfo(new Page1UpdateDomain(
                "420736073@qq.com",
                "443808626@qq.com",
                "梁嘉"
        )));
    }
}
