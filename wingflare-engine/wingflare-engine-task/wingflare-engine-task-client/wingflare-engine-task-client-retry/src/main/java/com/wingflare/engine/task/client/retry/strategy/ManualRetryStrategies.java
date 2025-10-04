package com.wingflare.engine.task.client.retry.strategy;


import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.StopStrategy;
import com.github.rholder.retry.WaitStrategies;
import com.github.rholder.retry.WaitStrategy;
import com.wingflare.engine.task.client.retry.RetryExecutor;
import com.wingflare.engine.task.client.retry.RetryExecutorParameter;
import com.wingflare.engine.task.client.retry.intercepter.RetrySiteSnapshot;
import com.wingflare.api.retry.enums.RetryType;
import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;
import com.wingflare.engine.task.client.retry.retryer.RetryerResultContext;
import com.wingflare.engine.task.common.core.enums.RetryResultStatusEnum;
import com.wingflare.engine.task.common.log.TaskEngineLog;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * 手动执行重试
 *
 * @author: opensnail
 * @date : 2023-05-15 18:19
 */
public class ManualRetryStrategies extends AbstractRetryStrategies {

    @Override
    protected void setStage() {
        RetrySiteSnapshot.setStage(RetrySiteSnapshot.EnumStage.MANUAL_REPORT.getStage());
    }

    @Override
    protected Consumer<Object> doRetrySuccessConsumer(final RetryerResultContext context) {
        return o -> {
            TaskEngineLog.LOCAL.debug("ManualRetryStrategies doRetrySuccessConsumer ");
        };
    }

    @Override
    protected void error(final RetryerResultContext context) {
        context.setRetryResultStatusEnum(RetryResultStatusEnum.FAILURE);
    }

    @Override
    protected boolean preValidator(final RetryerInfo retryerInfo, final RetryerResultContext resultContext) {

        if (retryerInfo.isForceReport()) {
            return true;
        }

        if (RetrySiteSnapshot.isRunning()) {
            resultContext.setRetryResultStatusEnum(RetryResultStatusEnum.FAILURE);
            resultContext.setMessage("Retry validation failed: reason: there is an ongoing retry task");
            return false;
        }

        return true;
    }

    @Override
    protected void unexpectedError(final Exception e, final RetryerResultContext retryerResultContext) {
        retryerResultContext.setRetryResultStatusEnum(RetryResultStatusEnum.FAILURE);
    }

    @Override
    protected void success(final RetryerResultContext retryerResultContext) {
        retryerResultContext.setRetryResultStatusEnum(RetryResultStatusEnum.SUCCESS);
    }

    @Override
    protected Consumer<Throwable> doGetRetryErrorConsumer(final RetryerInfo retryerInfo, final Object[] params) {
        return throwable -> {
            TaskEngineLog.LOCAL.debug("ManualRetryStrategies doGetRetryErrorConsumer ");
        };
    }

    @Override
    protected Callable doGetCallable(final RetryExecutor<WaitStrategy, StopStrategy> retryExecutor, Object[] params) {
        RetryerInfo retryerInfo = retryExecutor.getRetryerInfo();
        return () -> doReport(retryerInfo, params);

    }

    @Override
    protected RetryExecutorParameter<WaitStrategy, StopStrategy> getRetryExecutorParameter(RetryerInfo retryerInfo) {
        return new RetryExecutorParameter<WaitStrategy, StopStrategy>() {

            @Override
            public WaitStrategy backOff() {
                return WaitStrategies.fixedWait(500, TimeUnit.MILLISECONDS);
            }

            @Override
            public StopStrategy stop() {
                return StopStrategies.stopAfterAttempt(5);
            }

            @Override
            public List<RetryListener> getRetryListeners() {
                return Collections.singletonList(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        if (attempt.hasResult()) {
                            TaskEngineLog.LOCAL.info("wingflare-task manually created retry data successfully, scheduled for the [{}] time", attempt.getAttemptNumber());
                        }

                        if (attempt.hasException()) {
                            TaskEngineLog.LOCAL.error(" Manually creating retry data for wingflare-task failed, attempt [{}]", attempt.getAttemptNumber(),
                                    attempt.getExceptionCause());
                        }

                    }
                });
            }
        };
    }

    @Override
    public boolean supports(final int stage, final RetryType retryType) {
        return RetrySiteSnapshot.EnumStage.MANUAL_REPORT.getStage() == stage;
    }
}
