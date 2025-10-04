package com.wingflare.engine.task.client.retry.openapi;

import com.wingflare.engine.task.client.retry.handler.QueryRetryHandler;
import com.wingflare.engine.task.client.retry.handler.TriggerRetryHandler;
import com.wingflare.engine.task.client.retry.handler.UpdateRetryStatusHandler;

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
