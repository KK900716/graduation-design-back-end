package com.example.service.Impl;

import com.example.service.Page3Service;
import com.example.async.AsyncTaskService;
import com.example.mapper.Page3Mapper;
import com.example.mapper.PublicMapper;
import com.example.pojo.dao.*;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.WareHouseNameAndAccount;
import com.example.pojo.resquest.UpdateWareHouseMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

/**
 * @author ljc
 */
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
    @Transactional(rollbackFor = Exception.class)
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
                if (!delete) {
                    return false;
                }
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
    @Transactional(rollbackFor = Exception.class)
    public boolean updateWareHouseMessage(String account, UpdateWareHouseMessage updateWareHouseMessage) {
        try {
            page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(account, updateWareHouseMessage.getNewWareHouseName()));
        } catch (Exception e1) {
            try {
                page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(account, updateWareHouseMessage.getOldWareHouseName()));
                return 1==page3Mapper.updateWareHouseMessage(new UpdateWareHouseMessageDomain(
                        account,
                        updateWareHouseMessage.getOldWareHouseName(),
                        updateWareHouseMessage.getNewWareHouseName()
                ));
            } catch (Exception e2) {
                return false;
            }
        }
        return false;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean upload(String account, MultipartFile file, String name) {
        String originName=file.getOriginalFilename();
//        验证上传文件是否正确
        String png = ".png";
        String jpg = ".jpg";
        String jpeg = ".jpeg";
        if (originName==null) {
            return false;
        } else if (!originName.endsWith(png)&&!originName.endsWith(jpeg)&&!originName.endsWith(jpg)){
            return false;
        }
        UserWareHouse userWareHouse=page3Mapper.selectWareHouseAvailable(new WareHouseNameAndAccount(account,name));
        //        查看仓库容量
        if (userWareHouse.getRemaining()<=0) {
            return false;
        }
//        插入图片到数据库
        String id = UUID.randomUUID().toString();
        if (!page3Mapper.insertWarehouse(new WareHouse(id,userWareHouse.getId()))) {
            return false;
        }
        String newName= id +".png";
        File newFile = new File(basePath + "/" + userWareHouse.getUserInfoId() + "/" + userWareHouse.getId(), newName);
//        存储图片
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
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
                userWareHouse.getUserInfoId()
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
        if (l==null) {
            return null;
        }
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
                if (fileInputStream!=null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean getScore(String account, int score, String name, String id) {
        id=id.split("\\.")[0];
        System.out.println(id);
        id=id.substring(1);
        return page3Mapper.updateScore(new UpdateScore(
                id,
                score
        ));
    }
}