package com.example.mapper;

import com.example.pojo.dao.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Page1Mapper {
    @Select("select * from userinfo where account =#{account} limit 1")
    UserInfo selectUserInfo(@Param("account") String account);
}
