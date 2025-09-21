package com.wingflare.lib.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 高性能任务调度器
 * 采用多线程模型：时间轮推进线程 + 任务执行线程池
 * 支持Cron、固定延时、固定频率、一次性延时任务
 */
public class TaskScheduler extends TimingWheel {
    
    // 默认配置
    private static final int DEFAULT_CORE_POOL_SIZE = Math.max(2, Runtime.getRuntime().availableProcessors());
    private static final int DEFAULT_MAX_POOL_SIZE = DEFAULT_CORE_POOL_SIZE * 4;
    private static final int DEFAULT_QUEUE_CAPACITY = 1000;
    
    // 调度器状态
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean shutdown = new AtomicBoolean(false);
    
    // 线程池配置
    private final int corePoolSize;
    private final int maxPoolSize;
    private final int queueCapacity;
    
    // 线程池
    private ExecutorService taskExecutor;
    private ScheduledExecutorService wheelAdvancer;
    private ExecutorService timeoutChecker;
    
    // 任务队列（用于解耦时间轮和任务执行）
    private final BlockingQueue<TaskNode> readyTaskQueue;
    
    // 任务分发线程
    private Thread taskDispatcher;
    
    // 统计信息
    private final AtomicLong totalSubmittedTasks = new AtomicLong(0);
    private final AtomicLong totalExecutedTasks = new AtomicLong(0);
    private final AtomicLong totalFailedTasks = new AtomicLong(0);
    private final AtomicLong totalCancelledTasks = new AtomicLong(0);
    
    /**
     * 使用默认配置创建调度器
     */
    public TaskScheduler() {
        this(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE, DEFAULT_QUEUE_CAPACITY);
    }
    
    /**
     * 创建调度器
     */
    public TaskScheduler(int corePoolSize, int maxPoolSize, int queueCapacity) {
        super();
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.queueCapacity = queueCapacity;
        this.readyTaskQueue = new ArrayBlockingQueue<>(queueCapacity);
        
        initializeThreadPools();
    }
    
    /**
     * 初始化线程池
     */
    private void initializeThreadPools() {
        // 任务执行线程池
        this.taskExecutor = Executors.newFixedThreadPool(corePoolSize, 
            new NamedThreadFactory("TaskExecutor"));
        
        // 时间轮推进器（单线程）
        this.wheelAdvancer = Executors.newSingleThreadScheduledExecutor(
            new NamedThreadFactory("WheelAdvancer"));
        
        // 超时检查线程池（单线程）
        this.timeoutChecker = Executors.newSingleThreadExecutor(
            new NamedThreadFactory("TimeoutChecker"));
    }
    
    /**
     * 启动调度器
     */
    public synchronized void start() {
        if (running.get()) {
            return;
        }
        
        if (shutdown.get()) {
            throw new IllegalStateException("调度器已关闭，无法重新启动");
        }
        
        running.set(true);
        
        // 启动时间轮推进器
        wheelAdvancer.scheduleAtFixedRate(this::advanceClock, 0, 1, TimeUnit.MILLISECONDS);
        
        // 启动任务分发器
        taskDispatcher = new Thread(this::runTaskDispatcher, "TaskDispatcher");
        taskDispatcher.setDaemon(true);
        taskDispatcher.start();
        
        // 启动超时检查器
        timeoutChecker.execute(this::runTimeoutChecker);
    }
    
