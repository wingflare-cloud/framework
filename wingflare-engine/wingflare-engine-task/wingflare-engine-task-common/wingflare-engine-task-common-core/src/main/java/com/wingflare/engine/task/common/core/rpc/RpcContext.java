package com.wingflare.engine.task.common.core.rpc;

import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.wingflare.engine.task.common.core.exception.TaskRemotingTimeOutException;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.core.model.TaskRpcResult;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * 处理RPC超时和回调
 *
 * @author: opensnail
 * @date : 2023-05-12 09:05
 * @since 1.3.0
 */
public final class RpcContext {

    private RpcContext() {
    }

    private static final HashedWheelTimer WHEEL_TIMER;

    static {
        WHEEL_TIMER = new HashedWheelTimer(
                new CustomizableThreadFactory("wingflare-task-rpc-timeout-"), 1,
                TimeUnit.SECONDS, 1024);
    }

    private static final ConcurrentMap<Long, TaskFuture> COMPLETABLE_FUTURE = new ConcurrentHashMap<>();

    public static void invoke(Long requestId, TaskRpcResult taskRpcResult, boolean timeout) {

        try {
            // 同步请求同步返回
            Optional.ofNullable(COMPLETABLE_FUTURE.remove(requestId))
                    .ifPresent(future -> {
                        if (timeout) {
                            future.completeExceptionally(new TaskRemotingTimeOutException("Request to remote interface timed out."));
                        } else {
                            future.complete(taskRpcResult);
                        }
                    });

        } catch (Exception e) {
            TaskEngineLog.LOCAL.error("Callback processing failed requestId:[{}]", requestId, e);
        }
    }

    public static <R extends Result<Object>> void setFuture(TaskFuture<R> future) {
        if (Objects.nonNull(future)) {
            COMPLETABLE_FUTURE.put(future.getRequestId(), future);
        }

        // 放入时间轮
        WHEEL_TIMER.newTimeout(new TimeoutCheckTask(future.getRequestId()), future.getTimeout(), future.getUnit());
    }

    public static class TimeoutCheckTask implements TimerTask {

        private final Long requestId;

        public TimeoutCheckTask(Long requestId) {
            this.requestId = requestId;
        }

        @Override
        public void run(final Timeout timeout) throws Exception {
            invoke(requestId, new TaskRpcResult(StatusEnum.NO.getStatus(), "Request to remote interface timed out.", null, requestId), true);
        }
    }

}
