package com.example.Service;

import com.example.pojo.response.ResponsePage1;
import com.example.pojo.response.ResponsePate1Update;
import com.example.pojo.resquest.Page1Update;

/**
 * @author ljc
 */
public interface Page1Service {
    /**
     * com.example.Service.Page1Service.getInfo():
     *
     * @author ljc
     * @date 2022/4/6~17:26
     * @param account 用户名
     * @return com.example.pojo.response.ResponsePage1
     */
    ResponsePage1 getInfo(String account);
    /**
     * com.example.Service.Page1Service.updateUserInfo():
     * 更新用户信息
     * @author ljc
     * @date 2022/4/6~17:27
     * @param account 用户名
     * @param page1Update 更新的用户信息
     * @return com.example.pojo.response.ResponsePate1Update
     */
    ResponsePate1Update updateUserInfo(String account, Page1Update page1Update);
}
