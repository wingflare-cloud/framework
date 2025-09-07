package com.wingflare.engine.task.server.common.schedule;


import cn.hutool.core.lang.Assert;
import com.wingflare.api.lock.RemoveLockDrive;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.Schedule;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.lib.container.Container;
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
        String lockTimeout = lockTimeout();
        String lockExpire = lockExpire();
        Assert.notBlank(lockTimeout, () -> new TaskServerException("lockTimeout can not be null."));
        Assert.notBlank(lockExpire, () -> new TaskServerException("lockExpire can not be null."));
        Assert.notBlank(lockName, () -> new TaskServerException("lockName can not be null."));

        RemoveLockDrive lockDrive = Container.get(RemoveLockDrive.class);

        boolean lock = false;
        try {
            lock = lockDrive.tryLock(lockName, Duration.parse(lockTimeout), Duration.parse(lockExpire));
            if (lock) {
                doExecute();
            }
        } catch (Exception e) {
            TaskEngineLog.LOCAL.error(this.getClass().getName() + " execute error. lockName:[{}]", lockName, e);
        } finally {
            if (lock) {
                lockDrive.unlock();
            }
        }

    }

    protected abstract void doExecute();

    protected abstract String lockName();

    protected abstract String lockTimeout();

    protected abstract String lockExpire();


}
