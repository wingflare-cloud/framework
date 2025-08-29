package com.wingflare.engine.task.common.core.enums;


/**
 * 服务节点类型
 *
 * @since 1.6.0
 */
public enum ServerStatusEnum {
    UP(1,"up"),
    DOWN(2, "down"),
    ;

    private final Integer type;
    private final String status;

    ServerStatusEnum(Integer type, String status) {
        this.type = type;
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public static ServerStatusEnum getByType(Integer type) {
        for (ServerStatusEnum serverStatusEnum : ServerStatusEnum.values()) {
            if (serverStatusEnum.getType().equals(type)) {
                return serverStatusEnum;
            }
        }
        return null;
    }
}
