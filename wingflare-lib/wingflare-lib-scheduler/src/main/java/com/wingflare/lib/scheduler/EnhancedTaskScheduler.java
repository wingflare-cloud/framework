package com.wingflare.lib.scheduler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 增强版任务调度器
 * 集成超时管理和资源管理功能
 */
public class EnhancedTaskScheduler extends TaskScheduler {


    private static final Logger logger = LoggerFactory.getLogger(EnhancedTaskScheduler.class);
    // 超时管理器
    private final TimeoutManager timeoutManager;
    
    // 资源管理器  
    private final ResourceManager resourceManager;
    
    /**
     * 创建增强调度器
     */
    public EnhancedTaskScheduler() {
        super();
        this.timeoutManager = new TimeoutManager();
        this.resourceManager = new ResourceManager();
    }
    
    @Override
    public synchronized void start() {
        super.start();
        
        // 启动超时管理器
        timeoutManager.start();
        
        // 启动资源监控
        resourceManager.startMonitoring();
    }
    
    @Override
    public synchronized void stop() {
        // 停止超时管理器
        timeoutManager.stop();
        
        // 停止资源监控
        resourceManager.stopMonitoring();
        
        super.stop();
    }
    
    @Override
    protected void submitTaskForExecution(TaskNode taskNode) {
        // 注册到超时管理器
        timeoutManager.registerExecutingTask(taskNode);
        
        // 调用父类方法
        super.submitTaskForExecution(taskNode);
    }
    
    /**
     * 增强的任务执行方法（重写父类的executeTask方法）
     */
    protected void executeTaskEnhanced(TaskNode taskNode) {
        ScheduledTask task = taskNode.getTask();
        if (task == null) {
            return;
        }
        
        taskNode.markExecuting();
        
        try {
            // 检查任务超时
            if (taskNode.isTimeout()) {
                logger.warn("Task execution timeout: {}", task.getName());
                return;
            }
            
            // 执行任务
            task.execute();
            
            // 如果是重复任务，重新调度
            if (!task.isCompleted() && task.getNextExecutionTime() != null) {
                addTask(task);
            }
            
        } catch (Exception e) {
            logger.warn("Task execution exception: {}, error: {}", task.getName(), e.getMessage());
            
            // 如果是重复任务且允许重试，重新调度
            if (!task.isCompleted() && task.getCurrentRetries() < task.getMaxRetries()) {
                addTask(task);
            }
            
        } finally {
            taskNode.markCompleted();
            
            // 从超时管理器注销
            timeoutManager.unregisterExecutingTask(taskNode.getTaskId());
        }
    }
    
    /**
     * 获取超时管理器
     */
    public TimeoutManager getTimeoutManager() {
        return timeoutManager;
    }
    
    /**
     * 获取资源管理器
     */
    public ResourceManager getResourceManager() {
        return resourceManager;
    }
    
    /**
     * 获取增强统计信息
     */
    public EnhancedSchedulerStats getEnhancedStats() {
        SchedulerStats baseStats = getSchedulerStats();
        ResourceManager.ResourceStats resourceStats = resourceManager.getResourceStats();
        
        return new EnhancedSchedulerStats(
            baseStats,
            timeoutManager.getExecutingTaskCount(),
            timeoutManager.getTotalTimeoutTasks(),
            timeoutManager.getTotalCheckedTasks(),
            resourceStats
        );
    }
    
    /**
     * 手动触发资源清理
     */
    public void cleanup() {
        resourceManager.cleanup();
    }
    
    /**
     * 增强统计信息
     */
    public static class EnhancedSchedulerStats {
        private final SchedulerStats baseStats;
        private final int executingTaskCount;
        private final long totalTimeoutTasks;
        private final long totalCheckedTasks;
        private final ResourceManager.ResourceStats resourceStats;
        
        public EnhancedSchedulerStats(SchedulerStats baseStats, int executingTaskCount,
                                    long totalTimeoutTasks, long totalCheckedTasks,
                                    ResourceManager.ResourceStats resourceStats) {
            this.baseStats = baseStats;
            this.executingTaskCount = executingTaskCount;
            this.totalTimeoutTasks = totalTimeoutTasks;
            this.totalCheckedTasks = totalCheckedTasks;
            this.resourceStats = resourceStats;
        }
        
        // Getters
        public SchedulerStats getBaseStats() { return baseStats; }
        public int getExecutingTaskCount() { return executingTaskCount; }
        public long getTotalTimeoutTasks() { return totalTimeoutTasks; }
        public long getTotalCheckedTasks() { return totalCheckedTasks; }
        public ResourceManager.ResourceStats getResourceStats() { return resourceStats; }
        
        @Override
        public String toString() {
            return String.format(
                "EnhancedSchedulerStats[\n  %s,\n  executing=%d, timeouts=%d, checked=%d,\n  %s\n]",
                baseStats, executingTaskCount, totalTimeoutTasks, totalCheckedTasks, resourceStats);
        }
    }
}