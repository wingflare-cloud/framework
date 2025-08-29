package com.wingflare.engine.task.client.retry.core.openapi;

import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.annotation.Param;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.TriggerRetryApiRequest;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.*;

public interface RetryOpenApiClientV2 {
    @Mapping(method = RequestMethod.GET, path = OPENAPI_QUERY_RETRY)
    Result<Object> queryRetryTask(@Param("id") Long retryId);

    @Mapping(method = RequestMethod.POST, path = OPENAPI_TRIGGER_RETRY_V2)
    Result<Object> triggerRetryTask(TriggerRetryApiRequest triggerRetryDTO);

    @Mapping(method = RequestMethod.PUT, path = OPENAPI_UPDATE_RETRY_STATUS_V2)
    Result<Object> updateRetryTaskStatus(StatusUpdateRequest statusDTO);
}
