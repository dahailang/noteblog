package com.pursuit.noteblog.enums;

/**
 * 用户类型
 */
public enum UserTypeEnum {

    //管理员
    ADMIN_USER(0),
    //普通用户
    REGULAR_USER(1),
    //不存在用户
    NO_USER(-1);
    
    private Integer value;

    private UserTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
