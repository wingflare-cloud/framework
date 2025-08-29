package com.wingflare.engine.task.common.core.enums;


/**
 * 重试结果状态
 *
 * @author: opensnail
 * @date : 2021-11-03 11:05
 */
public enum RetryResultStatusEnum {

    SUCCESS(0),
    STOP(1),
    FAILURE(2);

    private final Integer status;

    RetryResultStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public static RetryResultStatusEnum getRetryResultStatusEnum(int status) {
        for (RetryResultStatusEnum value : RetryResultStatusEnum.values()) {
            if (value.status == status) {
                return value;
            }
        }

        return null;
    }
}
