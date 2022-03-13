package com.example.controller;

import com.example.Service.Impl.LoginServiceImpl;
import com.example.pojo.response.ResponseLogin;
import com.example.pojo.resquest.UserData;
import com.example.utils.CheckCodeUtil;
import com.example.utils.JwtUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {
    @Resource
    private LoginServiceImpl loginService;
    //登录逻辑
    @PostMapping("/login")
    public ResponseLogin login(@RequestBody UserData userData){
        ResponseLogin responseLogin=new ResponseLogin();
        boolean success= loginService.userDataVerification(userData);
        //返回登录验证成功或失败信息
        if (success){
            responseLogin.setState("200");
            responseLogin.setToken(JwtUtil.createToken(userData.getUserAccount(),userData.getUserPassword()));
        }else {
            responseLogin.setState("404");
        }
        return responseLogin;
    }
    //验证码请求
    @GetMapping("/checkcode")
    public void checkCode(String uuid, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        String checkCode= CheckCodeUtil.outputVerifyImage(150,50,outputStream,4);
        loginService.insertUidAndVerification(uuid,checkCode);
        //TODO 便于测试
        System.out.println(checkCode);
    }
    @GetMapping("/checkToken")
    public boolean checkToken(@RequestHeader(value = "token") String token){
        return JwtUtil.checkToken(token);
    }
}
