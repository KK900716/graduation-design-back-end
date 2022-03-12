package com.example.Service.Impl;

import com.example.Service.Page2Service;
import com.example.mapper.Page2Mapper;
import com.example.mapper.Page3Mapper;
import com.example.pojo.dao.Page2Insert2;
import com.example.pojo.dao.Page2InsertDomain;
import com.example.pojo.response.ResponsePage2;
import com.example.pojo.resquest.Page2Insert;
import com.example.pojo.resquest.Page3Delete;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Page2ServiceImpl implements Page2Service {
    @Resource
    Page2Mapper page2Mapper;
    @Resource
    Page3Mapper page3Mapper;
    @Override
    public ResponsePage2 getWareHouse(String account) {
        return page2Mapper.getWareHouse(account);
    }

    @Override
    public boolean insertWareHouse(String account, Page2Insert page2Insert) {
        ResponsePage2 wareHouse = page2Mapper.getWareHouse(account);
        if (wareHouse.getAvailable()>0){
            try {
                page3Mapper.selectHasWareHouse(new Page3Delete(
                        account,
                        page2Insert.getName()
                ));
            } catch (Exception e) {
                int x=page2Mapper.updateUserInfo(new Page2InsertDomain(
                        account,
                        wareHouse.getAvailable()-1
                ));
                if (x==1){
                    return 1==page2Mapper.insertWareHouse(new Page2Insert2(
                            account,
                            page2Insert.getName()
                    ));
                }
            }
        }
        return false;
    }
}
