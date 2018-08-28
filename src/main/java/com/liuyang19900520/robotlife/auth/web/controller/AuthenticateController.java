package com.liuyang19900520.robotlife.auth.web.controller;


import com.liuyang19900520.robotlife.auth.commons.pojo.Messages;
import com.liuyang19900520.robotlife.auth.commons.pojo.ResultVo;
import com.liuyang19900520.robotlife.auth.domain.SysUser;
import com.liuyang19900520.robotlife.auth.web.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuyang on 2018/3/16
 *
 * @author liuya
 */
@Slf4j

@RestController
@RequestMapping("/auth")
public class AuthenticateController {

    @Autowired
    UserFeignClient userFeignClient;

    /**
     * 登录
     *
     * @param loginUser
     * @return
     */

    @PostMapping("/signin")
    public Object signin(@RequestBody SysUser loginUser) {

        SysUser user = userFeignClient.findAccount(loginUser.getUserName());

        return ResultVo.success(Messages.OK, user);

//        Set<String> roles = authenticateService.listRolesByAccount(loginUser.getUsername());
//        Set<String> permissions = authenticateService.listPermissionsByAccount(loginUser.getUsername());
//
//        StringBuffer strRoles = new StringBuffer();
//        StringBuffer strPerms = new StringBuffer();
//
//        permissions.stream().forEachOrdered(s -> strPerms.append(s).append(","));
//        String permsJwt = strPerms.substring(0, strPerms.length() - 1);
//
//
//        roles.stream().forEachOrdered(s -> strRoles.append(s).append(","));
//        String rolesJwt = strRoles.substring(0, strRoles.length() - 1);
//
//        String jwt = CryptoUtil.issueJwt(UUID.randomUUID().toString(), loginUser.getUsername(),
//                rolesJwt, permsJwt, new Date(), CryptoUtil.ACCESS_TOKEN_TYPE);
//
//        String refresh = CryptoUtil.issueJwt(UUID.randomUUID().toString(), loginUser.getUsername(),
//                rolesJwt, permsJwt, new Date(), CryptoUtil.REFRESH_TOKEN_TYPE);
//
//        HashMap<String, String> tokens = Maps.newHashMap();
//        tokens.put("token", jwt);
//        tokens.put("refreshToken", refresh);
//
//        SysUser sysUser = new SysUser();
//        sysUser.setToken(jwt);
//        sysUser.setRefreshToken(refresh);
//
//        return ResultVo.success(Messages.OK, sysUser);


    }

    @PostMapping("/signup")
    public Object signup(@RequestBody SysUser loginUser) {

        userFeignClient.signUp(loginUser);

        return ResultVo.success(Messages.OK, "asdf");

    }


}
