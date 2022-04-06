package com.example.controller;

import com.example.service.Impl.Page1ServiceImpl;
import com.example.pojo.response.ResponsePage1;
import com.example.pojo.response.ResponsePate1Update;
import com.example.pojo.resquest.Page1Update;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 44380
 */
@RestController
public class Page1Controller {
    @Resource
    private Page1ServiceImpl page1Service;

    /**
     * 返回用户信息
     * @param token token
     * @return 用户信息
     */
    @GetMapping("/info")
    public ResponsePage1 getInfo(@RequestHeader(value = "token") String token){
        return page1Service.getInfo(JwtUtil.preCheckJwt(token));
    }
    /**
     * @author ljc
     * Page1Controller.updateUserInfo()
     * 更新用户信息
     * @date 2022/4/6~16:37
     * @param token token
     * @param page1Update 更新的用户信息
     * @return com.example.pojo.response.ResponsePate1Update
     */
    @PutMapping("/updateInfo")
    public ResponsePate1Update updateUserInfo(@RequestHeader(value = "token") String token, @RequestBody Page1Update page1Update){
        return page1Service.updateUserInfo(JwtUtil.preCheckJwt(token),page1Update);
    }
}
