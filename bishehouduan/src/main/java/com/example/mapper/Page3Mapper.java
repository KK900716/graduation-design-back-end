package com.example.mapper;

import com.example.pojo.response.ResponsePage3;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Page3Mapper {
    @Select("select userWareHouse.name,count,userWareHouse.available,remaining from userWareHouse inner join  userInfo on userInfo.id=userWareHouse.userInfo_id where account=#{account}")
    List<ResponsePage3> selectWareHouseList(@Param("account") String account);
}
