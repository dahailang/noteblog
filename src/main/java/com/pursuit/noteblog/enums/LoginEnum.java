package com.pursuit.noteblog.enums;

/**
 * 登录状态
 */
public enum LoginEnum {
    LOGIN_SUCCESS("1"), // 成功
    LOGIN_USER_NOT_EXIST("0"), // 用户未注册
    LOGIN_WRONG_USER_OR_PASSWORD("-1"), // 用户名或者密码错误
    LOGIN_NOT_ADMIN("-2");// 不是超级管理员

    String value;

    private LoginEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}