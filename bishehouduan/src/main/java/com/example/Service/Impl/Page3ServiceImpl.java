package com.example.Service.Impl;

import com.example.Service.Page3Service;
import com.example.mapper.Page3Mapper;
import com.example.mapper.PublicMapper;
import com.example.pojo.dao.DeleteWareHouseUserInfoDomain;
import com.example.pojo.dao.UpdateWHMessageDomain;
import com.example.pojo.dao.UserWareHouse;
import com.example.pojo.dao.WareHouse;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.WareHouseNameAndAccount;
import com.example.pojo.resquest.UpdateWHMessage;
import org.springframework.beans.factory.annotation.Value;
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
    private PublicMapper publicMapper;
    @Resource
    Page3Mapper page3Mapper;
    @Value("${user.basePath}")
    private String basePath;
    @Value("${user.newPath}")
    private String newPath;
    @Override
    public List<ResponsePage3> selectWareHouseList(String account) {
        return page3Mapper.selectWareHouseList(account);
    }
    @Override
    public boolean deleteWareHouse(String account, String name){
        WareHouseNameAndAccount wareHouseNameAndAccount = new WareHouseNameAndAccount(account,name);
        int i;
        try {
//            验证有没有当前仓库
            i = page3Mapper.selectHasWareHouse(wareHouseNameAndAccount);
        } catch (Exception e) {
            return false;
        }
//        TODO 删除这个仓库的外键约束
        if (!page3Mapper.deleteWareHouse(wareHouseNameAndAccount))
            return false;
        if (1==page3Mapper.deleteWareHouseUserInfo(new DeleteWareHouseUserInfoDomain(account,page3Mapper.selectAvailable(account)+1)) && 1==page3Mapper.deleteWareHouseUserWareHouse(wareHouseNameAndAccount)){
//            删除仓库成功
//            删除仓库所在文件夹
            String path="/"+publicMapper.selectId(account)+"/"+i;
            File folder=new File(basePath+path);
            File newFolder=new File(newPath+path);
            return delete(folder)&&delete(newFolder);
        }
        return false;
    }
    private boolean delete(File folder){
        String[] list = folder.list();
        File file=null;
        for (String l:list) {
            file=new File(folder+"/"+l);
            file.delete();
        }
        return folder.delete();
    }
    @Override
    public ResponsePage3Context getWareHouseMessage(String account, String name) {
        WareHouseNameAndAccount wareHouseNameAndAccount = new WareHouseNameAndAccount(account,name);
        return page3Mapper.selectWareHouse(wareHouseNameAndAccount);
    }
    @Override
    public boolean updateWHMessage(String account, UpdateWHMessage updateWHMessage) {
        try {
            page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(account,updateWHMessage.getNewWHName()));
        } catch (Exception e1) {
            try {
                page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(account,updateWHMessage.getOldWHName()));
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
    public boolean upload(String account, MultipartFile file, String name) {
        String originName=file.getOriginalFilename();
//        验证上传文件是否正确
        if (!originName.endsWith(".png")&&!originName.endsWith(".jpg")&&!originName.endsWith(".jpeg")){
            return false;
        }
        UserWareHouse userWareHouse=page3Mapper.selectWareHouseAvailable(new WareHouseNameAndAccount(account,name));
        //        查看仓库容量
        if (userWareHouse.getRemaining()<=0)
            return false;
//        插入图片到数据库
        String id = UUID.randomUUID().toString();
        if (!page3Mapper.insertWarehouse(new WareHouse(id,userWareHouse.getId())))
            return false;
        String newName= id +".png";
//        存储图片
        try {
            file.transferTo(new File(basePath+"/"+userWareHouse.getUserInfo_id()+"/"+userWareHouse.getId(),newName));
        } catch (IOException e) {
            return false;
        }
//        回写数据
        return page3Mapper.updateUserWareHouse(new UserWareHouse(
                userWareHouse.getId(),
                userWareHouse.getName(),
                userWareHouse.getCount(),
                userWareHouse.getAvailable()+1,
                userWareHouse.getRemaining()-1,
                userWareHouse.getUserInfo_id()
        ));
    }
}
