package com.wingflare.lib.scheduler;


/**
 * 任务调度器配置类，用于初始化静态调度器
 */
public class TaskSchedulerConfig {

    private long tickMs = 100;
    private int wheelSize = 60;
    private int levels = 3;
    private ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
    private TaskExceptionHandler exceptionHandler = new DefaultExceptionHandler();

    public TaskSchedulerConfig tickMs(long tickMs) {
        this.tickMs = tickMs;
        return this;
    }

    public TaskSchedulerConfig wheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
        return this;
    }

    public TaskSchedulerConfig levels(int levels) {
        this.levels = levels;
        return this;
    }

    public TaskSchedulerConfig threadPoolConfig(ThreadPoolConfig config) {
        this.threadPoolConfig = config;
        return this;
    }

    public TaskSchedulerConfig exceptionHandler(TaskExceptionHandler handler) {
        this.exceptionHandler = handler;
        return this;
    }

    // Getters
    long getTickMs() { return tickMs; }
    int getWheelSize() { return wheelSize; }
    int getLevels() { return levels; }
    ThreadPoolConfig getThreadPoolConfig() { return threadPoolConfig; }
    TaskExceptionHandler getExceptionHandler() { return exceptionHandler; }

}
