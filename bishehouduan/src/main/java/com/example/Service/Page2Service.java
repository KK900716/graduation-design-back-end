package com.example.Service;

import com.example.pojo.response.ResponsePage2;
import com.example.pojo.resquest.Page2Insert;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ljc
 */
public interface Page2Service {
    /**
     * com.example.Service.Page2Service.getWareHouse():
     * 获得仓库信息
     * @author ljc
     * @date 2022/4/6~17:30
     * @param account 用户名
     * @return com.example.pojo.response.ResponsePage2
     */
    ResponsePage2 getWareHouse(String account);
    /**
     * com.example.Service.Page2Service.insertWareHouse():
     * 插入仓库
     * @author ljc
     * @date 2022/4/6~17:31
     * @param preCheckJwt 用户名
     * @param page2Insert 插入的仓库信息
     * @return boolean
     */
    boolean insertWareHouse(String preCheckJwt, Page2Insert page2Insert);
}
