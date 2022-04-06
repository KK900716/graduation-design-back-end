package com.example.controller;


import com.example.Service.Impl.Page2ServiceImpl;
import com.example.pojo.response.ResponsePage2;
import com.example.pojo.resquest.Page2Insert;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 44380
 */
@RestController
public class Page2Controller {
    @Resource
    private Page2ServiceImpl page2Service;
    /**
     * @author ljc
     * Page2Controller.getWareHouse()
     * 查询仓库信息
     * @date 2022/4/6~16:38
     * @param token token
     * @return com.example.pojo.response.ResponsePage2
     */
    @GetMapping("/wareHouse")
    public ResponsePage2 getWareHouse(@RequestHeader(value = "token") String token){
        return page2Service.getWareHouse(JwtUtil.preCheckJwt(token));
    }
    /**
     * @author ljc
     * @desciption Page2Controller.insertWareHouse()
     * 新增仓库
     * @date 2022/4/6~16:40
     * @param token token
     * @param page2Insert 插入仓库信息
     * @return boolean
     */
    @PostMapping("/newWareHouse")
    public boolean insertWareHouse(@RequestHeader(value = "token") String token,@RequestBody Page2Insert page2Insert){
        return page2Service.insertWareHouse(JwtUtil.preCheckJwt(token),page2Insert);
    }
}
