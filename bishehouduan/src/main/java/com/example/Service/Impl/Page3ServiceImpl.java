package com.example.Service.Impl;

import com.example.Service.Page3Service;
import com.example.mapper.Page3Mapper;
import com.example.pojo.dao.DeleteWareHouseUserInfoDomain;
import com.example.pojo.dao.UpdateWHMessageDomain;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.Page3Delete;
import com.example.pojo.resquest.UpdateWHMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class Page3ServiceImpl implements Page3Service {
    @Resource
    Page3Mapper page3Mapper;
    @Override
    public List<ResponsePage3> selectWareHouseList(String account) {
        return page3Mapper.selectWareHouseList(account);
    }
    @Override
    public boolean deleteWareHouse(String account, String name) {
        Page3Delete page3Delete = new Page3Delete(account,name);
        try {
            page3Mapper.selectHasWareHouse(page3Delete);
        } catch (Exception e) {
            return false;
        }
        return 1==page3Mapper.deleteWareHouseUserInfo(new DeleteWareHouseUserInfoDomain(account,page3Mapper.selectAvailable(account)+1)) && 1==page3Mapper.deleteWareHouseUserWareHouse(page3Delete);
    }

    @Override
    public ResponsePage3Context getWareHouseMessage(String account, String name) {
        Page3Delete page3Delete = new Page3Delete(account,name);
        return page3Mapper.selectWareHouse(page3Delete);
    }
    @Override
    public boolean updateWHMessage(String account, UpdateWHMessage updateWHMessage) {
        try {
            page3Mapper.selectHasWareHouse(new Page3Delete(account,updateWHMessage.getNewWHName()));
        } catch (Exception e1) {
            try {
                page3Mapper.selectHasWareHouse(new Page3Delete(account,updateWHMessage.getOldWHName()));
                return 1==page3Mapper.updateWHMessage(new UpdateWHMessageDomain(
                        account,
                        updateWHMessage.getOldWHName(),
                        updateWHMessage.getNewWHName()
                ));
            } catch (Exception e2) {
                return false;
            }
        }
        return false;
    }
    @Override
    public boolean upload(MultipartFile file) {
        String originName=file.getOriginalFilename();
//        if (!originName.endsWith(".png")&&!originName.endsWith(".jpg")&&!originName.endsWith(".jpeg")){
//            return false;
//        }
        String newName= UUID.randomUUID().toString()+".png";
        try {
            file.transferTo(new File("D:\\360MoveData\\Users\\44380\\Desktop\\毕设后端\\bishehouduan\\src\\main\\resources\\warehouse",newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }




}
