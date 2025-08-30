package com.wingflare.engine.task.common.core.rpc;

import com.wingflare.engine.task.common.core.model.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: opensanil
 * @date : 2024-05-11
 * @since : sj_1.0.0
 */
public class TaskFuture<R extends Result<Object>> extends CompletableFuture<R> {
    private final Long requestId;
    private final long timeout;
    private final TimeUnit unit;

    public TaskFuture(Long requestId, long timeout, TimeUnit unit) {
        this.requestId = requestId;
        this.timeout = timeout;
        this.unit = unit;
    }

    public static <R extends Result<Object>> TaskFuture<R> newFuture(Long requestId, long timeout, TimeUnit unit) {
        return new TaskFuture<>(requestId, timeout, unit);
    }

    public Long getRequestId() {
        return requestId;
    }

    public long getTimeout() {
        return timeout;
    }

    public TimeUnit getUnit() {
        return unit;
    }
}
