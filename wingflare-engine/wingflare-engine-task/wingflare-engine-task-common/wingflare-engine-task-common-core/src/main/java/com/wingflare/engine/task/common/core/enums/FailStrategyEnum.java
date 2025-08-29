package com.wingflare.engine.task.common.core.enums;


import java.util.Objects;

/**
 * @author xiaowoniu
 * @date 2023-12-26 22:16:51
 * @since 2.6.0
 */
public enum FailStrategyEnum {

    SKIP(1, "Skip"),
    BLOCK(2, "Block");

    private final Integer code;
    private final String desc;

    FailStrategyEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static FailStrategyEnum valueOf(Integer code) {
        for (FailStrategyEnum failStrategyEnum : FailStrategyEnum.values()) {
            if (Objects.equals(failStrategyEnum.code, code)) {
                return failStrategyEnum;
            }
        }

        return null;
    }

}
