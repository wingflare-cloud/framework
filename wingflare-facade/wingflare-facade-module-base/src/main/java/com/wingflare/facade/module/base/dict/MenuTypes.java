package com.wingflare.facade.module.base.dict;

import com.wingflare.lib.core.enums.ValueEnum;

/**
 * @EnumName MenuTypes
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 菜单类型
 */
public enum MenuTypes implements ValueEnum<String> {

    /**
     * 按钮
     */
    BUTTON("button"),
    /**
     * 菜单
     */
    MENU("menu"),
    /**
     * 目录
     */
    DIR("dir"),
    ;

    private final String value;

    MenuTypes(String type) {
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
