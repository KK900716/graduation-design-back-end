package com.example.testMapper;

import com.example.mapper.Page3Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestPage3Mapper {
    @Resource
    Page3Mapper page3Mapper;
    @Test
    public void testSelectWareHouseList(){
        System.out.println(page3Mapper.selectWareHouseList("443808626@qq.com"));
    }
}
