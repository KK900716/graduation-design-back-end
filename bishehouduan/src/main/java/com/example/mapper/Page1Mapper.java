package com.example.mapper;

import com.example.pojo.dao.Page1UpdateDomain;
import com.example.pojo.response.ResponsePage1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Page1Mapper {
    @Select("select account,password,name,permission,balance,available from userinfo where account =#{account} limit 1")
    ResponsePage1 selectUserInfo(@Param("account") String account);
    @Update("update userInfo set account=#{account},name=#{name} where account=#{oldAccount}")
    int updateUserInfo(Page1UpdateDomain page1UpdateDomain);
}
