package com.example.mapper;

import com.example.pojo.dao.*;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.WareHouseNameAndAccount;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ljc
 */
@Mapper
public interface Page3Mapper {
    /**
     * com.example.mapper.Page3Mapper
     * 查询仓库列表
     * @author ljc
     * @date 2022/4/6~17:05
     * @param account 用户名
     * @return java.util.List<com.example.pojo.response.ResponsePage3>
     */
    @Select("select userWareHouse.name,count,userWareHouse.available,remaining from userWareHouse inner join  userInfo on userInfo.id=userWareHouse.userInfo_id where account=#{account}")
    List<ResponsePage3> selectWareHouseList(@Param("account") String account);
    /**
     * com.example.mapper.Page3Mapper.selectHasWareHouse():
     * 查询是否存在指定的仓库
     * @author ljc
     * @date 2022/4/6~17:49
     * @param wareHouseNameAndAccount 指定的信息
     * @return int
     */
    @Select("select id from userWareHouse where name=#{name} and userInfo_id=(select id from userInfo where account=#{account})")
    int selectHasWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);
    /**
     * com.example.mapper.Page3Mapper.selectAvailable():
     * 查询指定用户名的仓库可用空间
     * @author ljc
     * @date 2022/4/6~17:50
     * @param account 用户名
     * @return int
     */
    @Select("select available from userInfo where #{account}")
    int selectAvailable(@Param("account") String account);
    /**
     * com.example.mapper.Page3Mapper.deleteWareHouseUserInfo():
     * 更新仓库信息
     * @author ljc
     * @date 2022/4/6~17:50
     * @param deleteWareHouseUserInfoDomain 更新的信息
     * @return int
     */
    @Update("update userInfo set available=#{available} where account=#{account}")
    int deleteWareHouseUserInfo(DeleteWareHouseUserInfoDomain deleteWareHouseUserInfoDomain);
    /**
     * com.example.mapper.Page3Mapper.deleteWareHouseUserWareHouse():
     * 删除仓库
     * @author ljc
     * @date 2022/4/6~17:51
     * @param wareHouseNameAndAccount 删除仓库
     * @return int
     */
    @Delete("delete from userWareHouse where name=#{name} and userInfo_id=(select id from userInfo where account=#{account})")
    int deleteWareHouseUserWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);
    /**
     * com.example.mapper.Page3Mapper.selectWareHouse():
     * 查询仓库信息
     * @author ljc
     * @date 2022/4/6~17:51
     * @param wareHouseNameAndAccount 指定的仓库信息
     * @return com.example.pojo.response.ResponsePage3Context
     */
    @Select("select userwarehouse.name,count,userwarehouse.available,remaining from userwarehouse,userinfo where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{name}")
    ResponsePage3Context selectWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);
    /**
     * com.example.mapper.Page3Mapper.updateWareHouseMessage():
     * 更新仓库信息
     * @author ljc
     * @date 2022/4/6~18:07
     * @param updateWareHouseMessageDomain 更新的信息
     * @return int
     */
    @Update("update userwarehouse,userinfo set userwarehouse.name=#{newWareHouseName} where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{oldWareHouseName}")
    int updateWareHouseMessage(UpdateWareHouseMessageDomain updateWareHouseMessageDomain);
    /**
     * com.example.mapper.Page3Mapper.selectWareHouseAvailable():
     * 查询指定仓库的信息
     * @author ljc
     * @date 2022/4/6~17:53
     * @param wareHouseNameAndAccount 指定的信息
     * @return com.example.pojo.dao.UserWareHouse
     */
    @Select("select userwarehouse.id,userwarehouse.name,userwarehouse.count,userwarehouse.available,remaining,userwarehouse.userInfo_id from userwarehouse,userinfo where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{name}")
    @Results({
            @Result(property = "userInfoId",column = "userwarehouse.userInfo_id")
    })
    UserWareHouse selectWareHouseAvailable(WareHouseNameAndAccount wareHouseNameAndAccount);
    /**
     * com.example.mapper.Page3Mapper.insertWarehouse():
     * 插入图片信息
     * @author ljc
     * @date 2022/4/6~17:53
     * @param wareHouse 图片信息
     * @return boolean
     */
    @Insert("insert into warehouse values (#{id},#{userWarehouseId},#{state},0)")
    boolean insertWarehouse(WareHouse wareHouse);
    /**
     * com.example.mapper.Page3Mapper.updateUserWareHouse():
     * 更新仓库可用信息
     * @author ljc
     * @date 2022/4/6~17:54
     * @param userWareHouse 更新的信息
     * @return boolean
     */
    @Update("update userwarehouse set available=#{available},remaining=#{remaining} where id=#{id}")
    boolean updateUserWareHouse(UserWareHouse userWareHouse);
    /**
     * com.example.mapper.Page3Mapper.deleteWareHouse():
     * 删除仓库
     * @author ljc
     * @date 2022/4/6~17:54
     * @param wareHouseNameAndAccount 删除的仓库信息
     */
    @Delete("delete from wareHouse where userWareHouse_id=(select userwarehouse.id from userwarehouse,userinfo where account=#{account} and userwarehouse.name=#{name})")
    void deleteWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);
    /**
     * com.example.mapper.Page3Mapper.updateScore():
     * 更新分数
     * @author ljc
     * @date 2022/4/6~17:55
     * @param updateScore 分数信息
     * @return boolean
     */
    @Update("update warehouse set score=#{score} where id=#{id}")
    boolean updateScore(UpdateScore updateScore);
}
