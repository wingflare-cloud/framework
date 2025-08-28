package com.wingflare.lib.task.rpc;

import com.wingflare.lib.task.model.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author: opensanil
 * @date : 2024-05-11
 * @since : sj_1.0.0
 */
public class SnailJobFuture<R extends Result<Object>> extends CompletableFuture<R> {
    private final Long requestId;
    private final long timeout;
    private final TimeUnit unit;

    public SnailJobFuture(Long requestId, long timeout, TimeUnit unit) {
        this.requestId = requestId;
        this.timeout = timeout;
        this.unit = unit;
    }

    public static <R extends Result<Object>> SnailJobFuture<R> newFuture(Long requestId, long timeout, TimeUnit unit) {
        return new SnailJobFuture<>(requestId, timeout, unit);
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
