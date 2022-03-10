package com.example.mapper;

import com.example.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoginMapper {
    @Select("select * from userInfo")
    List<UserInfo> selectUserInfo();
}
