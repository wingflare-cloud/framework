package com.wingflare.engine.task.server.job.support.handler;

import com.wingflare.api.lock.RemoveLockDrive;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.job.support.LockExecutor;
import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.wingflare.lib.container.Container;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁工具类
 *
 * @author: xiaowoniu
 * @date : 2024-01-02
 * @since : 2.6.0
 */
@Component
public class DistributedLockHandler {

    /**
     * 获取分布式锁并支持重试
     *
     * @param lockExecutor  执行器
     * @param lockName      锁名称
     * @param expire    锁超时时间
     * @param sleepTime     重试间隔
     * @param maxRetryTimes 重试次数
     */
    public void lockWithDisposableAndRetry(LockExecutor lockExecutor,
                                           String lockName, Duration expire,
                                           Duration sleepTime, Integer maxRetryTimes) {
        RemoveLockDrive lockProvider = Container.get(RemoveLockDrive.class);

        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(result -> result.equals(Boolean.FALSE))
                .retryIfException(ex -> true)
                .withWaitStrategy(WaitStrategies.randomWait(sleepTime.toMillis(), TimeUnit.MILLISECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(maxRetryTimes))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(final Attempt<V> attempt) {
                        Object result = null;
                        if (attempt.hasResult()) {
                            try {
                                result = attempt.get();
                            } catch (ExecutionException ignored) {
                            }
                        }

                        TaskEngineLog.LOCAL.debug("Attempt [{}] to acquire lock. Lock name:[{}] Result:[{}] Thread name:[{}]",
                                attempt.getAttemptNumber(), lockName, result, Thread.currentThread().getName());
                    }
                }).build();

        boolean lock = false;
        try {
            lock = retryer.call(() -> lockProvider.tryLockNoTimeOut(lockName, expire));
            if (lock) {
                lockExecutor.execute();
            }
        } catch (Exception e) {
            Throwable throwable = e;
            if (e.getClass().isAssignableFrom(RetryException.class)) {
                RetryException re = (RetryException) e;
                Attempt<?> lastFailedAttempt = re.getLastFailedAttempt();
                if (lastFailedAttempt.hasException()) {
                    throwable = lastFailedAttempt.getExceptionCause();
                }
            }

            TaskEngineLog.LOCAL.warn("lock execute error. lockName:[{}]", lockName, throwable);
        } finally {
            if (lock) {
                lockProvider.unlock();
                TaskEngineLog.LOCAL.debug("[{}] Lock has been released", lockName);
            }
        }

    }

}
