package com.wingflare.lib.threadpool;


import com.wingflare.api.threadpool.ThreadPoolManageDrive;
import com.wingflare.lib.config.ConfigUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import com.wingflare.lib.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 可通过配置的线程池管理驱动实现
 * 支持配置处理剩余任务策略
 */
public class WFThreadPoolManage implements ThreadPoolManageDrive {

    private static final Logger logger = LoggerFactory.getLogger(WFThreadPoolManage.class);

    private static final String DEFAULT_POOL_KEY = "default";
    private static final String THREAD_POOL_CONFIG_PREFIX = "thread.pool.";
    private static final String CORE_POOL_SIZE_CONFIG_KEY = "core.size";
    private static final String MAX_POOL_SIZE_CONFIG_KEY = "max.size";
    private static final String QUEUE_CAPACITY_CONFIG_KEY = "queue.capacity";
    private static final String KEEP_ALIVE_TIME_CONFIG_KEY = "keep.alive.time";
    private static final String TIME_UNIT_CONFIG_KEY = "time.unit";
    private static final String ALLOW_CORE_THREAD_TIMEOUT_KEY = "allow.core.thread.timeout";

    // 新增：剩余任务处理策略配置键
    private static final String REMAINING_TASKS_HANDLER_CONFIG_KEY = "remaining.tasks.handler";
    private static final String REMAINING_TASKS_TIMEOUT_CONFIG_KEY = "remaining.tasks.timeout";

    // 剩余任务处理策略枚举
    public enum RemainingTasksHandler {
        IGNORE,          // 忽略剩余任务
        WAIT_COMPLETION, // 等待剩余任务完成
        LOG_AND_IGNORE   // 记录日志后忽略
    }

    private final Map<String, DynamicThreadPool> threadPools = new ConcurrentHashMap<>();
    private final Map<String, AtomicBoolean> poolShutdownStatus = new ConcurrentHashMap<>();
    private final ThreadFactory threadFactory;
    private final Map<String, ThreadFactory> threadFactories = new ConcurrentHashMap<>();

    // 默认配置
    private static final int DEFAULT_CORE_POOL_SIZE = 10;
    private static final int DEFAULT_MAX_POOL_SIZE = 20;
    private static final int DEFAULT_QUEUE_CAPACITY = 1000;
    private static final long DEFAULT_KEEP_ALIVE_TIME = 60;
    private static final boolean DEFAULT_KEEP_ALIVE_TIME_CONFIG = false;
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final RemainingTasksHandler DEFAULT_REMAINING_TASKS_HANDLER = RemainingTasksHandler.WAIT_COMPLETION;
    private static final long DEFAULT_REMAINING_TASKS_TIMEOUT = 30; // 30秒

    public WFThreadPoolManage() {
        if (Container.has(ThreadFactory.class)) {
            this.threadFactory = Container.get(ThreadFactory.class);
        } else {
            this.threadFactory = Executors.defaultThreadFactory();
        }
    }

    public void registerThreadFactory(String key, ThreadFactory threadFactory) {
        if (key != null && threadFactory != null) {
            threadFactories.put(key, threadFactory);
        }
    }

    /**
     * 获取剩余任务处理策略配置
     */
    private RemainingTasksHandler getRemainingTasksHandler(String poolKey) {
        // 特定线程池配置优先
        String specificKey = THREAD_POOL_CONFIG_PREFIX + poolKey + "." + REMAINING_TASKS_HANDLER_CONFIG_KEY;
        String handlerStr = ConfigUtil.getProperty(specificKey);

        // 全局配置次之
        if (handlerStr == null) {
            String globalKey = THREAD_POOL_CONFIG_PREFIX + REMAINING_TASKS_HANDLER_CONFIG_KEY;
            handlerStr = ConfigUtil.getProperty(globalKey);
        }

        // 解析配置值
        if (handlerStr != null) {
            try {
                return RemainingTasksHandler.valueOf(handlerStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid remaining tasks handler configuration '{}' for pool '{}', using default",
                        handlerStr, poolKey);
            }
        }

        return DEFAULT_REMAINING_TASKS_HANDLER;
    }

