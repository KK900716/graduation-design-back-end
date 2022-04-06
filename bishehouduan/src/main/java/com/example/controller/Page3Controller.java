package com.example.controller;

import com.example.Service.Impl.Page3ServiceImpl;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.UpdateWareHouseMessage;
import com.example.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 44380
 */
@RestController
public class Page3Controller {
    @Resource
    Page3ServiceImpl page3Service;
    /**
     * @author ljc
     * @desciption Page3Controller.selectWareHouseList()
     * 查询仓库列表
     * @date 2022/4/6~16:42
     * @param token token
     * @return java.util.List<com.example.pojo.response.ResponsePage3>
     */
    @GetMapping("/viewWareHouse")
    public List<ResponsePage3> selectWareHouseList(@RequestHeader String token){
        return page3Service.selectWareHouseList(JwtUtil.preCheckJwt(token));
    }
    /**
     * @author ljc
     * @desciption Page3Controller.deleteWareHouse()
     * 删除指定仓库
     * @date 2022/4/6~16:42
     * @param token token
     * @param name 仓库名
     * @return boolean
     */
    @DeleteMapping("/delete")
    public boolean deleteWareHouse(@RequestHeader String token,String name){
        return page3Service.deleteWareHouse(JwtUtil.preCheckJwt(token),name);
    }
    /**
     * @author ljc
     * @desciption Page3Controller.getWareHouseMessage()
     * 查询仓库信息
     * @date 2022/4/6~16:43
     * @param token token
     * @param name 仓库名
     * @return com.example.pojo.response.ResponsePage3Context
     */
    @GetMapping("/getWareHouse")
    public ResponsePage3Context getWareHouseMessage(@RequestHeader String token, String name){
        return page3Service.getWareHouseMessage(JwtUtil.preCheckJwt(token),name);
    }
    /**
     * @author ljc
     * @desciption Page3Controller.updateWareHouseMessage()
     * 更新仓库信息
     * @date 2022/4/6~16:45
     * @param token token
     * @param updateWareHouseMessage 前端返回的仓库信息
     * @return boolean
     */
    @PutMapping("/updateWHMessage")
    public boolean updateWareHouseMessage(@RequestHeader String token, @RequestBody UpdateWareHouseMessage updateWareHouseMessage){
        return page3Service.updateWareHouseMessage(JwtUtil.preCheckJwt(token), updateWareHouseMessage);
    }
    /**
     * @author ljc
     * @desciption Page3Controller.upload()
     * 上传文件
     * @date 2022/4/6~16:46
     * @param token token
     * @param file 文件
     * @param name 仓库名
     * @return boolean
     */
    @PostMapping("/upload")
    public boolean upload(@RequestHeader String token, MultipartFile file,String name){
        return page3Service.upload(JwtUtil.preCheckJwt(token),file,name);
    }
    /**
     * @author ljc
     * @desciption Page3Controller.refresh()
     * 返回仓库图片的URL
     * @date 2022/4/6~16:46
     * @param token token
     * @param name 仓库名
     * @return java.util.List<java.lang.String>
     */
    @GetMapping("/refresh")
    public List<String> refresh(@RequestHeader String token,String name){
        return page3Service.refresh(JwtUtil.preCheckJwt(token),name);
    }
    /**
     * @author ljc
     * @desciption Page3Controller.getImg()
     * 返回图片
     * @date 2022/4/6~16:47
     * @param account 用户名
     * @param id 图片id
     * @param name 仓库名
     * @param response response
     */
    @GetMapping("/getImg")
    public void getImg(String account,String id,String name,HttpServletResponse response){
        page3Service.getImg(account,id,name,response);
    }
    /**
     * @author ljc
     * @desciption Page3Controller.getScore()
     * 给图片打分
     * @date 2022/4/6~16:48
     * @param token token
     * @param score 分数
     * @param name 仓库名
     * @param id 图片id
     * @return boolean
     */
    @GetMapping("/submitScore")
    public boolean getScore(@RequestHeader String token,@RequestParam int score,@RequestParam String name,@RequestParam String id){
        return page3Service.getScore(JwtUtil.preCheckJwt(token),score,name,id);
    }
}
