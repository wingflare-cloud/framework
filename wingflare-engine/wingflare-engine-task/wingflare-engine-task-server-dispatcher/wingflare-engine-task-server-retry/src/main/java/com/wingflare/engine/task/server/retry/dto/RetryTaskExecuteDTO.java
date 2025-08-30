package com.wingflare.engine.task.server.retry.dto;


/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-26
 */
public class RetryTaskExecuteDTO extends BaseDTO {

    private Integer routeKey;

    private Integer retryTaskExecutorScene;

    public Integer getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(Integer routeKey) {
        this.routeKey = routeKey;
    }

    public Integer getRetryTaskExecutorScene() {
        return retryTaskExecutorScene;
    }

    public void setRetryTaskExecutorScene(Integer retryTaskExecutorScene) {
        this.retryTaskExecutorScene = retryTaskExecutorScene;
    }
}

