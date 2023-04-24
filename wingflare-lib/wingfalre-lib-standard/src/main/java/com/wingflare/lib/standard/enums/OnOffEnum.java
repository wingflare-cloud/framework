package com.wingflare.lib.standard.enums;

import com.wingflare.lib.core.enums.ValueEnum;

/**
 * @EnumName OnOffEnum
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 启用禁用
 */
public enum OnOffEnum implements ValueEnum<Integer> {

    /**
     * 启用
     */
    ON(1),
    /**
     * 禁用
     */
    OFF(0),
    ;

    private final Integer value;

    OnOffEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
