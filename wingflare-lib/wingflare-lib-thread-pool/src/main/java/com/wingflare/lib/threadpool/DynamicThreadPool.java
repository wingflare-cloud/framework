package com.wingflare.lib.threadpool;


import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 可动态调整配置的线程池
 * 支持动态修改核心线程数、最大线程数、队列大小和拒绝策略
 */
public class DynamicThreadPool {
    // 线程池核心实现
    private final ThreadPoolExecutor executor;
    // 用于生成线程名称的计数器
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    // 线程组
    private final ThreadGroup threadGroup;

    /**
     * 构造函数
     *
     * @param config    动态线程池配置
     */
    public DynamicThreadPool(DynamicThreadPoolConfig config) {
        this.threadGroup = new ThreadGroup(config.namePrefix() + "-group");
        // 创建工作队列
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(config.queueCapacity());
        // 创建线程工厂
        ThreadFactory threadFactory = runnable -> {
            Thread thread = new Thread(threadGroup, runnable,
                    config.namePrefix() + "-thread-" + threadNumber.getAndIncrement(), 0);
            // 设置默认优先级
            if (thread.getPriority() != Thread.NORM_PRIORITY) {
                thread.setPriority(Thread.NORM_PRIORITY);
            }
            return thread;
        };

        // 默认拒绝策略：记录日志并抛出异常
        RejectedExecutionHandler defaultHandler = (r, e) -> {
            throw new RejectedExecutionException("Task " + r + " rejected from " + e);
        };

        this.executor = new ThreadPoolExecutor(
                config.corePoolSize(),
                config.maximumPoolSize(),
                config.keepAliveTime(),
                config.timeUnit(),
                workQueue,
                threadFactory,
                defaultHandler
        );

        // 允许核心线程超时退出
        this.executor.allowCoreThreadTimeOut(false);
    }

    /**
     * 动态调整核心线程数
     *
     * @param corePoolSize 新的核心线程数
     */
    public void setCorePoolSize(int corePoolSize) {
        if (corePoolSize < 0) {
            throw new IllegalArgumentException("Core pool size cannot be negative");
        }
        executor.setCorePoolSize(corePoolSize);
    }

    /**
     * 动态调整最大线程数
     *
     * @param maximumPoolSize 新的最大线程数
     */
    public void setMaximumPoolSize(int maximumPoolSize) {
        if (maximumPoolSize < executor.getCorePoolSize()) {
            throw new IllegalArgumentException("Maximum pool size cannot be less than core pool size");
        }
        executor.setMaximumPoolSize(maximumPoolSize);
    }

    /**
     * 动态调整空闲线程存活时间
     *
     * @param keepAliveTime 新的存活时间
     * @param unit          时间单位
     */
    public void setKeepAliveTime(long keepAliveTime, TimeUnit unit) {
        if (keepAliveTime < 0) {
            throw new IllegalArgumentException("Keep alive time cannot be negative");
        }
        if (unit == null) {
            throw new NullPointerException("Time unit cannot be null");
        }
        executor.setKeepAliveTime(keepAliveTime, unit);
    }

    /**
     * 动态调整拒绝策略
     *
     * @param handler 新的拒绝策略
     */
    public void setRejectedExecutionHandler(RejectedExecutionHandler handler) {
        if (handler == null) {
            throw new NullPointerException("Rejected execution handler cannot be null");
        }
        executor.setRejectedExecutionHandler(handler);
    }

    /**
     * 动态调整是否允许核心线程超时
     *
     * @param allowCoreThreadTimeOut 是否允许核心线程超时
     */
    public void allowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        executor.allowCoreThreadTimeOut(allowCoreThreadTimeOut);
    }

    /**
     * 提交任务并返回Future
     *
     * @param task 任务
     * @return Future对象
     */
    public <T> Future<T> submit(Callable<T> task) {
        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }
        return executor.submit(task);
    }

    /**
     * 提交Runnable任务
     *
     * @param task 任务
     * @return Future对象
     */
    public Future<?> submit(Runnable task) {
        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }
        return executor.submit(task);
    }

    /**
     * 提交Runnable任务并指定返回值
     *
     * @param task  任务
     * @param result 返回值
     * @return Future对象
     */
    public <T> Future<T> submit(Runnable task, T result) {
        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }
        return executor.submit(task, result);
    }

    /**
     * 执行任务
     *
     * @param command 任务
     */
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException("Command cannot be null");
        }
        executor.execute(command);
    }

    /**
     * 关闭线程池，不再接受新任务，但会完成已提交的任务
     */
    public void shutdown() {
        executor.shutdown();
    }

    /**
     * 立即关闭线程池，尝试中断正在执行的任务，并返回未执行的任务
     *
     * @return 未执行的任务列表
     */
    public List<Runnable> shutdownNow() {
        return executor.shutdownNow();
    }

    /**
     * 等待线程池终止
     *
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return 如果线程池已终止返回true，否则返回false
     * @throws InterruptedException 中断异常
     */
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }

    /**
     * 检查线程池是否已关闭
     *
     * @return 如果已关闭返回true，否则返回false
     */
    public boolean isShutdown() {
        return executor.isShutdown();
    }

    /**
     * 检查线程池是否已终止
     *
     * @return 如果已终止返回true，否则返回false
     */
    public boolean isTerminated() {
        return executor.isTerminated();
    }

    /**
     * 获取实际线程池
     * @return
     */
    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    /**
     * 获取线程池当前状态信息
     *
     * @return 线程池状态信息
     */
    public ThreadPoolStatus getStatus() {
        return new ThreadPoolStatus(
                executor.getCorePoolSize(),
                executor.getMaximumPoolSize(),
                executor.getPoolSize(),
                executor.getActiveCount(),
                executor.getLargestPoolSize(),
                executor.getTaskCount(),
                executor.getCompletedTaskCount(),
                executor.getQueue().size(),
                executor.getQueue().remainingCapacity(),
                executor.getKeepAliveTime(TimeUnit.MILLISECONDS),
                executor.allowsCoreThreadTimeOut()
        );
    }


}