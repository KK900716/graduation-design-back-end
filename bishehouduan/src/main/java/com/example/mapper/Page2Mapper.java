package com.example.mapper;

import com.example.pojo.dao.Page2Insert2;
import com.example.pojo.dao.Page2InsertDomain;
import com.example.pojo.response.ResponsePage2;
import org.apache.ibatis.annotations.*;

/**
 * @author 44380
 */
@Mapper
public interface Page2Mapper {
    /**
     * 获得仓库信息
     * @author ljc
     * @desciption Page2Mapper.getWareHouse()
     * @date 2022/4/6~16:50
     * @param account 账户
     * @return com.example.pojo.response.ResponsePage2
     */
    @Select("select available,capacity from userInfo where account=#{account} limit 1")
    ResponsePage2 getWareHouse(@Param("account") String account);
    /**
     * 更新仓库信息
     * @author ljc
     * @desciption Page2Mapper.updateUserInfo()
     * @date 2022/4/6~16:50
     * @param page2InsertDomain 要更新的仓库信息
     * @return int
     */
    @Update("update userinfo set available=#{available},balance=#{balance} where account=#{account}")
    int updateUserInfo(Page2InsertDomain page2InsertDomain);
    /**
     * Page2Mapper.insertWareHouse()
     * 插入仓库
     * @author ljc
     * @date 2022/4/6~16:52
     * @param page2Insert2 插入的数据
     * @return int
     */
    @Insert("insert into userwarehouse values (null,#{name},#{count},#{available},#{remaining},(select id from userInfo where account=#{account}))")
    int insertWareHouse(Page2Insert2 page2Insert2);
    /**
     * com.example.mapper.Page2Mapper
     *
     * @author 44380
     * @date 2022/4/6~16:59
     * @param account 账户信息
     * @return float
     */
    @Select("select balance from userinfo where account=#{account}")
    float selectBalance(@Param("account") String account);
}
