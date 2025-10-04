package com.wingflare.engine.task.client.retry.openapi;

import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.TriggerRetryApiRequest;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;

@Deprecated(since = "1.7.0")
public interface RetryOpenApiClient {
    @Mapping(method = RequestMethod.POST, path = "/api/retry/query")
    Result<Object> queryRetryTask(Long retryId);

    @Mapping(method = RequestMethod.POST, path = "/api/retry/triggerRetry")
    Result<Object> triggerRetryTask(TriggerRetryApiRequest triggerRetryDTO);

    @Mapping(method = RequestMethod.POST, path = "/api/retry/updateRetryStatus")
    Result<Object> updateRetryTaskStatus(StatusUpdateRequest statusDTO);
}
