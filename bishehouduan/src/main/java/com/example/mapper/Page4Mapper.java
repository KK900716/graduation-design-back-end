package com.example.mapper;

import com.example.pojo.dao.UpdatePasswordDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface Page4Mapper {
    @Update("update userinfo set password=#{newPassword} where account=#{account} and password=#{oldPassword}")
    int updatePassword(UpdatePasswordDomain updatePasswordDomain);
}
