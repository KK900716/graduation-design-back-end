package com.example.controller;

import com.example.Service.Impl.Page3ServiceImpl;
import com.example.pojo.response.ResponsePage3;
import com.example.utils.JwtUtil;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Page3Controller {
    @Resource
    Page3ServiceImpl page3Service;
    @GetMapping("/viewWareHouse")
    public List<ResponsePage3> selectWareHouseList(@RequestHeader String token){
        return page3Service.selectWareHouseList(JwtUtil.preCheckJwt(token));
    }
    @DeleteMapping("/delete")
    public boolean deleteWareHouse(@RequestHeader String token,String name){
        return page3Service.deleteWareHouse(JwtUtil.preCheckJwt(token),name);
    }
}
