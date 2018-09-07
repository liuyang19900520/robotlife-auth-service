package com.liuyang19900520.robotlife.auth.service.impl;


import com.liuyang19900520.robotlife.auth.common.util.CryptoUtil;
import com.liuyang19900520.robotlife.auth.domain.SysUser;
import com.liuyang19900520.robotlife.auth.feign.UserFeignClient;
import com.liuyang19900520.robotlife.auth.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Created by liuyang on 2018/3/16
 *
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

        roles.stream().filter(role -> !role.equals("") && role != null)
                .forEachOrdered(s -> strRoles.append(s).append(","));


        permissions.stream().filter(permission -> !permission.equals("") && permission != null)
                .forEachOrdered(s -> strPerms.append(s).append(","));

        String permsJwt = "";
        String rolesJwt = "";

        if (!strRoles.toString().equals("")) {
            rolesJwt = strRoles.substring(0, strRoles.length() - 1);
        }
        if (!strPerms.toString().equals("")) {
            permsJwt = strPerms.substring(0, strPerms.length() - 1);
        }


        String jwt = CryptoUtil.issueJwt(UUID.randomUUID().toString(), userName,
                rolesJwt, permsJwt, new Date(), CryptoUtil.ACCESS_TOKEN_TYPE);

        user.setToken(jwt);

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
