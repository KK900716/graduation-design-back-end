package com.example.Service.Impl;

import com.example.Service.Page3Service;
import com.example.mapper.Page3Mapper;
import com.example.pojo.response.ResponsePage3;
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
}
