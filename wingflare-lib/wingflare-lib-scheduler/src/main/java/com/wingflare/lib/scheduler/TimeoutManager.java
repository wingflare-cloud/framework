package com.wingflare.lib.scheduler;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 任务超时管理器
 * 负责监控任务执行时间，处理超时任务
 */
public class TimeoutManager {
    
    // 执行中的任务映射
    private final ConcurrentHashMap<Long, ExecutingTaskInfo> executingTasks;
    
    // 超时检查器
    private final ScheduledExecutorService timeoutChecker;
    
    // 管理器状态
    private final AtomicBoolean running = new AtomicBoolean(false);
    
    // 统计信息
    private final AtomicLong totalTimeoutTasks = new AtomicLong(0);
    private final AtomicLong totalCheckedTasks = new AtomicLong(0);
    
    // 默认超时检查间隔（秒）
    private static final int DEFAULT_CHECK_INTERVAL_SECONDS = 5;
    
    /**
     * 创建超时管理器
     */
    public TimeoutManager() {
        this.executingTasks = new ConcurrentHashMap<>();
        this.timeoutChecker = Executors.newSingleThreadScheduledExecutor(
            new TimeoutThreadFactory());
    }
    
    /**
     * 启动超时管理器
     */
    public void start() {
        if (running.compareAndSet(false, true)) {
            timeoutChecker.scheduleAtFixedRate(
                this::checkTimeoutTasks,
                DEFAULT_CHECK_INTERVAL_SECONDS,
                DEFAULT_CHECK_INTERVAL_SECONDS,
                TimeUnit.SECONDS
            );
        }
    }
    
    /**
     * 停止超时管理器
     */
    public void stop() {
        if (running.compareAndSet(true, false)) {
            timeoutChecker.shutdown();
            try {
                if (!timeoutChecker.awaitTermination(10, TimeUnit.SECONDS)) {
                    timeoutChecker.shutdownNow();
                }
            } catch (InterruptedException e) {
                timeoutChecker.shutdownNow();
                Thread.currentThread().interrupt();
            }
            
            executingTasks.clear();
        }
    }
    
    /**
     * 注册开始执行的任务
     */
    public void registerExecutingTask(TaskNode taskNode) {
        if (taskNode == null || taskNode.getTask() == null) {
            return;
        }
        
        long timeoutMs = taskNode.getTimeoutMs();
        if (timeoutMs <= 0) {
            return; // 无超时限制
        }
        
        ExecutingTaskInfo taskInfo = new ExecutingTaskInfo(
            taskNode,
            LocalDateTime.now(),
            timeoutMs
        );
        
        executingTasks.put(taskNode.getTaskId(), taskInfo);
    }
    
    /**
     * 注销完成的任务
     */
    public void unregisterExecutingTask(long taskId) {
        executingTasks.remove(taskId);
    }
    
    /**
     * 检查超时任务
     */
    private void checkTimeoutTasks() {
        if (!running.get()) {
            return;
        }
        
        LocalDateTime now = LocalDateTime.now();
        
        executingTasks.values().removeIf(taskInfo -> {
            totalCheckedTasks.incrementAndGet();
            
            if (isTaskTimeout(taskInfo, now)) {
                handleTimeoutTask(taskInfo);
                totalTimeoutTasks.incrementAndGet();
                return true; // 从映射中移除
            }
            
            return false;
        });
    }
    
    /**
     * 检查任务是否超时
     */
    private boolean isTaskTimeout(ExecutingTaskInfo taskInfo, LocalDateTime now) {
        Duration executionTime = Duration.between(taskInfo.getStartTime(), now);
        return executionTime.toMillis() > taskInfo.getTimeoutMs();
    }
    
    /**
     * 处理超时任务
     */
    private void handleTimeoutTask(ExecutingTaskInfo taskInfo) {
        TaskNode taskNode = taskInfo.getTaskNode();
        ScheduledTask task = taskNode.getTask();
        
        if (task != null) {
            // 标记任务为取消状态
            taskNode.cancel();
            task.cancel();
            
            // 记录超时信息
            String errorMessage = String.format(
                "任务执行超时: %s, 超时时间: %dms, 实际执行时间: %dms",
                task.getName(),
                taskInfo.getTimeoutMs(),
                Duration.between(taskInfo.getStartTime(), LocalDateTime.now()).toMillis()
            );
            
            System.err.println(errorMessage);
        }
    }
    
    /**
     * 获取当前执行中的任务数量
     */
    public int getExecutingTaskCount() {
        return executingTasks.size();
    }
    
    /**
     * 获取总超时任务数
     */
    public long getTotalTimeoutTasks() {
        return totalTimeoutTasks.get();
    }
    
    /**
     * 获取总检查任务数
     */
    public long getTotalCheckedTasks() {
        return totalCheckedTasks.get();
    }
    
    /**
     * 检查管理器是否运行中
     */
    public boolean isRunning() {
        return running.get();
    }
    
    /**
     * 执行中的任务信息
     */
    private static class ExecutingTaskInfo {
        private final TaskNode taskNode;
        private final LocalDateTime startTime;
        private final long timeoutMs;
        
        public ExecutingTaskInfo(TaskNode taskNode, LocalDateTime startTime, long timeoutMs) {
            this.taskNode = taskNode;
            this.startTime = startTime;
            this.timeoutMs = timeoutMs;
        }
        
        public TaskNode getTaskNode() { return taskNode; }
        public LocalDateTime getStartTime() { return startTime; }
        public long getTimeoutMs() { return timeoutMs; }
    }
    
    /**
     * 超时检查线程工厂
     */
    private static class TimeoutThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "TimeoutChecker-" + threadNumber.getAndIncrement());
            t.setDaemon(true);
            return t;
        }
    }
}