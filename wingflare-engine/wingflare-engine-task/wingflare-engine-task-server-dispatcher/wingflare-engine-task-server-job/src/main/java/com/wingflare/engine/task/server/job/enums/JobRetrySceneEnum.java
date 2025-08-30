package com.wingflare.engine.task.server.job.enums;


/**
 * @author: xiaowoniu
 * @date : 2024-02-27
 * @since : 3.1.0
 */
public enum JobRetrySceneEnum {

    AUTO(1),
    MANUAL(2);

    private final Integer retryScene;

    JobRetrySceneEnum(Integer retryScene) {
        this.retryScene = retryScene;
    }

    public Integer getRetryScene() {
        return retryScene;
    }
}
