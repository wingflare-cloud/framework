package com.wingflare.facade.module.user.dict;

import com.wingflare.lib.core.enums.ValueEnum;

/**
 * @EnumName UserAccountType
 * @Author naizui_ycx
 * @Date 2023/04/04 04
 * @Description 用户账户类型
 */
public enum UserAccountType implements ValueEnum<String> {

    /**
     * 员工账户
     */
    STAFF("staff"),
    /**
     * 客户账户
     */
    CUSTOMER("customer"),
    /**
     * 友商账户
     */
    FRIENDLY("friendly"),
    /**
     * 外包员工账户
     */
    OUTSOURCED("outsourced"),
    /**
     * 特权账户
     */
    PRIVILEGE("privilege")
    ;

    private final String value;

    UserAccountType(String type) {
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
