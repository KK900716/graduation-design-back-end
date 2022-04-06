package com.example.service;

import com.example.pojo.response.ResponsePage3;
import com.example.pojo.response.ResponsePage3Context;
import com.example.pojo.resquest.UpdateWareHouseMessage;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ljc
 */
public interface Page3Service {
    /**
     * com.example.Service.Page3Service.selectWareHouseList():
     * 查询仓库列表
     * @author ljc
     * @date 2022/4/6~17:35
     * @param account 用户名
     * @return java.util.List<com.example.pojo.response.ResponsePage3>
     */
    List<ResponsePage3> selectWareHouseList(String account);
    /**
     * com.example.Service.Page3Service.deleteWareHouse():
     * 删除仓库
     * @author ljc
     * @date 2022/4/6~17:36
     * @param account 用户名
     * @param name 仓库名
     * @return boolean
     */
    boolean deleteWareHouse(String account, String name);
    /**
     * com.example.Service.Page3Service.getWareHouseMessage():
     * 获得仓库信息
     * @author ljc
     * @date 2022/4/6~17:36
     * @param account 用户名
     * @param name 仓库名
     * @return com.example.pojo.response.ResponsePage3Context
     */
    ResponsePage3Context getWareHouseMessage(String account, String name);
    /**
     * com.example.Service.Page3Service.upload():
     * 上传文件
     * @author ljc
     * @date 2022/4/6~17:37
     * @param account 用户名
     * @param file 文件
     * @param name 仓库名
     * @return boolean
     */
    boolean upload(String account, MultipartFile file, String name);
    /**
     * com.example.Service.Page3Service.updateWareHouseMessage():
     * 更新仓库信息
     * @author ljc
     * @date 2022/4/6~17:38
     * @param account 用户名
     * @param updateWareHouseMessage 更新仓库信息
     * @return boolean
     */
    boolean updateWareHouseMessage(String account, UpdateWareHouseMessage updateWareHouseMessage);
    /**
     * com.example.Service.Page3Service.refresh():
     * 获得仓库图片的URL
     * @author ljc
     * @date 2022/4/6~17:39
     * @param account 用户名
     * @param name 仓库名
     * @return java.util.List<java.lang.String>
     */
    List<String> refresh(String account, String name);
    /**
     * com.example.Service.Page3Service.getImg():
     * 获得仓库图片
     * @author ljc
     * @date 2022/4/6~17:39
     * @param account 用户名
     * @param id 图片id
     * @param name 仓库名
     * @param response response
     */
    void getImg(String account, String id,String name, HttpServletResponse response);
    /**
     * com.example.Service.Page3Service.getScore():
     * 上传分数
     * @author ljc
     * @date 2022/4/6~17:40
     * @param account 用户名
     * @param score 分数
     * @param name 仓库名
     * @param id 图片id
     * @return boolean
     */
    boolean getScore(String account, int score, String name, String id);
}
