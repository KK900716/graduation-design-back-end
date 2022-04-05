package com.example.controller;

import com.example.Service.Impl.Page4ServiceImpl;
import com.example.pojo.resquest.UpdatePassword;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author 44380
 */
@RestController
public class Page4Controller {
    @Resource
    Page4ServiceImpl page4Service;
    @PostMapping("updatePassword")
    public boolean updatePassword(@RequestHeader String token, @RequestBody UpdatePassword updatePassword){
        return page4Service.updatePassword(JwtUtil.preCheckJwt(token),updatePassword);
    }
}
