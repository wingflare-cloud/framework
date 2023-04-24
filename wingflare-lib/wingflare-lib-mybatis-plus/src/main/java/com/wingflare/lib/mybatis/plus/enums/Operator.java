package com.wingflare.lib.mybatis.plus.enums;

/**
 * 操作符
 *
 * @author shaoyuyao
 * @date 2022/10/10 14:27
 */
public enum Operator {
    EQ(" = "),
    NE(" != "),
    GT(" > "),
    GE(" >= "),
    LT(" < "),
    LE(" <= "),
    BETWEEN(" between "),
    NOT_BETWEEN(" not between "),
    LIKE( " like " ),
    NOT_LIKE(" not like "),
    IN(" in "),
    NOT_IN(" not in "),
    AND(" and "),
    OR(" or "),
    ;

    private final String value;

    Operator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
