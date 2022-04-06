package com.example.Service.Impl;

import com.example.Service.Page1Service;
import com.example.mapper.LoginMapper;
import com.example.mapper.Page1Mapper;
import com.example.pojo.dao.Page1UpdateDomain;
import com.example.pojo.response.ResponsePage1;
import com.example.pojo.response.ResponsePate1Update;
import com.example.pojo.resquest.Page1Update;
import com.example.utils.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @author ljc
 */
@Service
public class Page1ServiceImpl implements Page1Service {
    @Resource
    private Page1Mapper page1Mapper;
    @Resource
    private LoginMapper loginMapper;
    @Override
    public ResponsePage1 getInfo(String account) {
        return page1Mapper.selectUserInfo(account);
    }
    @Override
    public ResponsePate1Update updateUserInfo(String preCheckJwt, Page1Update page1Update) {
        page1Update.setAccount(page1Update.getAccount().trim());
        int res;
        String password;
        try {
            res=page1Mapper.updateUserInfo(new Page1UpdateDomain(
                    page1Update.getAccount(),
                    preCheckJwt,
                    page1Update.getName()
                    ));
            password= loginMapper.selectUserLogin(page1Update.getAccount());
        } catch (Exception e) {
            return new ResponsePate1Update();
        }
        if (res==1) {
            return new ResponsePate1Update(true,JwtUtil.createToken(page1Update.getAccount(),password));
        }
        return new ResponsePate1Update();
    }
}
