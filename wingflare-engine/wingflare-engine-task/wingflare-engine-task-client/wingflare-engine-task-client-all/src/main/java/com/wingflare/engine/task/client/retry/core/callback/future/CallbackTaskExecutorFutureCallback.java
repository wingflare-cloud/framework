package com.wingflare.engine.task.client.retry.core.callback.future;

import com.wingflare.engine.task.client.common.rpc.client.RequestBuilder;
import com.wingflare.engine.task.client.retry.core.client.RetryClient;
import com.wingflare.engine.task.client.retry.core.context.CallbackContext;
import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.model.request.DispatchCallbackResultRequest;
import com.google.common.util.concurrent.FutureCallback;

import java.util.concurrent.CancellationException;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-11
 */
public class CallbackTaskExecutorFutureCallback implements FutureCallback<Boolean> {

    private static final RetryClient CLIENT = RequestBuilder.<RetryClient, TaskRpcResult>newBuilder()
            .client(RetryClient.class)
            .callback(nettyResult -> {
                if (nettyResult.getStatus() == StatusEnum.NO.getStatus()) {
                    TaskEngineLog.LOCAL.error("Retry callback execute result report successfully requestId:[{}]",
                            nettyResult.getReqId());
                }

            }).build();


    private final CallbackContext context;
    public CallbackTaskExecutorFutureCallback(CallbackContext context) {
        this.context = context;
    }

    @Override
    public void onSuccess(Boolean result) {
        try {
            DispatchCallbackResultRequest request = buildDispatchRetryResultRequest();
            request.setTaskStatus(RetryTaskStatusEnum.SUCCESS.getStatus());
            CLIENT.callbackResult(request);
        } catch (Exception e) {
            TaskEngineLog.REMOTE.error("Callback execution result reporting exception.[{}]", context.getRetryTaskId(), e);

        }

    }

    @Override
    public void onFailure(Throwable t) {
        if (t instanceof CancellationException) {
            TaskEngineLog.LOCAL.debug("The task has been canceled, no status feedback will be made");
            return;
        }
        try {
            DispatchCallbackResultRequest request = buildDispatchRetryResultRequest();
            request.setTaskStatus(RetryTaskStatusEnum.FAIL.getStatus());
            request.setExceptionMsg(t.getMessage());
            CLIENT.callbackResult(request);
        } catch (Exception e) {
            TaskEngineLog.REMOTE.error("Callback execution result reporting exception.[{}]", context.getRetryTaskId(), e);
        }
    }

    private DispatchCallbackResultRequest buildDispatchRetryResultRequest() {
        DispatchCallbackResultRequest request = new DispatchCallbackResultRequest();
        request.setRetryTaskId(context.getRetryTaskId());
        request.setNamespaceId(context.getNamespaceId());
        request.setGroupName(context.getGroupName());
        request.setSceneName(context.getSceneName());
        request.setRetryId(context.getRetryId());
        request.setRetryTaskId(context.getRetryTaskId());
        return request;
    }
}
