package com.wingflare.engine.task.client.retry.timer;


import com.wingflare.api.threadpool.ThreadPoolManageDrive;
import com.wingflare.lib.container.Container;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;


import java.util.concurrent.TimeUnit;

/**
 * @author opensnail
 * @date 2023-10-08 22:23:57
 * @since 2.4.0
 */
public class TimerManager {

    private static class Holder {
        private static final HashedWheelTimer INSTANCE = new HashedWheelTimer(
                Container.get(ThreadPoolManageDrive.class).factory("retry-task-timer-wheel"),
                1, TimeUnit.SECONDS, 1024);
    }

    private TimerManager() {
    }

    public static Timeout add(TimerTask task, long delay, TimeUnit unit) {
        return Holder.INSTANCE.newTimeout(task, delay, unit);
    }
}
