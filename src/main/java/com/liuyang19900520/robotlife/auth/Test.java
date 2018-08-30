package com.liuyang19900520.robotlife.auth;

import com.liuyang19900520.robotlife.auth.commons.util.CryptoUtil;
import com.liuyang19900520.robotlife.auth.shiro.Encrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public class Test {

    public static void main(String[] args) {
        MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<String, Object>();
//        String clientKey = "576996936";// 客户端标识（用户名）
        String clientKey = "super";// 客户端标识（用户名）
        Long current = System.currentTimeMillis();
        String timeStamp = current.toString();// 时间戳

        String baseString = clientKey + timeStamp;
        String digest = CryptoUtil.hmacDigest(baseString);// 生成HMAC摘要

        System.out.println(digest + "===================================================================================");
        System.out.println(timeStamp + "===================================================================================");
        System.out.println(clientKey + "===================================================================================");

    }


}