    /**
     * 停止调度器（优雅关闭）
     */
    public synchronized void stop() {
        if (!running.get()) {
            return;
        }
        
        running.set(false);
        
        // 停止时间轮推进器
        wheelAdvancer.shutdown();
        
        // 等待任务队列清空
        try {
            while (!readyTaskQueue.isEmpty()) {
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 停止任务分发器
        if (taskDispatcher != null) {
            taskDispatcher.interrupt();
        }
        
        // 停止任务执行线程池
        taskExecutor.shutdown();
        try {
            if (!taskExecutor.awaitTermination(30, TimeUnit.SECONDS)) {
                taskExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            taskExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
        
        // 停止超时检查器
        timeoutChecker.shutdown();
        
        // 清理时间轮资源
        shutdown();
        
        shutdown.set(true);
    }
    
    /**
     * 提交Cron任务
     */
    public Future<Void> scheduleWithCron(String name, String cronExpression, Runnable task) {
        return scheduleWithCron(name, cronExpression, task, 0);
    }
    
    /**
     * 提交Cron任务（带超时）
     */
    public Future<Void> scheduleWithCron(String name, String cronExpression, 
                                       Runnable task, long timeoutMs) {
        ScheduledTask scheduledTask = ScheduledTask.cronTask(name, cronExpression, task, timeoutMs);
        return submitTask(scheduledTask);
    }
    
    /**
     * 提交固定延时任务
     */
    public Future<Void> scheduleWithFixedDelay(String name, Runnable task, 
                                             long initialDelayMs, long delayMs) {
        ScheduledTask scheduledTask = ScheduledTask.fixedDelayTask(name, task, initialDelayMs, delayMs);
        return submitTask(scheduledTask);
    }
    
    /**
     * 提交固定频率任务
     */
    public Future<Void> scheduleAtFixedRate(String name, Runnable task, 
                                          long initialDelayMs, long periodMs) {
        ScheduledTask scheduledTask = ScheduledTask.fixedRateTask(name, task, initialDelayMs, periodMs);
        return submitTask(scheduledTask);
    }
    
    /**
     * 提交一次性延时任务
     */
    public Future<Void> schedule(String name, Runnable task, long delayMs) {
        ScheduledTask scheduledTask = ScheduledTask.onceDelayTask(name, task, delayMs);
        return submitTask(scheduledTask);
    }
    
    /**
     * 提交任务到调度器
     */
    private Future<Void> submitTask(ScheduledTask task) {
        if (!running.get()) {
            throw new IllegalStateException("调度器未启动");
        }
        
        CompletableFuture<Void> future = new CompletableFuture<>();
        
        // 添加任务到时间轮
        boolean added = addTask(task);
        if (added) {
            totalSubmittedTasks.incrementAndGet();
            future.complete(null);
        } else {
            future.completeExceptionally(new RuntimeException("任务添加失败"));
        }
        
        return future;
    }
    
    /**
     * 重写父类方法，将到期任务提交到执行队列
     */
    @Override
    protected void submitTaskForExecution(TaskNode taskNode) {
        try {
            // 非阻塞方式添加到就绪队列
            if (!readyTaskQueue.offer(taskNode)) {
                // 队列满了，记录失败并释放节点
                totalFailedTasks.incrementAndGet();
                System.err.println("任务队列已满，丢弃任务: " + taskNode.getTask().getName());
            }
        } catch (Exception e) {
            totalFailedTasks.incrementAndGet();
            System.err.println("提交任务执行失败: " + e.getMessage());
        }
    }
    
    /**
     * 任务分发器主循环
     */
    private void runTaskDispatcher() {
        while (running.get() && !Thread.currentThread().isInterrupted()) {
            try {
                // 从就绪队列获取任务
                TaskNode taskNode = readyTaskQueue.poll(100, TimeUnit.MILLISECONDS);
                if (taskNode == null) {
                    continue;
                }
                
                // 检查任务是否被取消
                if (taskNode.isCancelled()) {
                    totalCancelledTasks.incrementAndGet();
                    continue;
                }
                
                // 提交任务执行
                taskExecutor.execute(() -> executeTask(taskNode));
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                System.err.println("任务分发器异常: " + e.getMessage());
            }
        }
    }
    
    /**
     * 执行任务
     */
    private void executeTask(TaskNode taskNode) {
        ScheduledTask task = taskNode.getTask();
        if (task == null) {
            return;
        }
        
        taskNode.markExecuting();
        
        try {
            // 检查任务超时
            if (taskNode.isTimeout()) {
                System.err.println("任务执行超时: " + task.getName());
                totalFailedTasks.incrementAndGet();
                return;
            }
            
            // 执行任务
            task.execute();
            totalExecutedTasks.incrementAndGet();
            
            // 如果是重复任务，重新调度
            if (!task.isCompleted() && task.getNextExecutionTime() != null) {
                addTask(task);
            }
            
        } catch (Exception e) {
            totalFailedTasks.incrementAndGet();
            System.err.println("任务执行异常: " + task.getName() + ", 错误: " + e.getMessage());
            
            // 如果是重复任务且允许重试，重新调度
            if (!task.isCompleted() && task.getCurrentRetries() < task.getMaxRetries()) {
                addTask(task);
            }
            
        } finally {
            taskNode.markCompleted();
        }
    }
    
    /**
     * 超时检查器主循环
     */
    private void runTimeoutChecker() {
        while (running.get() && !Thread.currentThread().isInterrupted()) {
            try {
                // 检查执行中的任务是否超时
                // 这里可以实现更精细的超时控制
                Thread.sleep(1000);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    /**
     * 取消任务
     */
    public boolean cancelTask(long taskId) {
        boolean cancelled = super.cancelTask(taskId);
        if (cancelled) {
            totalCancelledTasks.incrementAndGet();
        }
        return cancelled;
    }
    
    /**
     * 获取调度器统计信息
     */
    public SchedulerStats getSchedulerStats() {
        TimingWheelStats wheelStats = getStats();
        
        return new SchedulerStats(
            running.get(),
            totalSubmittedTasks.get(),
            totalExecutedTasks.get(),
            totalFailedTasks.get(),
            totalCancelledTasks.get(),
            readyTaskQueue.size(),
            wheelStats
        );
    }
    
    /**
     * 自定义线程工厂
     */
    private static class NamedThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        
        NamedThreadFactory(String namePrefix) {
            this.namePrefix = "Scheduler-" + namePrefix + "-";
        }
        
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
            t.setDaemon(true);
            return t;
        }
    }
    
    /**
     * 调度器统计信息
     */
    public static class SchedulerStats {
        private final boolean running;
        private final long totalSubmitted;
        private final long totalExecuted;
        private final long totalFailed;
        private final long totalCancelled;
        private final int queueSize;
        private final TimingWheelStats wheelStats;
        
        public SchedulerStats(boolean running, long totalSubmitted, long totalExecuted,
                            long totalFailed, long totalCancelled, int queueSize,
                            TimingWheelStats wheelStats) {
            this.running = running;
            this.totalSubmitted = totalSubmitted;
            this.totalExecuted = totalExecuted;
            this.totalFailed = totalFailed;
            this.totalCancelled = totalCancelled;
            this.queueSize = queueSize;
            this.wheelStats = wheelStats;
        }
        
        // Getters
        public boolean isRunning() { return running; }
        public long getTotalSubmitted() { return totalSubmitted; }
        public long getTotalExecuted() { return totalExecuted; }
        public long getTotalFailed() { return totalFailed; }
        public long getTotalCancelled() { return totalCancelled; }
        public int getQueueSize() { return queueSize; }
        public TimingWheelStats getWheelStats() { return wheelStats; }
        
        @Override
        public String toString() {
            return String.format(
                "SchedulerStats[running=%b, submitted=%d, executed=%d, failed=%d, cancelled=%d, queueSize=%d, %s]",
                running, totalSubmitted, totalExecuted, totalFailed, totalCancelled, queueSize, wheelStats);
        }
    }
}