package com.liuyang19900520.robotlife.auth.service.impl;


import com.liuyang19900520.robotlife.auth.domain.SysUser;
import com.liuyang19900520.robotlife.auth.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by liuyang on 2018/3/16
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {


    @Override
    public SysUser findUserByAccount(String userName) {
        return null;
    }

    @Override
    public Set<String> listRolesByAccount(String userName) {
        return null;
    }

    @Override
    public Set<String> listPermissionsByAccount(String userName) {
        return null;
    }
}
