package com.wingflare.engine.task.client.retry.core.openapi;

import com.wingflare.engine.task.client.retry.core.handler.QueryRetryHandler;
import com.wingflare.engine.task.client.retry.core.handler.TriggerRetryHandler;
import com.wingflare.engine.task.client.retry.core.handler.UpdateRetryStatusHandler;

public class TaskRetryOpenApi {

    public static QueryRetryHandler queryTask(Long retryId) {
        return new QueryRetryHandler(retryId);
    }

    public static UpdateRetryStatusHandler updateTaskStatus(Long retryId) {
        return new UpdateRetryStatusHandler(retryId);
    }

    public static TriggerRetryHandler triggerTask(Long retryId) {
        return new TriggerRetryHandler(retryId);
    }
}
