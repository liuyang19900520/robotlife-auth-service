package com.liuyang19900520.robotlife.auth.common.pojo;

/**
 * Message类别
 *
 * @author liuya
 */

public enum Messages {

    //HttpStatus业务相关，负责exception的管理

    UNAUTHORIZATION(401, "认证失败"),
    UNFORBIDDEN(403, "授权失败"),
    OK(700000, "登录成功"),
    SIGN_IN_FAILED(700001, "登录失败"),
    SIGN_IN_AUTH_FAILED(700002, "错误的凭证，用户名或密码不正确"),
    SIGN_IN_STATUS_FAILED(700003, "账户已锁定"),
    SIGN_IN_TIMES_FAILED(700004, "错误次数过多");


    private final int value;

    private final String msg;

    Messages(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int value() {
        return value;
    }

    public String msg() {
        return msg;
    }
}
