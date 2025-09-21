package com.wingflare.lib.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 调度任务定义
 * 支持不同类型的定时任务：Cron、固定延时、一次性延时等
 */
public class ScheduledTask {
    
    // 任务类型枚举
    public enum TaskType {
        CRON,           // Cron表达式任务
        FIXED_DELAY,    // 固定延时任务
        FIXED_RATE,     // 固定频率任务  
        ONCE_DELAY      // 一次性延时任务
    }
    
    // 任务状态枚举
    public enum TaskStatus {
        PENDING,        // 等待执行
        RUNNING,        // 正在执行
        COMPLETED,      // 执行完成
        CANCELLED,      // 已取消
        FAILED          // 执行失败
    }
    
    // 全局任务ID生成器
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);
    
    // 基本属性
    private final long taskId;
    private final String name;
    private final TaskType type;
    private final Runnable runnable;
    private final Callable<Void> callable;
    
    // 时间相关
    private final CronExpression cronExpression;
    private final long initialDelayMs;
    private final long periodMs;
    private LocalDateTime nextExecutionTime;
    private LocalDateTime lastExecutionTime;
    
    // 状态和配置
    private volatile TaskStatus status;
    private final long timeoutMs;
    private final int maxRetries;
    private int currentRetries;
    
    // 执行统计
    private long executionCount;
    private long totalExecutionTimeMs;
    private Throwable lastException;
    
    /**
     * 创建Cron任务
     */
    public static ScheduledTask cronTask(String name, String cronExpression, Runnable runnable) {
        return new ScheduledTask(name, TaskType.CRON, runnable, null, 
                               new CronExpression(cronExpression), 0, 0, 0, 0);
    }
    
    /**
     * 创建Cron任务（带超时）
     */
    public static ScheduledTask cronTask(String name, String cronExpression, 
                                       Runnable runnable, long timeoutMs) {
        return new ScheduledTask(name, TaskType.CRON, runnable, null, 
                               new CronExpression(cronExpression), 0, 0, timeoutMs, 0);
    }
    
    /**
     * 创建固定延时任务
     */
    public static ScheduledTask fixedDelayTask(String name, Runnable runnable, 
                                             long initialDelayMs, long delayMs) {
        return new ScheduledTask(name, TaskType.FIXED_DELAY, runnable, null, 
                               null, initialDelayMs, delayMs, 0, 0);
    }
    
    /**
     * 创建固定频率任务
     */
    public static ScheduledTask fixedRateTask(String name, Runnable runnable, 
                                            long initialDelayMs, long periodMs) {
        return new ScheduledTask(name, TaskType.FIXED_RATE, runnable, null, 
                               null, initialDelayMs, periodMs, 0, 0);
    }
    
    /**
     * 创建一次性延时任务
     */
    public static ScheduledTask onceDelayTask(String name, Runnable runnable, long delayMs) {
        return new ScheduledTask(name, TaskType.ONCE_DELAY, runnable, null, 
                               null, delayMs, 0, 0, 0);
    }
    
    /**
     * 私有构造函数
     */
    private ScheduledTask(String name, TaskType type, Runnable runnable, Callable<Void> callable,
                         CronExpression cronExpression, long initialDelayMs, long periodMs,
                         long timeoutMs, int maxRetries) {
        this.taskId = ID_GENERATOR.incrementAndGet();
        this.name = name != null ? name : "Task-" + taskId;
        this.type = type;
        this.runnable = runnable;
        this.callable = callable;
        this.cronExpression = cronExpression;
        this.initialDelayMs = initialDelayMs;
        this.periodMs = periodMs;
        this.timeoutMs = timeoutMs;
        this.maxRetries = maxRetries;
        this.status = TaskStatus.PENDING;
        this.currentRetries = 0;
        this.executionCount = 0;
        this.totalExecutionTimeMs = 0;
        
        // 计算初始执行时间
        calculateNextExecutionTime();
    }
    
    /**
     * 计算下一次执行时间
     */
    public void calculateNextExecutionTime() {
        LocalDateTime now = LocalDateTime.now();
        
        switch (type) {
            case CRON:
                if (cronExpression != null) {
                    LocalDateTime baseTime = lastExecutionTime != null ? lastExecutionTime : now;
                    nextExecutionTime = cronExpression.getNextExecutionTime(baseTime);
                }
                break;
                
            case FIXED_DELAY:
                if (lastExecutionTime == null) {
                    nextExecutionTime = now.plusNanos(initialDelayMs * 1_000_000);
                } else {
                    nextExecutionTime = lastExecutionTime.plusNanos(periodMs * 1_000_000);
                }
                break;
                
            case FIXED_RATE:
                if (lastExecutionTime == null) {
                    nextExecutionTime = now.plusNanos(initialDelayMs * 1_000_000);
                } else {
                    nextExecutionTime = lastExecutionTime.plusNanos(periodMs * 1_000_000);
                    // 确保不会超过当前时间太多
                    if (nextExecutionTime.isBefore(now)) {
                        nextExecutionTime = now;
                    }
                }
                break;
                
            case ONCE_DELAY:
                if (lastExecutionTime == null) {
                    nextExecutionTime = now.plusNanos(initialDelayMs * 1_000_000);
                } else {
                    // 一次性任务执行完后不再调度
                    nextExecutionTime = null;
                }
                break;
        }
    }
    
    /**
     * 执行任务
     */
    public void execute() throws Exception {
        if (status == TaskStatus.CANCELLED) {
            return;
        }
        
        status = TaskStatus.RUNNING;
        long startTime = System.currentTimeMillis();
        
        try {
            if (runnable != null) {
                runnable.run();
            } else if (callable != null) {
                callable.call();
            }
            
            status = TaskStatus.COMPLETED;
            lastException = null;
            
        } catch (Exception e) {
            lastException = e;
            currentRetries++;
            
            if (currentRetries >= maxRetries) {
                status = TaskStatus.FAILED;
            } else {
                status = TaskStatus.PENDING;
            }
            throw e;
            
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            totalExecutionTimeMs += executionTime;
            executionCount++;
            lastExecutionTime = LocalDateTime.now();
            
            // 计算下一次执行时间
            if (status != TaskStatus.FAILED && type != TaskType.ONCE_DELAY) {
                calculateNextExecutionTime();
                if (nextExecutionTime != null) {
                    status = TaskStatus.PENDING;
                }
            }
        }
    }
    
    /**
     * 取消任务
     */
    public void cancel() {
        this.status = TaskStatus.CANCELLED;
    }
    
    /**
     * 重置重试次数
     */
    public void resetRetries() {
        this.currentRetries = 0;
        if (status == TaskStatus.FAILED) {
            status = TaskStatus.PENDING;
        }
    }
    
    /**
     * 检查任务是否应该执行
     */
    public boolean shouldExecute() {
        return status == TaskStatus.PENDING && 
               nextExecutionTime != null && 
               !nextExecutionTime.isAfter(LocalDateTime.now());
    }
    
    /**
     * 检查任务是否已完成（不会再执行）
     */
    public boolean isCompleted() {
        return status == TaskStatus.CANCELLED || 
               status == TaskStatus.FAILED ||
               (type == TaskType.ONCE_DELAY && executionCount > 0);
    }
    
    /**
     * 获取平均执行时间
     */
    public double getAverageExecutionTimeMs() {
        return executionCount > 0 ? (double) totalExecutionTimeMs / executionCount : 0;
    }
    
    // Getters
    
    public long getTaskId() {
        return taskId;
    }
    
    public String getName() {
        return name;
    }
    
    public TaskType getType() {
        return type;
    }
    
    public CronExpression getCronExpression() {
        return cronExpression;
    }
    
    public long getInitialDelayMs() {
        return initialDelayMs;
    }
    
    public long getPeriodMs() {
        return periodMs;
    }
    
    public LocalDateTime getNextExecutionTime() {
        return nextExecutionTime;
    }
    
    public LocalDateTime getLastExecutionTime() {
        return lastExecutionTime;
    }
    
    public TaskStatus getStatus() {
        return status;
    }
    
    public long getTimeoutMs() {
        return timeoutMs;
    }
    
    public int getMaxRetries() {
        return maxRetries;
    }
    
    public int getCurrentRetries() {
        return currentRetries;
    }
    
    public long getExecutionCount() {
        return executionCount;
    }
    
    public long getTotalExecutionTimeMs() {
        return totalExecutionTimeMs;
    }
    
    public Throwable getLastException() {
        return lastException;
    }
    
    @Override
    public String toString() {
        return String.format("ScheduledTask[id=%d, name='%s', type=%s, status=%s, nextExecution=%s]",
                           taskId, name, type, status, nextExecutionTime);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ScheduledTask that = (ScheduledTask) obj;
        return taskId == that.taskId;
    }
    
    @Override
    public int hashCode() {
        return Long.hashCode(taskId);
    }
}