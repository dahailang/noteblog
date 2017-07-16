package com.pursuit.noteblog.enums;

/**
 * 成功失败状态
 */
public enum SuccessEnum {
    SUCCESS(1),
    FAIL(0);

    int value;

    private SuccessEnum(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
    
    public String info() {
        if (value == 1) {
            return "成功";
        } else {
            return "失败";
        }
    }
}
