package com.liuyang19900520.robotlife.auth.service.impl;


import com.liuyang19900520.robotlife.auth.commons.util.CryptoUtil;
import com.liuyang19900520.robotlife.auth.domain.SysUser;
import com.liuyang19900520.robotlife.auth.service.AuthenticateService;
import com.liuyang19900520.robotlife.auth.web.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Created by liuyang on 2018/3/16
 * @author liuya
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    UserFeignClient userFeignClient;


    @Override
    public SysUser findUserByAccount(String userName) {

        SysUser user = userFeignClient.findAccount(userName);

        Set<String> roles = listRolesByAccount(userName);
        Set<String> permissions = listPermissionsByAccount(userName);

        StringBuffer strRoles = new StringBuffer();
        StringBuffer strPerms = new StringBuffer();

        permissions.stream().forEachOrdered(s -> strPerms.append(s).append(","));
        String permsJwt = strPerms.substring(0, strPerms.length() - 1);


        roles.stream().forEachOrdered(s -> strRoles.append(s).append(","));
        String rolesJwt = strRoles.substring(0, strRoles.length() - 1);

        String jwt = CryptoUtil.issueJwt(UUID.randomUUID().toString(), userName,
                rolesJwt, permsJwt, new Date(), CryptoUtil.ACCESS_TOKEN_TYPE);

        return user;
    }

    @Override
    public Set<String> listRolesByAccount(String userName) {
        return userFeignClient.listRolesByAccount(userName);
    }

    @Override
    public Set<String> listPermissionsByAccount(String userName) {
        return userFeignClient.listPermissionsByAccount(userName);
    }
}
