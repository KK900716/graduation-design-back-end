package com.example.Service;

import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.UpdateWHMessage;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Page3Service {
    List<ResponsePage3> selectWareHouseList(String account);
    @Transactional
    boolean deleteWareHouse(String account, String name);
    ResponsePage3Context getWareHouseMessage(String account, String name);
    @Transactional
    boolean upload(String account, MultipartFile file, String name);
    @Transactional
    boolean updateWHMessage(String account, UpdateWHMessage updateWHMessage);
}
