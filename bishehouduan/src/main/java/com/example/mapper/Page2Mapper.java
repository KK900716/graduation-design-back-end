package com.example.mapper;

import com.example.pojo.dao.Page2Insert2;
import com.example.pojo.dao.Page2InsertDomain;
import com.example.pojo.response.ResponsePage2;
import org.apache.ibatis.annotations.*;

@Mapper
public interface Page2Mapper {
    @Select("select available,capacity from userInfo where account=#{account} limit 1")
    ResponsePage2 getWareHouse(@Param("account") String account);
    @Update("update userinfo set available=#{available} where account=#{account}")
    int updateUserInfo(Page2InsertDomain page2InsertDomain);
    @Insert("insert into userwarehouse values (null,#{name},#{count},#{available},#{remaining},(select id from userInfo where account=#{account}))")
    int insertWareHouse(Page2Insert2 page2Insert2);
}
