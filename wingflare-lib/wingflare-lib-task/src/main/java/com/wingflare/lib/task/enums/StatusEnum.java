package com.wingflare.lib.task.enums;


/**
 * 状态值
 *
 * @author: opensnail
 * @date : 2021-11-26 17:58
 */
public enum StatusEnum {

    NO(0),
    YES(1);

    private final Integer status;

    StatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public static StatusEnum of(Integer status) {
        for (StatusEnum statusEnum : StatusEnum.values()) {
            if (statusEnum.status.equals(status)) {
                return statusEnum;
            }
        }
        return null;
    }

}
