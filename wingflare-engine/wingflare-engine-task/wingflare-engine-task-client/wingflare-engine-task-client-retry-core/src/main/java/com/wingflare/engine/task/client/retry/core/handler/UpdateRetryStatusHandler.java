package com.wingflare.engine.task.client.retry.core.handler;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.common.util.ValidatorUtils;
import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;

public class UpdateRetryStatusHandler extends AbstractRetryRequestHandler<Boolean> {

    private final StatusUpdateRequest updateRetryStatusDTO;

    public UpdateRetryStatusHandler(Long retryId) {
        this.updateRetryStatusDTO = new StatusUpdateRequest();
        updateRetryStatusDTO.setId(retryId);
    }

    public UpdateRetryStatusHandler setRetryStatus(RetryStatusEnum retryStatusEnum) {
        updateRetryStatusDTO.setRetryStatus(retryStatusEnum.getStatus());
        updateRetryStatusDTO.setStatus(retryStatusEnum.getStatus());
        return this;
    }

    @Override
    protected Boolean doExecute() {
        Result<Object> result;
        if (isOpenApiV2()) {
            result = clientV2.updateRetryTaskStatus(updateRetryStatusDTO);
        } else {
            result = client.updateRetryTaskStatus(updateRetryStatusDTO);
        }
        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new TaskClientException(result.getMessage()));
        return (Boolean) result.getData();
    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return ValidatorUtils.validateEntity(updateRetryStatusDTO);
    }
}
