package com.imooc.enums;

public enum Sex {
    /**
     * 表示性别的枚举
     */
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    public final Integer type;
    public final String value;

    Sex(int type, String value) {
        this.type = type;
        this.value = value;
    }
}
