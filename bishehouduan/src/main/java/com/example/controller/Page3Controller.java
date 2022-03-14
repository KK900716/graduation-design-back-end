package com.example.controller;

import com.example.Service.Impl.Page3ServiceImpl;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.UpdateWHMessage;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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
    @GetMapping("/getWareHouse")
    public ResponsePage3Context getWareHouseMessage(@RequestHeader String token, String name){
        return page3Service.getWareHouseMessage(JwtUtil.preCheckJwt(token),name);
    }
    @PutMapping("/updateWHMessage")
    public boolean updateWHMessage(@RequestHeader String token,@RequestBody UpdateWHMessage updateWHMessage){
        return page3Service.updateWHMessage(JwtUtil.preCheckJwt(token),updateWHMessage);
    }
    @PostMapping("/upload")
    public boolean upload(@RequestHeader String token, MultipartFile file,String name){
        return page3Service.upload(JwtUtil.preCheckJwt(token),file,name);
    }
    @GetMapping("/refresh")
    public List<String> refresh(@RequestHeader String token,String name){
        return page3Service.refresh(JwtUtil.preCheckJwt(token),name);
    }
    @GetMapping("/getImg")
    public void getImg(String account,String id,String name,HttpServletResponse response){
        page3Service.getImg(account,id,name,response);
    }
}
