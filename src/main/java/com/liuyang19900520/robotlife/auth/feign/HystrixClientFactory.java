package com.liuyang19900520.robotlife.auth.feign;

import com.liuyang19900520.robotlife.auth.domain.SysUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
public class HystrixClientFactory implements FallbackFactory<UserFeignClient> {


    @Override
    public UserFeignClient create(Throwable cause) {
        log.info("fallback; reason was: {}", cause.getMessage());
        return new UserFeignClientWithFactory() {

            @Override
            public SysUser findAccount(String userName) {
                return new SysUser();
            }

            @Override
            public Set<String> listRolesByAccount(String userName) {
                return null;
            }

            @Override
            public Set<String> listPermissionsByAccount(String userName) {
                return null;
            }
        };
    }
}
