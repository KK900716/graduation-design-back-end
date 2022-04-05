package com.example.mapper;

import com.example.pojo.dao.Page1UpdateDomain;
import com.example.pojo.response.ResponsePage1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 44380
 */
@Mapper
public interface Page1Mapper {
    /**
     * 通过账户查询账户信息
     * @param account 账户
     * @return 账户信息
     */
    @Select("select account,password,name,permission,balance,available from userinfo where account =#{account} limit 1")
    ResponsePage1 selectUserInfo(@Param("account") String account);

    /**
     * 更新账户登录名和用户姓名
     * @param page1UpdateDomain 封装的账户信息
     * @return 更新结果
     */
    @Update("update userInfo set account=#{account},name=#{name} where account=#{oldAccount}")
    int updateUserInfo(Page1UpdateDomain page1UpdateDomain);
}
