package com.example.service;

import com.example.pojo.resquest.UserData;


/**
 * @author ljc
 */
public interface LoginService {
    /**
     * com.example.Service.LoginService
     * 验证用户登录信息
     * @author ljc
     * @date 2022/4/6~17:16
     * @param userData 用户信息
     * @return boolean
     */
    boolean userDataVerification(UserData userData);
    /**
     * com.example.Service.LoginService.insertUidAndVerification
     * 存储临时的uuid以验证用户身份
     * @author ljc
     * @date 2022/4/6~17:24
     * @param uuid uuid
     * @param checkCode checkCode
     */
    void insertUidAndVerification(String uuid, String checkCode);

}
