package com.wingflare.engine.task.client.core.handler.update;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.SnailJobClientException;
import com.wingflare.engine.task.client.common.util.ValidatorUtils;
import com.wingflare.engine.task.client.core.enums.TriggerTypeEnum;
import com.wingflare.engine.task.client.core.handler.AbstractParamsHandler;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.lib.core.validation.Update;

import java.util.Objects;


public abstract class UpdateHandler<H> extends AbstractParamsHandler<H, Boolean> {

    public UpdateHandler(JobTaskTypeEnum typeEnum, Long jobId) {
        super(typeEnum);
        // 更新必须要id
        setId(jobId);
    }

    @Override
    protected void afterExecute(Boolean aBoolean) {

    }

    @Override
    protected void beforeExecute() {
        Integer triggerType = getReqDTO().getTriggerType();
        if (Objects.nonNull(triggerType) && triggerType == TriggerTypeEnum.WORK_FLOW.getType()) {
            // 工作流没有调度时间
            setTriggerInterval("*");
        }
    }

    @Override
    protected Boolean doExecute() {
        Result<Object> result;
        if (isOpenApiV2()) {
            result = clientV2.updateJob(getReqDTO());
        } else {
            result = client.updateJob(getReqDTO());
        }

        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new SnailJobClientException(result.getMessage()));
        return (Boolean) result.getData();
    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return ValidatorUtils.validateEntity(Update.class, getReqDTO());
    }

}
