package com.wingflare.facade.module.base.dict;

import com.wingflare.lib.core.enums.ValueEnum;

/**
 * @EnumName MenuTypes
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 设置状态
 */
public enum SettingStatus implements ValueEnum<String> {

    /**
     * 启用状态
     */
    ENABLE("1"),
    /**
     * 禁用状态
     */
    DISABLE("0"),
    ;


    private final String value;

    SettingStatus(String status) {
        value = status;
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
