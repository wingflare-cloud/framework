package com.wingflare.engine.task.client.core.handler.trigger;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.core.handler.AbstractTriggerHandler;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;

public abstract class TriggerJobHandler<H> extends AbstractTriggerHandler<H, Boolean> {

    public TriggerJobHandler(Long triggerJobId) {
        super(triggerJobId);
    }

    @Override
    protected void afterExecute(Boolean aBoolean) {

    }

    @Override
    protected void beforeExecute() {

    }

    @Override
    protected Boolean doExecute() {
        Result<Object> result;
        if (isOpenApiV2()) {
            result = clientV2.triggerJob(getReqDTO());
        } else {
            result = client.triggerJob(getReqDTO());
        }

        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new TaskClientException(result.getMessage()));
        return (Boolean)result.getData();
    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return Pair.of(getReqDTO().getJobId() != null && !Long.valueOf(0).equals(getReqDTO().getJobId()),  "triggerJobId cannot be null and must be greater than 0");
    }
}
