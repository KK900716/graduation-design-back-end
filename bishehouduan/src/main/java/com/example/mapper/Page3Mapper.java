package com.example.mapper;

import com.example.pojo.dao.*;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.WareHouseNameAndAccount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Page3Mapper {
    @Select("select userWareHouse.name,count,userWareHouse.available,remaining from userWareHouse inner join  userInfo on userInfo.id=userWareHouse.userInfo_id where account=#{account}")
    List<ResponsePage3> selectWareHouseList(@Param("account") String account);

    @Select("select id from userWareHouse where name=#{name} and userInfo_id=(select id from userInfo where account=#{account})")
    int selectHasWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);

    @Select("select available from userInfo where #{account}")
    int selectAvailable(@Param("account") String account);

    @Update("update userInfo set available=#{available} where account=#{account}")
    int deleteWareHouseUserInfo(DeleteWareHouseUserInfoDomain deleteWareHouseUserInfoDomain);

    @Delete("delete from userWareHouse where name=#{name} and userInfo_id=(select id from userInfo where account=#{account})")
    int deleteWareHouseUserWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);

    @Select("select userwarehouse.name,count,userwarehouse.available,remaining from userwarehouse,userinfo where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{name}")
    ResponsePage3Context selectWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);
    @Update("update userwarehouse,userinfo set userwarehouse.name=#{newWHName} where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{oldWHName}")
    int updateWHMessage(UpdateWHMessageDomain updateWHMessageDomain);
    @Select("select userwarehouse.id,userwarehouse.name,userwarehouse.count,userwarehouse.available,remaining,userwarehouse.userInfo_id from userwarehouse,userinfo where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{name}")
    UserWareHouse selectWareHouseAvailable(WareHouseNameAndAccount wareHouseNameAndAccount);
    @Insert("insert into warehouse values (#{id},#{userWarehouse_id},#{state},0)")
    boolean insertWarehouse(WareHouse wareHouse);
    @Update("update userwarehouse set available=#{available},remaining=#{remaining} where id=#{id}")
    boolean updateUserWareHouse(UserWareHouse userWareHouse);
    @Delete("delete from wareHouse where userWareHouse_id=(select userwarehouse.id from userwarehouse,userinfo where account=#{account} and userwarehouse.name=#{name})")
    void deleteWareHouse(WareHouseNameAndAccount wareHouseNameAndAccount);
    @Update("update warehouse set score=#{score} where id=#{id}")
    boolean updateScore(UpdateScore updateScore);
}
