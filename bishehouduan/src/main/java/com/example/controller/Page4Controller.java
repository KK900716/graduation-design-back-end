package com.example.controller;

import com.example.service.Impl.Page4ServiceImpl;
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
    /**
     * @author ljc
     * @desciption Page4Controller.updatePassword()
     * 修改密码
     * @date 2022/4/6~16:49
     * @param token token
     * @param updatePassword 密码
     * @return boolean
     */
    @PostMapping("/updatePassword")
    public boolean updatePassword(@RequestHeader String token, @RequestBody UpdatePassword updatePassword){
        return page4Service.updatePassword(JwtUtil.preCheckJwt(token),updatePassword);
    }
}
