package com.example.testMapper;

import com.example.mapper.Page3Mapper;
import com.example.pojo.dao.UserWareHouse;
import com.example.pojo.resquest.WareHouseNameAndAccount;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestPage3Mapper {
    @Resource
    Page3Mapper page3Mapper;
    @Test
    public void test(){
        System.out.println("abc");
    }
    @Test
    public void testSelectWareHouseAvailable(){
        WareHouseNameAndAccount input = new WareHouseNameAndAccount();
        input.setName("a");
        input.setAccount("443808626@qq.com");
        UserWareHouse userWareHouse = page3Mapper.selectWareHouseAvailable(input);
        System.out.println(userWareHouse);
    }
//    @Resource
//    Page3Mapper page3Mapper;
//    @Test
//    public void testSelectWareHouseList(){
//        System.out.println(page3Mapper.selectWareHouseList("443808626@qq.com"));
//    }
//    @Test
//    public void testDeleteWareHouse(){
//        System.out.println(page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(
//                "443808626@qq.com",
//                "6"
//        )));
//    }
}
