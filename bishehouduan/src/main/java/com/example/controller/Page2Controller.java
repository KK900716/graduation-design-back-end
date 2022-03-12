package com.example.controller;


import com.example.Service.Impl.Page2ServiceImpl;
import com.example.Service.Page2Service;
import com.example.pojo.response.ResponsePage2;
import com.example.pojo.resquest.Page2Insert;
import com.example.utils.JwtUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class Page2Controller {
    @Resource
    private Page2ServiceImpl page2Service;
    @GetMapping("/wareHouse")
    public ResponsePage2 getWareHouse(@RequestHeader(value = "token") String token){
        return page2Service.getWareHouse(JwtUtil.preCheckJwt(token));
    }
    @PostMapping("/newWareHouse")
    public boolean insertWareHouse(@RequestHeader(value = "token") String token,@RequestBody Page2Insert page2Insert){
        return page2Service.insertWareHouse(JwtUtil.preCheckJwt(token),page2Insert);
    }
}
