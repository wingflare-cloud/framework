package com.wingflare.engine.task.client.retry.core.callback.future;

import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.retry.core.client.RetryClient;
import com.wingflare.engine.task.client.retry.core.context.RemoteRetryContext;
import com.wingflare.engine.task.common.core.enums.RetryResultStatusEnum;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.dto.DispatchRetryResultDTO;
import com.wingflare.engine.task.common.model.request.DispatchRetryResultRequest;
import com.google.common.util.concurrent.FutureCallback;

import java.util.Objects;
import java.util.concurrent.CancellationException;

/**
 * <p>
 * 重试执行结果上报
 * </p>
 *
 * @author opensnail
 * @date 2025-02-11
 */
public class RetryTaskExecutorFutureCallback implements FutureCallback<DispatchRetryResultDTO> {

    private static final RetryClient CLIENT = RequestBuilder.<RetryClient, TaskRpcResult>newBuilder()
            .client(RetryClient.class)
            .callback(nettyResult -> {
                if (nettyResult.getStatus() == StatusEnum.NO.getStatus()) {
                    TaskEngineLog.LOCAL.error("Retry execute result report successfully requestId:[{}]",
                            nettyResult.getReqId());
                }

            }).build();


    private final RemoteRetryContext retryContext;
    public RetryTaskExecutorFutureCallback(RemoteRetryContext retryContext) {
        this.retryContext = retryContext;
    }

    @Override
    public void onSuccess(DispatchRetryResultDTO result) {

        try {
            DispatchRetryResultRequest request = buildDispatchRetryResultRequest(result);
            if (RetryResultStatusEnum.SUCCESS.getStatus().equals(result.getStatusCode())) {
                request.setTaskStatus(RetryTaskStatusEnum.SUCCESS.getStatus());
            } else if (RetryResultStatusEnum.STOP.getStatus().equals(result.getStatusCode())) {
                request.setTaskStatus(RetryTaskStatusEnum.STOP.getStatus());
            } else {
                request.setTaskStatus(RetryTaskStatusEnum.FAIL.getStatus());
            }
            CLIENT.dispatchResult(request);
        } catch (Exception e) {
            TaskEngineLog.REMOTE.error("Execution result reporting exception.[{}]", retryContext.getRetryTaskId(), e);
        }

    }


    @Override
    public void onFailure(Throwable t) {
        if (t instanceof CancellationException) {
            TaskEngineLog.LOCAL.debug("The task has been canceled, no status feedback will be made");
            return;
        }

        try {
            DispatchRetryResultRequest request = buildDispatchRetryResultRequest(null);
            request.setExceptionMsg(t.getMessage());
            request.setTaskStatus(RetryTaskStatusEnum.FAIL.getStatus());
            CLIENT.dispatchResult(request);
        } catch (Exception e) {
            TaskEngineLog.REMOTE.error("Execution result reporting exception.[{}]", retryContext.getRetryTaskId(), e);
        }

    }


    private DispatchRetryResultRequest buildDispatchRetryResultRequest(DispatchRetryResultDTO result) {
        DispatchRetryResultRequest request = new DispatchRetryResultRequest();
        request.setRetryTaskId(retryContext.getRetryTaskId());
        request.setNamespaceId(retryContext.getNamespaceId());
        request.setGroupName(retryContext.getGroupName());
        request.setSceneName(retryContext.getScene());
        request.setRetryId(retryContext.getRetryId());
        request.setRetryTaskId(retryContext.getRetryTaskId());
        if (Objects.nonNull(result)) {
            request.setResult(result.getResultJson());
            request.setExceptionMsg(result.getExceptionMsg());
        }
        return request;
    }
}
