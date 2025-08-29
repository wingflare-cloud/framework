package com.wingflare.engine.task.client.core.handler.query;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.SnailJobClientException;
import com.wingflare.engine.task.client.core.handler.AbstractJobRequestHandler;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.model.response.JobBatchApiResponse;

import java.util.Objects;

/**
 * @since 1.5.0
 */
public class RequestQueryJobBatchHandler extends AbstractJobRequestHandler<JobBatchApiResponse> {
    private final Long queryJobBatchId;

    public RequestQueryJobBatchHandler(Long queryJobBatchId) {
        this.queryJobBatchId = queryJobBatchId;
    }

    @Override
    protected void afterExecute(JobBatchApiResponse jobBatchResponseVO) {

    }

    @Override
    protected void beforeExecute() {

    }

    @Override
    protected JobBatchApiResponse doExecute() {
        Result<Object> result;
        if (isOpenApiV2()) {
            result = clientV2.getJobBatchDetail(queryJobBatchId);
        } else {
            result = client.getJobBatchDetail(queryJobBatchId);
        }

        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new SnailJobClientException(result.getMessage()));
        Object data = result.getData();
        if (Objects.isNull(data)) {
            return null;
        }
        return JsonUtil.parseObject(JsonUtil.toJsonString(data), JobBatchApiResponse.class);
    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return Pair.of(queryJobBatchId != null && !Long.valueOf(0).equals(queryJobBatchId), "queryJobBatchId cannot be null and must be greater than 0");
    }

}
