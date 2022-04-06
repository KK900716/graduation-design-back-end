package com.example.Service;

import com.example.pojo.resquest.UpdatePassword;

public interface Page4Service {
    /**
     * com.example.Service.Page4Service.updatePassword():
     * 更新密码
     * @author ljc
     * @date 2022/4/6~17:41
     * @param account 用户名
     * @param updatePassword 更新密码
     * @return boolean
     */
    boolean updatePassword(String account, UpdatePassword updatePassword);
}
