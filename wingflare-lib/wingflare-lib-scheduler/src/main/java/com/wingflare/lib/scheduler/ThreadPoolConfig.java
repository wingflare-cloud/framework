package com.wingflare.lib.scheduler;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池配置类
 */
public class ThreadPoolConfig {

    private int corePoolSize = Runtime.getRuntime().availableProcessors();
    private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 2;
    private long keepAliveTime = 60;
    private TimeUnit unit = TimeUnit.SECONDS;
    private BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1000);
    private ThreadFactory threadFactory = runnable -> {
        Thread thread = new Thread(runnable, "task-worker-" + System.currentTimeMillis());
        thread.setDaemon(true); // 设置为守护线程
        return thread;
    };
    private RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

    // Getters and setters
    public int getCorePoolSize() { return corePoolSize; }
    public void setCorePoolSize(int corePoolSize) { this.corePoolSize = corePoolSize; }
    public int getMaximumPoolSize() { return maximumPoolSize; }
    public void setMaximumPoolSize(int maximumPoolSize) { this.maximumPoolSize = maximumPoolSize; }
    public long getKeepAliveTime() { return keepAliveTime; }
    public void setKeepAliveTime(long keepAliveTime) { this.keepAliveTime = keepAliveTime; }
    public TimeUnit getUnit() { return unit; }
    public void setUnit(TimeUnit unit) { this.unit = unit; }
    public BlockingQueue<Runnable> getWorkQueue() { return workQueue; }
    public void setWorkQueue(BlockingQueue<Runnable> workQueue) { this.workQueue = workQueue; }
    public ThreadFactory getThreadFactory() { return threadFactory; }
    public void setThreadFactory(ThreadFactory threadFactory) { this.threadFactory = threadFactory; }
    public RejectedExecutionHandler getHandler() { return handler; }
    public void setHandler(RejectedExecutionHandler handler) { this.handler = handler; }

    public ExecutorService createExecutorService() {
        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler
        );
    }

}
