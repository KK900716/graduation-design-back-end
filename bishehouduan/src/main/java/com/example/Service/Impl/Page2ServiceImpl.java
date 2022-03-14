package com.example.Service.Impl;

import com.example.Service.Page2Service;
import com.example.mapper.Page2Mapper;
import com.example.mapper.Page3Mapper;
import com.example.mapper.PublicMapper;
import com.example.pojo.dao.Page2Insert2;
import com.example.pojo.dao.Page2InsertDomain;
import com.example.pojo.response.ResponsePage2;
import com.example.pojo.resquest.Page2Insert;
import com.example.pojo.resquest.Page3Delete;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

@Service
public class Page2ServiceImpl implements Page2Service {
    @Resource
    private PublicMapper publicMapper;
    @Resource
    private Page2Mapper page2Mapper;
    @Resource
    private Page3Mapper page3Mapper;
    @Value("${user.basePath}")
    private String basePath;
    @Override
    public ResponsePage2 getWareHouse(String account) {
        return page2Mapper.getWareHouse(account);
    }

    @Override
    public boolean insertWareHouse(String account, Page2Insert page2Insert) {
//        获得仓库可用数量
        ResponsePage2 wareHouse = page2Mapper.getWareHouse(account);
//        如果可用数量大于零
        if (wareHouse.getAvailable()>0){
            try {
//                尝试寻找有没有这样的仓库，有捕获异常返回false
                page3Mapper.selectHasWareHouse(new Page3Delete(
                        account,
                        page2Insert.getName()
                ));
            } catch (Exception e) {
//                没有继续执行，先更新仓库标志数据
                int x=page2Mapper.updateUserInfo(new Page2InsertDomain(
                        account,
                        wareHouse.getAvailable()-1
                ));
                if (x==1){
//                    插入新仓库
                    int i = page2Mapper.insertWareHouse(new Page2Insert2(
                            account,
                            page2Insert.getName()
                    ));
//                    插入成功则创建目录
                    if (i==1){
                        File folder=new File(basePath+"/"+publicMapper.selectId(account)+"/"+page3Mapper.selectHasWareHouse(
                                new Page3Delete(
                                        account,
                                        page2Insert.getName()
                                )
                        ));
                        if (!folder.exists())
                            folder.mkdir();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
