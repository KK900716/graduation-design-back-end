package com.example.testMapper;

import com.example.mapper.Page2Mapper;
import com.example.pojo.dao.Page2Insert2;
import com.example.pojo.dao.Page2InsertDomain;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestPage2Mapper {
    @Resource
    Page2Mapper page2Mapper;
    @Test
    public void testUpdateUserInfo(){
        page2Mapper.updateUserInfo(new Page2InsertDomain(
                "443808626@qq.com",
                0
        ));
    }
    @Test
    public void testInsertWareHouse(){
        System.out.println(page2Mapper.insertWareHouse(new Page2Insert2(
                "443808626@qq.com",
                        "one"
                )));
    }
}
