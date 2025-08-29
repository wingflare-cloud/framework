package com.wingflare.engine.task.common.core.enums;


/**
 * 执行器类型枚举
 *
 * @author dhb52
 * @date 2024-07-09 23:48:55
 * @since 1.1.0
 */
public enum ExecutorTypeEnum {

    JAVA(1),
    PYTHON(2),
    GO(3),
    ;

    private final int type;

    ExecutorTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
