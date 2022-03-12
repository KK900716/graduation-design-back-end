package com.example.Service;

import com.example.pojo.response.ResponsePage2;
import com.example.pojo.resquest.Page2Insert;
import org.springframework.transaction.annotation.Transactional;

public interface Page2Service {
    ResponsePage2 getWareHouse(String account);
    @Transactional
    boolean insertWareHouse(String preCheckJwt, Page2Insert page2Insert);
}
