package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    /**
     * @param account 账户
     * @return password 密码
     */
    @Select("select password from userInfo where account=#{account} limit 1")
    String selectUserLogin(@Param("account") String account);
}
