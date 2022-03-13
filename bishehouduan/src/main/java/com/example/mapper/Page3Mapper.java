package com.example.mapper;

import com.example.pojo.dao.DeleteWareHouseUserInfoDomain;
import com.example.pojo.dao.UpdateWHMessageDomain;
import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.Page3Delete;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Page3Mapper {
    @Select("select userWareHouse.name,count,userWareHouse.available,remaining from userWareHouse inner join  userInfo on userInfo.id=userWareHouse.userInfo_id where account=#{account}")
    List<ResponsePage3> selectWareHouseList(@Param("account") String account);

    @Select("select id from userWareHouse where name=#{name} and userInfo_id=(select id from userInfo where account=#{account})")
    int selectHasWareHouse(Page3Delete page3Delete);

    @Select("select available from userInfo where #{account}")
    int selectAvailable(@Param("account") String account);

    @Update("update userInfo set available=#{available} where account=#{account}")
    int deleteWareHouseUserInfo(DeleteWareHouseUserInfoDomain deleteWareHouseUserInfoDomain);

    @Delete("delete from userWareHouse where name=#{name} and userInfo_id=(select id from userInfo where account=#{account})")
    int deleteWareHouseUserWareHouse(Page3Delete page3Delete);

    @Select("select userwarehouse.name,count,userwarehouse.available,remaining from userwarehouse,userinfo where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{name}")
    ResponsePage3Context selectWareHouse(Page3Delete page3Delete);
    @Update("update userwarehouse,userinfo set userwarehouse.name=#{newWHName} where userinfo.id=userwarehouse.userInfo_id and account=#{account} and userwarehouse.name=#{oldWHName}")
    int updateWHMessage(UpdateWHMessageDomain updateWHMessageDomain);
}
