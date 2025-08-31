package com.wingflare.engine.task.server.common.schedule;

import cn.hutool.core.lang.Assert;
import com.wingflare.engine.task.common.log.SnailJobLog;
import com.wingflare.engine.task.server.common.Schedule;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.common.lock.LockBuilder;
import com.wingflare.engine.task.server.common.lock.LockManager;
import com.wingflare.engine.task.server.common.lock.LockProvider;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;

import java.time.Duration;

/**
 * @author: opensnail
 * @date : 2023-07-21 13:04
 * @since 2.1.0
 */
public abstract class AbstractSchedule implements Schedule {

    @Resource
    @Qualifier("scheduledExecutorService")
    protected TaskScheduler taskScheduler;

    @Override
    public void execute() {

        String lockName = lockName();
        String lockAtMost = lockAtMost();
        String lockAtLeast = lockAtLeast();
        Assert.notBlank(lockAtMost, () -> new TaskServerException("lockAtLeast can not be null."));
        Assert.notBlank(lockAtLeast, () -> new TaskServerException("lockAtLeast can not be null."));
        Assert.notBlank(lockName, () -> new TaskServerException("lockName can not be null."));

        LockProvider lockProvider = LockBuilder.newBuilder()
                .withResident(lockName)
                .build();
        boolean lock = false;
        try {
            lock = lockProvider.lock(Duration.parse(lockAtLeast), Duration.parse(lockAtMost));
            if (lock) {
                doExecute();
            }
        } catch (Exception e) {
            SnailJobLog.LOCAL.error(this.getClass().getName() + " execute error. lockName:[{}]", lockName, e);
        } finally {
            if (lock) {
                lockProvider.unlock();
            } else {
                LockManager.clear();
            }
        }

    }

    protected abstract void doExecute();

    protected abstract String lockName();

    protected abstract String lockAtMost();

    protected abstract String lockAtLeast();


}
