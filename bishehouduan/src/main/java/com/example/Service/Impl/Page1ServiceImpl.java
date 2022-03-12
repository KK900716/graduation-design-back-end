package com.example.Service.Impl;

import com.example.Service.Page1Service;
import com.example.mapper.Page1Mapper;
import com.example.pojo.dao.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class Page1ServiceImpl implements Page1Service {
    @Resource
    private Page1Mapper page1Mapper;
    @Override
    public UserInfo getInfo(String account) {
        return page1Mapper.selectUserInfo(account);
    }
}
