package com.example.Service.Impl;

import com.example.Service.Page3Service;
import com.example.mapper.Page3Mapper;
import com.example.pojo.dao.DeleteWareHouseUserInfoDomain;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.resquest.Page3Delete;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

}
