package com.wingflare.engine.task.client.core.handler.add;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.common.util.ValidatorUtils;
import com.wingflare.engine.task.client.core.handler.AbstractParamsHandler;
import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.lib.core.validation.Create;

import static com.wingflare.engine.task.client.core.enums.TriggerTypeEnum.WORK_FLOW;

public abstract class AddHandler<H> extends AbstractParamsHandler<H, Long> {

    public AddHandler(JobTaskTypeEnum taskType) {
        super(taskType);
    }

    @Override
    protected Long doExecute() {
        Result<Object> result;
        if (isOpenApiV2()) {
            result = clientV2.addJob(getReqDTO());
        } else {
            result = client.addJob(getReqDTO());
        }
        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new TaskClientException(result.getMessage()));
        String data = JsonUtil.toJsonString(result.getData());
        return Long.valueOf(data);
    }

    @Override
    protected void beforeExecute() {
        // 此次是兜底覆盖,工作流是没有调度时间
        if (getReqDTO().getTriggerType() == WORK_FLOW.getType()) {
            setTriggerInterval("*");
        }
    }

    @Override
    protected void afterExecute(Long id) {

    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return ValidatorUtils.validateEntity(Create.class, getReqDTO());
    }

}
