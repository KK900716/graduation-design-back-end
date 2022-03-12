package com.example.Service;

import com.example.pojo.dao.UserInfo;

public interface Page1Service {
    UserInfo getInfo(String preCheckJwt);
}
