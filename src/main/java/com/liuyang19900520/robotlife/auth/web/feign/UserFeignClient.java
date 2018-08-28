package com.liuyang19900520.robotlife.auth.web.feign;


import com.liuyang19900520.robotlife.auth.domain.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

/**
 * @author liuya
 */
@FeignClient(name = "robotlife-user", fallbackFactory = HystrixClientFactory.class)
@Component
public interface UserFeignClient {

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    Object signUp(@RequestBody SysUser sysUser);

    @RequestMapping(value = "/users/signin", method = RequestMethod.POST)
    SysUser findAccount(String sysUser);

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public Set<String> listRolesByAccount(String userName);

    @RequestMapping(value = "/permissions", method = RequestMethod.POST)
    public Set<String> listPermissionsByAccount(String userName);


}
