package com.wingflare.engine.task.client.core.handler.delete;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.client.common.exception.TaskClientException;
import com.wingflare.engine.task.client.core.handler.AbstractJobRequestHandler;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.Result;

import java.util.Set;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.client.core.handler.delete
 * @Project：snail-job
 * @Date：2024/11/21 22:42
 * @Filename：DeleteWorkflowHandler
 */
public class DeleteWorkflowHandler extends AbstractJobRequestHandler<Boolean> {
    private final Set<Long> toDeleteIds;

    public DeleteWorkflowHandler(Set<Long> toDeleteIds) {
        this.toDeleteIds = toDeleteIds;
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
        if(isOpenApiV2()) {
            result = clientV2.deleteWorkflow(toDeleteIds);
        } else {
            result = client.deleteWorkflow(toDeleteIds);
        }

        Assert.isTrue(StatusEnum.YES.getStatus() == result.getStatus(),
                () -> new TaskClientException(result.getMessage()));
        return (Boolean)result.getData();
    }

    @Override
    protected Pair<Boolean, String> checkRequest() {
        return Pair.of(toDeleteIds != null && !toDeleteIds.isEmpty() && !toDeleteIds.contains(0L),  "toDeleteId cannot be null or 0");
    }
}
