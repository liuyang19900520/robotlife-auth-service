package com.liuyang19900520.robotlife.auth.web.feign;


import com.liuyang19900520.robotlife.auth.domain.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "robotlife-user", fallbackFactory = HystrixClientFactory.class)
public interface UserFeignClient {

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    Object signUp(@RequestBody SysUser sysUser);

    @RequestMapping(value = "/users/signin", method = RequestMethod.POST)
    SysUser signIn(@RequestBody SysUser sysUser);


}
