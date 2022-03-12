package com.example.controller;

import com.example.Service.Impl.Page1ServiceImpl;
import com.example.Service.Page1Service;
import com.example.pojo.dao.UserInfo;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Page1Controller {
    @Resource
    private Page1ServiceImpl page1Service;
    @GetMapping("/info")
    public UserInfo getInfo(@RequestHeader(value = "token") String token){
        return page1Service.getInfo(JwtUtil.preCheckJwt(token));
    }
}
