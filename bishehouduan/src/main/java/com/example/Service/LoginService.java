package com.example.Service;

import com.example.pojo.resquest.UserData;


public interface LoginService {

    boolean userDataVerification(UserData userData);

    void insertUidAndVerification(String uuid, String checkCode);
}
