package com.example.Service.Impl;

import com.example.Service.Page4Service;
import com.example.mapper.Page4Mapper;
import com.example.pojo.dao.UpdatePasswordDomain;
import com.example.pojo.resquest.UpdatePassword;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @author ljc
 */
@Service
public class Page4ServiceImpl implements Page4Service {
    @Resource
    Page4Mapper page4Mapper;
    @Override
    public boolean updatePassword(String account, UpdatePassword updatePassword) {
        return 1==page4Mapper.updatePassword(new UpdatePasswordDomain(
                account,updatePassword.getOldPassword(),updatePassword.getNewPassword()
        ));
    }
}
