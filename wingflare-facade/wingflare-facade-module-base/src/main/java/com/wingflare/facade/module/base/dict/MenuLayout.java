package com.wingflare.facade.module.base.dict;


import com.wingflare.api.core.ValueEnum;

/**
 * @EnumName MenuTypes
 * @Author naizui_ycx
 * @Date 2025/03/06 03
 * @Description 路由布局
 */
public enum MenuLayout implements ValueEnum<String> {

    /**
     * 基础组件，具有公共部分的布局，如全局头部、侧边栏、底部等
     */
    BASE("base"),
    /**
     * 空白布局
     */
    BLANK("blank"),
    ;

    private final String value;

    MenuLayout(String type) {
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
