package com.example.controller;

import com.example.service.Impl.LoginServiceImpl;
import com.example.pojo.response.ResponseLogin;
import com.example.pojo.resquest.UserData;
import com.example.utils.CheckCodeUtil;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 44380
 */
@RestController
public class LoginController {
    @Resource
    private LoginServiceImpl loginService;
    /**
     * com.example.controller.LoginController.login():
     * 登录逻辑
     * @author ljc
     * @date 2022/5/6~22:06
     * @param userData 登录用户信息
     * @return com.example.pojo.response.ResponseLogin
     */
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
    /**
     * com.example.controller.LoginController.checkCode():
     * 验证码逻辑
     * @author ljc
     * @date 2022/5/6~22:10
     * @param uuid uuid
     * @param response 响应
     */
    @GetMapping("/checkcode")
    public void checkCode(String uuid, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        String checkCode= CheckCodeUtil.outputVerifyImage(150,50,outputStream,4);
        loginService.insertUidAndVerification(uuid,checkCode);
        //TODO 便于测试
        System.out.println(checkCode);
    }
    /**
     * com.example.controller.LoginController.checkToken():
     * 检查token
     * @author ljc
     * @date 2022/5/6~22:11
     * @param token token
     * @return boolean
     */
    @GetMapping("/checkToken")
    public boolean checkToken(@RequestHeader(value = "token") String token){
        return JwtUtil.checkToken(token);
    }
}