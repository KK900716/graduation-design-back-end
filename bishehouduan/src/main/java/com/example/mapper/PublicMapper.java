package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author ljc
 */
@Mapper
public interface PublicMapper {
    /**
     * com.example.mapper.PublicMapper.selectId():
     * 查询用户id
     * @author ljc
     * @date 2022/4/6~17:56
     * @param account 用户名
     * @return int
     */
    @Select("select id from userinfo where account=#{account} limit 1")
    int selectId(@Param("account") String account);
}
