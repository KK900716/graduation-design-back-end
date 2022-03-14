package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PublicMapper {
    @Select("select id from userinfo where account=#{account} limit 1")
    int selectId(@Param("account") String account);
}
