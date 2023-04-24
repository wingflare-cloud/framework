package com.wingflare.facade.module.base.dict;

import com.wingflare.lib.core.enums.ValueEnum;

/**
 * @ClassName DictTypes
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 字典类型
 */
public enum DictTypes implements ValueEnum<String> {

    /**
     * 目录类型字典
     */
    DIRECTORY("directory"),
    /**
     * 元素类型字典
     */
    ELEMENT("element"),
    ;


    private final String value;

    DictTypes(String type) {
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
