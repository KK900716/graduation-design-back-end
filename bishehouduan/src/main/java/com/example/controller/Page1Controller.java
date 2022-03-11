package com.example.controller;

import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Page1Controller {
    @GetMapping("/info")
    public String getInfo(@RequestHeader(value = "token") String token){
        String account = JwtUtil.preCheckJwt(token);
        System.out.println(account);
        return "abc";
    }
}
