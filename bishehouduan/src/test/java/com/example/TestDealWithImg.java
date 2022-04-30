package com.example;

import com.example.async.AsyncTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;

@SpringBootTest
public class TestDealWithImg {
    @Test
    public void test(){
        System.out.println("abc");
    }
//    @Resource
//    AsyncTaskService asyncTaskService;
//    @Test
//    public void testDealWithImg(){
//        for (int i = 0; i < 5; i++) {
//            asyncTaskService.dealWithImg("D:\\360MoveData\\Users\\44380\\Desktop\\yicun.jpg");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
