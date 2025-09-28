package com.wingflare.lib.scheduler;


import com.wingflare.api.threadpool.ThreadPoolManageDrive;
import com.wingflare.lib.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 任务超时管理器
 * 负责监控任务执行时间，处理超时任务
 */
public class TimeoutManager {

    private static final Logger logger = LoggerFactory.getLogger(TimeoutManager.class);
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
        ThreadPoolManageDrive threadPoolManage = Container.get(ThreadPoolManageDrive.class);

        this.executingTasks = new ConcurrentHashMap<>();
        this.timeoutChecker = Executors.newSingleThreadScheduledExecutor(
                threadPoolManage.factory("SchedulerTimeoutChecker"));
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

            logger.error(String.format(
                    "Task execution timeout: %s, timeout time: %dms, actual execution time: %dms",
                    task.getName(),
                    taskInfo.getTimeoutMs(),
                    Duration.between(taskInfo.getStartTime(), LocalDateTime.now()).toMillis()
            ));
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

}