package com.wingflare.engine.task.common.core.enums;


import com.wingflare.lib.core.utils.StringUtil;

/**
 * 标识某个操作的具体原因
 *
 * @author opensnail
 * @date 2023-10-07 23:05:50
 * @since 2.4.0
 */
public enum RetryOperationReasonEnum {

    NONE(0, StringUtil.EMPTY),
    TASK_EXECUTION_TIMEOUT(1, "Task execution timeout"),
    NOT_CLIENT(2, "No client nodes"),
    RETRY_SUSPEND(3, "Retry paused"),
    RETRY_TASK_DISCARD(4, "Task discarded"),
    RETRY_TASK_OVERLAY(5, "Task overridden"),
    TASK_EXECUTION_ERROR(6, "Unexpected exception occurred during task execution"),
    MANNER_STOP(7, "Manual stop"),
    NOT_RUNNING_RETRY(8, "Current retry not running"),
    SCENE_CLOSED(9, "Current scene closed"),
    RETRY_FAIL(10, "Retry failed"),
    CLIENT_TRIGGER_RETRY_STOP(11, "Client triggered task stop"),
    ;

    private final int reason;
    private final String desc;

    RetryOperationReasonEnum(int reason, String desc) {
        this.reason = reason;
        this.desc = desc;
    }

    public int getReason() {
        return reason;
    }

    public String getDesc() {
        return desc;
    }

    public static RetryOperationReasonEnum of(Integer operationReason) {
        if (operationReason == null) {
            return NONE;
        }

        for (RetryOperationReasonEnum sceneEnum : RetryOperationReasonEnum.values()) {
            if (sceneEnum.getReason() == operationReason) {
                return sceneEnum;
            }
        }

        return NONE;
    }

}
