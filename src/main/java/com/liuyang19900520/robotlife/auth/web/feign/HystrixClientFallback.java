package com.liuyang19900520.robotlife.auth.web.feign;

import com.liuyang19900520.robotlife.auth.domain.SysUser;
import org.springframework.stereotype.Component;

@Component
public class HystrixClientFallback implements UserFeignClient {

    @Override
    public Object signUp(SysUser sysUser) {
        return "HystrixClientFallback of method signUp in the feign @ auth-service";
    }

    @Override
    public SysUser signIn(SysUser sysUser) {
        return new SysUser();
    }
}