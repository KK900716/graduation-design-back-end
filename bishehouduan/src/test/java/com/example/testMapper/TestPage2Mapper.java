package com.example.testMapper;

import com.example.mapper.Page2Mapper;
import com.example.pojo.dao.Page2Insert2;
import com.example.pojo.dao.Page2InsertDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

@SpringBootTest
public class TestPage2Mapper {
    @Resource
    Page2Mapper page2Mapper;
    @Value("${user.basePath}")
    private String basePath;
    @Test
    public void testUpdateUserInfo(){

    }
    @Test
    public void testInsertWareHouse(){
        System.out.println(page2Mapper.insertWareHouse(new Page2Insert2(
                "443808626@qq.com",
                        "one"
                )));
    }
    @Test
    public void testYml(){
        System.out.println(basePath);
        File file=new File(basePath+"/"+UUID.randomUUID());
        file.mkdir();
    }
}
