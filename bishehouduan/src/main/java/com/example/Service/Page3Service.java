package com.example.Service;

import com.example.pojo.response.ResponsePage3;

import java.util.List;

public interface Page3Service {
    List<ResponsePage3> selectWareHouseList(String account);
}
