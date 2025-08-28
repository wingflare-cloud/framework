package com.wingflare.lib.task.enums;


/**
 * @author: xiaowoniu
 * @date : 2024-01-09
 * @since : 2.6.0
 */
public enum JobArgsTypeEnum {
    TEXT(1, "Text"),
    JSON(2, "JSON");

    private final Integer argsType;
    private final String desc;

    JobArgsTypeEnum(Integer argsType, String desc) {
        this.argsType = argsType;
        this.desc = desc;
    }

    public Integer getArgsType() {
        return argsType;
    }

    public String getDesc() {
        return desc;
    }
}
