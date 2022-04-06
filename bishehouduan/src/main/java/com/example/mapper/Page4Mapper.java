package com.example.mapper;

import com.example.pojo.dao.UpdatePasswordDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author ljc
 */
@Mapper
public interface Page4Mapper {
    /**
     * com.example.mapper.Page4Mapper.updatePassword():
     * 更新密码
     * @author ljc
     * @date 2022/4/6~17:55
     * @param updatePasswordDomain 更新的密码信息
     * @return int
     */
    @Update("update userinfo set password=#{newPassword} where account=#{account} and password=#{oldPassword}")
    int updatePassword(UpdatePasswordDomain updatePasswordDomain);
}
