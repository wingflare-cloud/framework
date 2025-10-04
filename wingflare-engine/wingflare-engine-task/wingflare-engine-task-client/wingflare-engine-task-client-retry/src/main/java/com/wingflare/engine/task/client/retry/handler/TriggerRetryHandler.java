package com.wingflare.engine.task.client.retry.handler;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.common.util.ValidatorUtils;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.TriggerRetryApiRequest;

public class TriggerRetryHandler extends AbstractRetryRequestHandler<Boolean> {

    private final TriggerRetryApiRequest triggerRetryDTO;

    public TriggerRetryHandler(Long retryId) {
        this.triggerRetryDTO = new TriggerRetryApiRequest();
        triggerRetryDTO.setId(retryId);
    }

    @Override
    protected Boolean doExecute() {
        Result<Object> result;
        if (isOpenApiV2()){
            result = clientV2.triggerRetryTask(triggerRetryDTO);
        } else {
            result = client.triggerRetryTask(triggerRetryDTO);
        }
        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new TaskClientException(result.getMessage()));
        return (Boolean) result.getData();
    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return ValidatorUtils.validateEntity(triggerRetryDTO);
    }
}