    /**
     * 获取剩余任务处理超时配置
     */
    private long getRemainingTasksTimeout(String poolKey) {
        // 特定线程池配置优先
        Long timeout = getPoolConfigLong(poolKey, REMAINING_TASKS_TIMEOUT_CONFIG_KEY, DEFAULT_REMAINING_TASKS_TIMEOUT);
        return timeout != null ? timeout : DEFAULT_REMAINING_TASKS_TIMEOUT;
    }

    private Integer getPoolConfigInt(String poolKey, String configKey, Integer defaultValue) {
        String specificKey = THREAD_POOL_CONFIG_PREFIX + poolKey + "." + configKey;
        Long value = ConfigUtil.getLongProperty(specificKey);

        if (value == null) {
            String globalKey = THREAD_POOL_CONFIG_PREFIX + configKey;
            value = ConfigUtil.getLongProperty(globalKey, Long.valueOf(defaultValue));
        }

        return value.intValue();
    }

    private Long getPoolConfigLong(String poolKey, String configKey, Long defaultValue) {
        String specificKey = THREAD_POOL_CONFIG_PREFIX + poolKey + "." + configKey;
        String valueStr = ConfigUtil.getProperty(specificKey);

        if (valueStr != null) {
            try {
                return Long.parseLong(valueStr);
            } catch (NumberFormatException e) {
                logger.warn("Invalid long value '{}' for config '{}' in pool '{}'",
                        valueStr, configKey, poolKey);
            }
        }

        String globalKey = THREAD_POOL_CONFIG_PREFIX + configKey;
        valueStr = ConfigUtil.getProperty(globalKey);

        if (valueStr != null) {
            try {
                return Long.parseLong(valueStr);
            } catch (NumberFormatException e) {
                logger.warn("Invalid long value '{}' for global config '{}'", valueStr, configKey);
            }
        }

        return defaultValue;
    }

    private Boolean getPoolConfigBool(String poolKey, String configKey, Boolean defaultValue) {
        String specificKey = THREAD_POOL_CONFIG_PREFIX + poolKey + "." + configKey;
        String valueStr = ConfigUtil.getProperty(specificKey);

        if (valueStr != null) {
            try {
                return Boolean.parseBoolean(valueStr);
            } catch (NumberFormatException e) {
                logger.warn("Invalid long value '{}' for config '{}' in pool '{}'",
                        valueStr, configKey, poolKey);
            }
        }

        String globalKey = THREAD_POOL_CONFIG_PREFIX + configKey;
        valueStr = ConfigUtil.getProperty(globalKey);

        if (valueStr != null) {
            try {
                return Boolean.parseBoolean(valueStr);
            } catch (NumberFormatException e) {
                logger.warn("Invalid long value '{}' for global config '{}'", valueStr, configKey);
            }
        }

        return defaultValue;
    }

