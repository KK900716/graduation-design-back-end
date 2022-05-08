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
     * com.example.controller.Page1Controller.getInfo():
     * 获取token
     * @author ljc
     * @date 2022/5/6~22:40
     * @param token token
     * @return com.example.pojo.response.ResponsePage1
     */
    @GetMapping("/info")
    public ResponsePage1 getInfo(@RequestHeader(value = "token") String token){
        return page1Service.getInfo(JwtUtil.preCheckJwt(token));
    }
    /**
     * com.example.controller.Page1Controller.updateUserInfo():
     * 更新用户信息
     * @author ljc
     * @date 2022/5/6~22:46
     * @param token token
     * @param page1Update 返回页面1的更新信息
     * @return com.example.pojo.response.ResponsePate1Update
     */
    @PutMapping("/updateInfo")
    public ResponsePate1Update updateUserInfo(@RequestHeader(value = "token") String token, @RequestBody Page1Update page1Update){
        return page1Service.updateUserInfo(JwtUtil.preCheckJwt(token),page1Update);
    }
}
