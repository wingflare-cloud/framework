package com.wingflare.engine.task.server.retry.support.timer;


/**
 * @author opensnail
 * @date 2023-09-23 09:14:03
 * @since 2.4.0
 */
public class RetryTimerContext {

    private Long retryId;

    private Long retryTaskId;

    private Integer retryTaskExecutorScene;

    public Long getRetryId() {
        return retryId;
    }

    public void setRetryId(Long retryId) {
        this.retryId = retryId;
    }

    public Long getRetryTaskId() {
        return retryTaskId;
    }

    public void setRetryTaskId(Long retryTaskId) {
        this.retryTaskId = retryTaskId;
    }

    public Integer getRetryTaskExecutorScene() {
        return retryTaskExecutorScene;
    }

    public void setRetryTaskExecutorScene(Integer retryTaskExecutorScene) {
        this.retryTaskExecutorScene = retryTaskExecutorScene;
    }
}
