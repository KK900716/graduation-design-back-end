package com.example.Service.Impl;

import com.example.Service.LoginService;
import com.example.mapper.LoginMapper;
import com.example.pojo.resquest.UserData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author ljc
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;
    @Resource
    private LoginMapper loginMapper;

    /**
     * 处理登录逻辑
     * @param userData 登录信息
     * @return 是否正确
     */
    @Override
    public boolean userDataVerification(UserData userData) {
        String checkCode = redisTemplate.opsForValue().get(userData.getUid());
        if (!userData.getVerificationCode().equals(checkCode)) {
            return false;
        }
        try {
            return loginMapper.selectUserLogin(userData.getUserAccount()).equals(userData.getUserPassword());
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * uuid和checkCode存入redis
     * @param uuid uuid
     * @param checkCode checkCode
     */
    @Override
    public void insertUidAndVerification(String uuid, String checkCode) {
        redisTemplate.opsForValue().set(uuid,checkCode,3*60, TimeUnit.SECONDS);
    }
}
