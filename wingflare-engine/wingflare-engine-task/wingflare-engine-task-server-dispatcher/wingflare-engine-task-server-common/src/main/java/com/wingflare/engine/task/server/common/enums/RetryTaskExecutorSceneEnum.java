package com.wingflare.engine.task.server.common.enums;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-22
 */
public enum RetryTaskExecutorSceneEnum {
    AUTO_RETRY(1, SyetemTaskTypeEnum.RETRY),
    MANUAL_RETRY(2, SyetemTaskTypeEnum.RETRY),
    AUTO_CALLBACK(3, SyetemTaskTypeEnum.CALLBACK),
    MANUAL_CALLBACK(4, SyetemTaskTypeEnum.CALLBACK);

    private final int scene;
    private final SyetemTaskTypeEnum taskType;

    RetryTaskExecutorSceneEnum(int scene, SyetemTaskTypeEnum taskType) {
        this.scene = scene;
        this.taskType = taskType;
    }

    public int getScene() {
        return scene;
    }

    public SyetemTaskTypeEnum getTaskType() {
        return taskType;
    }
}
