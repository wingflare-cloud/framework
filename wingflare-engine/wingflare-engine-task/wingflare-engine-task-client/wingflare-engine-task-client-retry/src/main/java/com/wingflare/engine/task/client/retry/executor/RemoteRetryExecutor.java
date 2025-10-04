package com.wingflare.engine.task.client.retry.executor;


import com.wingflare.engine.task.client.common.log.support.TaskLogManager;
import com.wingflare.engine.task.client.retry.context.RemoteRetryContext;
import com.wingflare.engine.task.client.retry.intercepter.RetrySiteSnapshot;
import com.wingflare.engine.task.client.retry.log.RetryLogMeta;
import com.wingflare.engine.task.client.retry.retryer.RetryerResultContext;
import com.wingflare.engine.task.client.retry.strategy.RemoteRetryStrategies;
import com.wingflare.engine.task.client.retry.strategy.RetryStrategy;
import com.wingflare.engine.task.common.core.enums.RetryResultStatusEnum;
import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;
import com.wingflare.engine.task.common.model.dto.DispatchRetryResultDTO;

import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-11
 */
public class RemoteRetryExecutor {
    private final RetryStrategy remoteRetryStrategies;

    public RemoteRetryExecutor(RemoteRetryStrategies remoteRetryStrategies) {
        this.remoteRetryStrategies = remoteRetryStrategies;
    }

    /**
     * 执行远程重试
     *
     * @param context 重试上下文
     * @return
     */
    public DispatchRetryResultDTO doRetry(RemoteRetryContext context) {
        DispatchRetryResultDTO executeRespDto = new DispatchRetryResultDTO();
        executeRespDto.setRetryId(context.getRetryId());
        executeRespDto.setRetryTaskId(context.getRetryTaskId());
        executeRespDto.setNamespaceId(context.getNamespaceId());
        executeRespDto.setGroupName(context.getGroupName());
        executeRespDto.setSceneName(context.getScene());

        try {
            // 初始化实时日志上下文
            initLogContext(context);

            RetryerResultContext retryerResultContext = remoteRetryStrategies.openRetry(context.getScene(),
                    context.getExecutorName(), context.getDeSerialize());

            if (RetrySiteSnapshot.isRetryForStatusCode()) {
                executeRespDto.setStatusCode(RetryResultStatusEnum.STOP.getStatus());
                executeRespDto.setExceptionMsg("Downstream marks no need for retry");
            } else {
                RetryResultStatusEnum retryResultStatusEnum = retryerResultContext.getRetryResultStatusEnum();
                if (Objects.isNull(retryResultStatusEnum)) {
                    retryResultStatusEnum = RetryResultStatusEnum.STOP;
                    retryerResultContext.setMessage("No retry status obtained. Task stopped");
                }

                executeRespDto.setStatusCode(retryResultStatusEnum.getStatus());
                executeRespDto.setExceptionMsg(retryerResultContext.getMessage());
            }

            if (Objects.nonNull(retryerResultContext.getResult())) {
                executeRespDto.setResultJson(JsonUtil.toJsonString(retryerResultContext.getResult()));
            }

            Integer retryCount = context.getRetryCount() + 1;
            if (Objects.equals(RetryResultStatusEnum.SUCCESS.getStatus(), executeRespDto.getStatusCode())) {
                TaskEngineLog.REMOTE.info("remote retry【SUCCESS】. retryTaskId:[{}] count:[{}] result:[{}]",
                        context.getRetryTaskId(), retryCount, executeRespDto.getResultJson());
            } else if (Objects.equals(RetryResultStatusEnum.STOP.getStatus(), executeRespDto.getStatusCode())) {
                TaskEngineLog.REMOTE.warn("remote retry 【STOP】.retryTaskId:[{}] count:[{}]  exceptionMsg:[{}]",
                        context.getRetryTaskId(), retryCount, executeRespDto.getExceptionMsg());
            } else if (Objects.equals(RetryResultStatusEnum.FAILURE.getStatus(), executeRespDto.getStatusCode())) {
                TaskEngineLog.REMOTE.error("remote retry 【FAILURE】. retryTaskId:[{}] count:[{}] ",
                        context.getRetryTaskId(), retryCount, retryerResultContext.getThrowable());
            } else {
                TaskEngineLog.REMOTE.error("remote retry 【UNKNOWN】. retryTaskId:[{}] count:[{}] result:[{}]",
                        context.getRetryTaskId(), retryCount, executeRespDto.getResultJson(),
                        retryerResultContext.getThrowable());
            }

        } finally {
            RetrySiteSnapshot.removeAll();
            TaskLogManager.removeAll();
        }

        return executeRespDto;
    }

    private static void initLogContext(RemoteRetryContext context) {
        RetryLogMeta retryLogMeta = new RetryLogMeta();
        retryLogMeta.setGroupName(context.getGroupName());
        retryLogMeta.setNamespaceId(context.getNamespaceId());
        retryLogMeta.setRetryId(context.getRetryId());
        retryLogMeta.setRetryTaskId(context.getRetryTaskId());
        TaskLogManager.initLogInfo(retryLogMeta, LogTypeEnum.RETRY);
    }
}
