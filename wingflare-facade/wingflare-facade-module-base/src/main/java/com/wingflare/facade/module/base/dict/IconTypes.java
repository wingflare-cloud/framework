package com.wingflare.facade.module.base.dict;


import com.wingflare.api.core.ValueEnum;

/**
 * @EnumName MenuTypes
 * @Author naizui_ycx
 * @Date 2025/03/03 03
 * @Description 图标类型
 */
public enum IconTypes implements ValueEnum<String> {

    /**
     * iconify
     */
    ICONIFY("iconify"),
    /**
     * 本地
     */
    LOCAL("local"),
    ;

    private final String value;

    IconTypes(String type) {
        value = type;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
