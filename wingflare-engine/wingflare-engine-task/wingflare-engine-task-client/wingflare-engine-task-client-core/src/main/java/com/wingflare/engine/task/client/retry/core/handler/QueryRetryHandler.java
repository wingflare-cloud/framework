package com.wingflare.engine.task.client.retry.core.handler;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.response.RetryApiResponse;

import java.util.Objects;

public class QueryRetryHandler extends AbstractRetryRequestHandler<RetryApiResponse> {

    private final Long retryId;

    public QueryRetryHandler(Long retryId) {
        this.retryId = retryId;
    }

    @Override
    protected RetryApiResponse doExecute() {
        Result<Object> result;
        if (isOpenApiV2()){
            result = clientV2.queryRetryTask(retryId);
        } else {
            result = client.queryRetryTask(retryId);
        }
        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new TaskClientException(result.getMessage()));
        Object data = result.getData();
        if (Objects.isNull(data)) {
            return null;
        }
        return JsonUtil.parseObject(JsonUtil.toJsonString(data), RetryApiResponse.class);
    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return Pair.of(retryId != null && retryId > 0, "retryId cannot be null and must be greater than 0");
    }
}
