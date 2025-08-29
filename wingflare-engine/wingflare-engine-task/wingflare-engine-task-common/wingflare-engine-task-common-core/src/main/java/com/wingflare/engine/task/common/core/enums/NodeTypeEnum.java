package com.wingflare.engine.task.common.core.enums;


/**
 * 服务节点类型
 *
 * @author: opensnail
 * @date : 2021-11-26 18:01
 */
public enum NodeTypeEnum {

    /**
     * 客户端
     */
    CLIENT(1),

    /**
     * 服务端
     */
    SERVER(2),
    ;

    private final Integer type;

    NodeTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
