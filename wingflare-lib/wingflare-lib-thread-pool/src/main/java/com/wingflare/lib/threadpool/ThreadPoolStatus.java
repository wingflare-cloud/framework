package com.wingflare.lib.threadpool;


/**
 * 线程池状态信息类
 */
public record ThreadPoolStatus(int corePoolSize, int maximumPoolSize, int poolSize, int activeCount,
                               int largestPoolSize, long taskCount, long completedTaskCount, int queueSize,
                               int queueRemainingCapacity, long keepAliveTimeMs, boolean allowCoreThreadTimeOut) {

    @Override
    public String toString() {
        return "ThreadPoolStatus{" +
                "corePoolSize=" + corePoolSize +
                ", maximumPoolSize=" + maximumPoolSize +
                ", poolSize=" + poolSize +
                ", activeCount=" + activeCount +
                ", largestPoolSize=" + largestPoolSize +
                ", taskCount=" + taskCount +
                ", completedTaskCount=" + completedTaskCount +
                ", queueSize=" + queueSize +
                ", queueRemainingCapacity=" + queueRemainingCapacity +
                ", keepAliveTimeMs=" + keepAliveTimeMs +
                ", allowCoreThreadTimeOut=" + allowCoreThreadTimeOut +
                '}';
    }
}
