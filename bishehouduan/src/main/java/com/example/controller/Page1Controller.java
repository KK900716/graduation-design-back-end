package com.example.controller;

import com.example.Service.Impl.Page1ServiceImpl;
import com.example.pojo.response.ResponsePage1;
import com.example.pojo.response.ResponsePate1Update;
import com.example.pojo.resquest.Page1Update;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class Page1Controller {
    @Resource
    private Page1ServiceImpl page1Service;
    @GetMapping("/info")
    public ResponsePage1 getInfo(@RequestHeader(value = "token") String token){
        return page1Service.getInfo(JwtUtil.preCheckJwt(token));
    }
    @PutMapping("/updateInfo")
    public ResponsePate1Update updateUserInfo(@RequestHeader(value = "token") String token, @RequestBody Page1Update page1Update){
        return page1Service.updateUserInfo(JwtUtil.preCheckJwt(token),page1Update);
    }
}