    private TimeUnit getTimeUnit(String poolKey) {
        String specificKey = THREAD_POOL_CONFIG_PREFIX + poolKey + "." + TIME_UNIT_CONFIG_KEY;
        String timeUnitStr = ConfigUtil.getProperty(specificKey);

        if (timeUnitStr == null) {
            String globalKey = THREAD_POOL_CONFIG_PREFIX + TIME_UNIT_CONFIG_KEY;
            timeUnitStr = ConfigUtil.getProperty(globalKey);
        }

        if (timeUnitStr != null) {
            try {
                return TimeUnit.valueOf(timeUnitStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                logger.warn("Invalid time unit '{}' for pool '{}', using default", timeUnitStr, poolKey);
            }
        }

        return DEFAULT_TIME_UNIT;
    }

    private DynamicThreadPool createThreadPool(String poolKey) {
        int corePoolSize = getPoolConfigInt(poolKey, CORE_POOL_SIZE_CONFIG_KEY, DEFAULT_CORE_POOL_SIZE);
        int maxPoolSize = getPoolConfigInt(poolKey, MAX_POOL_SIZE_CONFIG_KEY, DEFAULT_MAX_POOL_SIZE);
        int queueCapacity = getPoolConfigInt(poolKey, QUEUE_CAPACITY_CONFIG_KEY, DEFAULT_QUEUE_CAPACITY);
        long keepAliveTime = getPoolConfigLong(poolKey, KEEP_ALIVE_TIME_CONFIG_KEY, DEFAULT_KEEP_ALIVE_TIME);
        boolean allowCoreThreadTimeout = getPoolConfigBool(poolKey, ALLOW_CORE_THREAD_TIMEOUT_KEY, DEFAULT_KEEP_ALIVE_TIME_CONFIG);
        TimeUnit timeUnit = getTimeUnit(poolKey);

        // 确保核心线程数不大于最大线程数
        corePoolSize = Math.min(corePoolSize, maxPoolSize);

        logger.info("creating.thread.pool [name={}, coreSize={}, maxSize={}, queueCapacity={}, keepAliveTime={}{}]",
                poolKey, corePoolSize, maxPoolSize, queueCapacity, keepAliveTime, timeUnit);

        return new DynamicThreadPool(
                new DynamicThreadPoolConfig(corePoolSize, maxPoolSize, queueCapacity, keepAliveTime,
                        timeUnit, allowCoreThreadTimeout, poolKey)
        );
    }

    public ThreadPoolExecutor getThreadPool() {
        return getThreadPool(null);
    }

    public ThreadPoolExecutor getThreadPool(String key) {
        String poolKey = key != null ? key : DEFAULT_POOL_KEY;

        AtomicBoolean shutdownStatus = poolShutdownStatus.computeIfAbsent(poolKey, k -> new AtomicBoolean(false));

        if (shutdownStatus.get()) {
            throw new IllegalStateException("ThreadPool with key '" + poolKey + "' is shutting down or already shut down");
        }

        DynamicThreadPool threadPool = threadPools.get(poolKey);

        if (threadPool == null || threadPool.isShutdown() || threadPool.isTerminated()) {
            DynamicThreadPool newThreadPool = createThreadPool(poolKey);
            threadPool = threadPools.putIfAbsent(poolKey, newThreadPool);

            if (threadPool == null) {
                threadPool = newThreadPool;
            } else {
                newThreadPool.shutdown();
            }
        }

        return threadPool.getExecutor();
    }

    /**
     * 处理剩余任务，根据配置的策略执行不同操作
     */
    private void handleRemainingTasks(String poolKey, List<Runnable> remainingTasks, DynamicThreadPool threadPool) {

        if (remainingTasks == null || remainingTasks.isEmpty()) {
            logger.debug("No remaining tasks in pool '{}'", poolKey);
            return;
        }

        RemainingTasksHandler handler = getRemainingTasksHandler(poolKey);

        logger.info("Handling {} remaining tasks in pool '{}' with strategy: {}",
                remainingTasks.size(), poolKey, handler);

        switch (handler) {
            case IGNORE:
                // 直接忽略剩余任务
                break;
            case WAIT_COMPLETION:
                // 等待剩余任务完成
                long timeout = getRemainingTasksTimeout(poolKey);
                try {
                    logger.debug("Waiting up to {} seconds for remaining tasks in pool '{}' to complete",
                            timeout, poolKey);
                    if (!threadPool.awaitTermination(timeout, TimeUnit.SECONDS)) {
                        logger.warn("Timeout waiting for remaining tasks in pool '{}' to complete", poolKey);
                        List<Runnable> stillRemaining = threadPool.shutdownNow();
                        logger.warn("{} tasks still remaining after timeout in pool '{}'",
                                stillRemaining.size(), poolKey);
                    } else {
                        logger.debug("All remaining tasks in pool '{}' completed successfully", poolKey);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.warn("Interrupted while waiting for remaining tasks in pool '{}'", poolKey, e);
                    threadPool.shutdownNow();
                }
                break;

            case LOG_AND_IGNORE:
                // 记录日志后忽略
                logger.warn("{} remaining tasks in pool '{}' are being ignored", remainingTasks.size(), poolKey);
                // 可以在这里添加更详细的日志，例如任务信息
                break;
        }
    }

    /**
     * 关闭指定的线程池，根据配置处理剩余任务
     */
    public void shutdownThreadPool(String key) {
        String poolKey = key != null ? key : DEFAULT_POOL_KEY;

        logger.info("Initiating shutdown for thread pool '{}'", poolKey);

        AtomicBoolean shutdownStatus = poolShutdownStatus.get(poolKey);
        if (shutdownStatus != null && shutdownStatus.compareAndSet(false, true)) {
            DynamicThreadPool threadPool = threadPools.remove(poolKey);
            if (threadPool != null) {
                try {
                    // 首先尝试优雅关闭
                    threadPool.shutdown();

                    // 获取并处理剩余任务
                    List<Runnable> remainingTasks = new ArrayList<>();
                    if (threadPool.getExecutor() instanceof ThreadPoolExecutor threadPoolExecutor) {
                        remainingTasks = new ArrayList<>(threadPoolExecutor.getQueue());
                    }

                    // 根据配置处理剩余任务
                    handleRemainingTasks(poolKey, remainingTasks, threadPool);

                } finally {
                    // 确保即使处理过程中出现异常，状态也会被清理
                    poolShutdownStatus.remove(poolKey);
                    logger.info("Shutdown process completed for thread pool '{}'", poolKey);
                }
            } else {
                logger.warn("No thread pool found with key '{}' during shutdown", poolKey);
                poolShutdownStatus.remove(poolKey);
            }
        } else {
            logger.debug("Thread pool '{}' is already shutting down or has been shut down", poolKey);
        }
    }

    /**
     * 关闭所有线程池
     */
    public void shutdownAll() {
        logger.info("Initiating shutdown for all thread pools");
        List<String> keys = new ArrayList<>(threadPools.keySet());

        for (String key : keys) {
            shutdownThreadPool(key);
        }

        threadPools.clear();
        poolShutdownStatus.clear();
        logger.info("All thread pools shutdown process completed");
    }

    // 以下是接口中定义的方法实现（保持不变）
    @Override
    public void execute(Runnable command) {
        getThreadPool(DEFAULT_POOL_KEY).execute(command);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return getThreadPool(DEFAULT_POOL_KEY).submit(task);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return getThreadPool(DEFAULT_POOL_KEY).submit(task, result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        return getThreadPool(DEFAULT_POOL_KEY).submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return getThreadPool(DEFAULT_POOL_KEY).invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        return getThreadPool(DEFAULT_POOL_KEY).invokeAll(tasks, timeout, unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return getThreadPool(DEFAULT_POOL_KEY).invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return getThreadPool(DEFAULT_POOL_KEY).invokeAny(tasks, timeout, unit);
    }

    @Override
    public void execute(String key, Runnable command) {
        getThreadPool(key).execute(command);
    }

    @Override
    public <T> Future<T> submit(String key, Callable<T> task) {
        return getThreadPool(key).submit(task);
    }

    @Override
    public <T> Future<T> submit(String key, Runnable task, T result) {
        return getThreadPool(key).submit(task, result);
    }

    @Override
    public Future<?> submit(String key, Runnable task) {
        return getThreadPool(key).submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(String key, Collection<? extends Callable<T>> tasks)
            throws InterruptedException {
        return getThreadPool(key).invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(String key, Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException {
        return getThreadPool(key).invokeAll(tasks, timeout, unit);
    }

    @Override
    public <T> T invokeAny(String key, Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        return getThreadPool(key).invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(String key, Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException {
        return getThreadPool(key).invokeAny(tasks, timeout, unit);
    }

    @Override
    public ThreadFactory factory() {
        return threadFactory;
    }

    @Override
    public ThreadFactory factory(String key) {
        return threadFactories.getOrDefault(key, threadFactory);
    }

}
