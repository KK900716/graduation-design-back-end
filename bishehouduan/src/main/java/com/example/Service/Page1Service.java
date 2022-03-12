package com.example.Service;

import com.example.pojo.response.ResponsePage1;
import com.example.pojo.response.ResponsePate1Update;
import com.example.pojo.resquest.Page1Update;

public interface Page1Service {
    ResponsePage1 getInfo(String account);
    ResponsePate1Update updateUserInfo(String preCheckJwt, Page1Update page1Update);
}
