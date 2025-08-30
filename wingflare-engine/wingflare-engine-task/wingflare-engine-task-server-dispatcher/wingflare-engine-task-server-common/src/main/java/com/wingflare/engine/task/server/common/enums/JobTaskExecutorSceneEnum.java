package com.wingflare.engine.task.server.common.enums;

import com.wingflare.engine.task.server.common.exception.SnailJobServerException;

/**
 * job 触发器类型枚举
 *
 * @author: xiaowoniu
 * @date : 2023-12-06 17:21
 * @since : 2.5.0
 */
public enum JobTaskExecutorSceneEnum {
    AUTO_JOB(1, SyetemTaskTypeEnum.JOB),
    MANUAL_JOB(2, SyetemTaskTypeEnum.JOB),
    AUTO_WORKFLOW(3, SyetemTaskTypeEnum.WORKFLOW),
    MANUAL_WORKFLOW(4, SyetemTaskTypeEnum.WORKFLOW),
    ;

    private final Integer type;
    private final SyetemTaskTypeEnum systemTaskType;

    JobTaskExecutorSceneEnum(Integer type, SyetemTaskTypeEnum systemTaskType) {
        this.type = type;
        this.systemTaskType = systemTaskType;
    }

    public Integer getType() {
        return type;
    }

    public SyetemTaskTypeEnum getSystemTaskType() {
        return systemTaskType;
    }

    /**
     * 根据给定的类型获取对应的触发器类型枚举
     *
     * @param type 触发器类型的整数值
     * @return 对应的触发器类型枚举
     * @throws SnailJobServerException 当给定的类型不是有效的枚举类型时抛出异常
     */
    public static JobTaskExecutorSceneEnum get(Integer type) {
        for (JobTaskExecutorSceneEnum jobTaskExecutorSceneEnum : JobTaskExecutorSceneEnum.values()) {
            if (jobTaskExecutorSceneEnum.getType().equals(type)) {
                return jobTaskExecutorSceneEnum;
            }
        }

        throw new SnailJobServerException("Invalid enumeration type.[{}]", type);
    }

}
