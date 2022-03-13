package com.example.controller;

import com.example.Service.Impl.Page3ServiceImpl;
import com.example.pojo.response.ResponsePage3;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    @PostMapping("/upload")
    public boolean upload(MultipartFile file){
        return page3Service.upload(file);
    }
}
