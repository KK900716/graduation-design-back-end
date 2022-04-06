package com.example.Service.Impl;

import com.example.Service.Page2Service;
import com.example.mapper.Page2Mapper;
import com.example.mapper.Page3Mapper;
import com.example.mapper.PublicMapper;
import com.example.pojo.dao.Page2Insert2;
import com.example.pojo.dao.Page2InsertDomain;
import com.example.pojo.response.ResponsePage2;
import com.example.pojo.resquest.Page2Insert;
import com.example.pojo.resquest.WareHouseNameAndAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author ljc
 */
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
    @Value("${user.newPath}")
    private String newPath;
    @Override
    public ResponsePage2 getWareHouse(String account) {
        return page2Mapper.getWareHouse(account);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean insertWareHouse(String account, Page2Insert page2Insert) {
//        获得仓库可用数量
        ResponsePage2 wareHouse = page2Mapper.getWareHouse(account);
//        如果可用数量大于零
        if (wareHouse.getAvailable()>0){
            try {
//                尝试寻找有没有这样的仓库，有捕获异常返回false
                page3Mapper.selectHasWareHouse(new WareHouseNameAndAccount(
                        account,
                        page2Insert.getName()
                ));
            } catch (Exception e) {
//                没有继续执行，先更新仓库标志数据
//                查询余额
                float balance=page2Mapper.selectBalance(account);
                if (balance<=1) {
                    return false;
                }
//                更新仓库标志位
                int x=page2Mapper.updateUserInfo(new Page2InsertDomain(
                        account,
                        wareHouse.getAvailable()-1,
                        balance-1
                ));
                if (x==1){
//                    插入新仓库
                    int i = page2Mapper.insertWareHouse(new Page2Insert2(
                            account,
                            page2Insert.getName()
                    ));
//                    插入成功则创建目录
                    if (i==1){
                        String path="/"+publicMapper.selectId(account)+"/"+page3Mapper.selectHasWareHouse(
                                new WareHouseNameAndAccount(
                                        account,
                                        page2Insert.getName()
                                )
                        );
                        File folder=new File(basePath+path);
                        File newFolder=new File(newPath+path);
                        if (!folder.exists()) {
                            folder.mkdirs();
                        }
                        if (!newFolder.exists()) {
                            newFolder.mkdirs();
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
