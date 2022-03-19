package com.example.Service.Impl;

import com.example.Service.Page3Service;
import com.example.async.AsyncTaskService;
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
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Page3ServiceImpl implements Page3Service {
    @Resource
    AsyncTaskService asyncTaskService;
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
        page3Mapper.deleteWareHouse(wareHouseNameAndAccount);
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
        File file;
        boolean delete;
        if (list!=null){
            for (String l:list) {
                file=new File(folder+"/"+l);
                delete = file.delete();
                if (!delete)
                    return false;
            }
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
        if (originName==null)
            return false;
        else if (!originName.endsWith(".png")&&!originName.endsWith(".jpg")&&!originName.endsWith(".jpeg")){
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
        File newFile = new File(basePath + "/" + userWareHouse.getUserInfo_id() + "/" + userWareHouse.getId(), newName);
//        存储图片
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            return false;
        }
//        启动图像识别算法
        asyncTaskService.dealWithImg(newFile.getPath());
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
    @Override
    public List<String> refresh(String account, String name) {
        List<String> list=new ArrayList<>();
//        TODO 此处需要将basePath改成newPath
        String path=basePath+"/"+publicMapper.selectId(account)+"/"+page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(account,name));
        File file=new File(path);
        String[] l = file.list();
        String path2="http://127.0.0.1:80/getImg?account="+account+"&name="+name+"&id=";
        if (l==null)
            return null;
        for (String s : l) {
            list.add(path2 + "/" + s);
        }
        return list;
    }

    @Override
    public void getImg(String account, String id,String name, HttpServletResponse response) {
        String path=basePath+"/"+publicMapper.selectId(account)+"/"+page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(account,name));
        File file=new File(path+"/"+id);
        FileInputStream fileInputStream=null;
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            fileInputStream=new FileInputStream(file);
            IOUtils.copy(fileInputStream,outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream!=null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
