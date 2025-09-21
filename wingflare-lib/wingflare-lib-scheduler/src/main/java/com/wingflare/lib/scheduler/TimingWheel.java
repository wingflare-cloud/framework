package com.wingflare.lib.scheduler;


import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 5层时间轮实现
 * 层次: 毫秒(1000) -> 秒(60) -> 分钟(60) -> 小时(24) -> 天(365)
 * 支持高精度任务调度，避免哈希冲突
 */
public class TimingWheel {
    
    // 时间轮层级定义
    public static final int MILLISECOND_WHEEL = 0;  // 毫秒轮
    public static final int SECOND_WHEEL = 1;       // 秒轮  
    public static final int MINUTE_WHEEL = 2;       // 分钟轮
    public static final int HOUR_WHEEL = 3;         // 小时轮
    public static final int DAY_WHEEL = 4;          // 天轮
    
    // 各层时间轮的槽位数量
    private static final int[] WHEEL_SIZES = {1000, 60, 60, 24, 365};
    
    // 各层时间轮的时间单位（毫秒）
    private static final long[] TIME_UNITS = {1L, 1000L, 60_000L, 3_600_000L, 86_400_000L};
    
    // 5层时间轮
    private final TimeSlot[][] wheels;
    
    // 当前时间指针（每层轮的当前位置）
    private final AtomicLong[] currentTicks;
    
    // 时间轮的起始时间戳
    private final long startTimeMs;
    
    // 节点池管理器
    private final NodePool nodePool;
    
    /**
     * 创建时间轮
     */
    public TimingWheel() {
        this.startTimeMs = System.currentTimeMillis();
        this.wheels = new TimeSlot[5][];
        this.currentTicks = new AtomicLong[5];
        this.nodePool = new NodePool(10000); // 预分配1万个节点
        
        // 初始化各层时间轮
        for (int level = 0; level < 5; level++) {
            wheels[level] = new TimeSlot[WHEEL_SIZES[level]];
            currentTicks[level] = new AtomicLong(0);
            
            // 预分配所有槽位
            for (int slot = 0; slot < WHEEL_SIZES[level]; slot++) {
                wheels[level][slot] = new TimeSlot(slot);
            }
        }
    }
    
    /**
     * 添加任务到时间轮
     */
    public boolean addTask(ScheduledTask task) {
        if (task == null || task.getNextExecutionTime() == null) {
            return false;
        }
        
        // 计算任务的执行延迟时间
        long delayMs = calculateDelayMs(task.getNextExecutionTime());
        
        if (delayMs < 0) {
            // 任务已过期，立即执行
            delayMs = 0;
        }
        
        // 获取节点并设置任务
        TaskNode taskNode = nodePool.acquire();
        if (taskNode == null) {
            // 节点池耗尽，创建新节点
            taskNode = new TaskNode(task);
        } else {
            taskNode.setTask(task);
        }
        
        // 将任务添加到合适的时间轮层级
        return addTaskNode(taskNode, delayMs);
    }
    
    /**
     * 添加任务节点到时间轮
     */
    private boolean addTaskNode(TaskNode taskNode, long delayMs) {
        // 确定任务应该放在哪一层时间轮
        int level = determineWheelLevel(delayMs);
        
        if (level >= 5) {
            // 延迟时间过长，放到最高层
            level = 4;
        }
        
        // 计算在该层的槽位索引
        int slotIndex = calculateSlotIndex(level, delayMs);
        
        // 设置任务节点的时间轮信息
        taskNode.setWheelLevel(level);
        taskNode.setSlotIndex(slotIndex);
        
        // 添加到对应槽位
        wheels[level][slotIndex].addTask(taskNode);
        
        return true;
    }
    
    /**
     * 确定任务应该放在哪一层时间轮
     */
    private int determineWheelLevel(long delayMs) {
        if (delayMs < TIME_UNITS[1]) {
            return MILLISECOND_WHEEL;
        } else if (delayMs < TIME_UNITS[2]) {
            return SECOND_WHEEL;
        } else if (delayMs < TIME_UNITS[3]) {
            return MINUTE_WHEEL;
        } else if (delayMs < TIME_UNITS[4]) {
            return HOUR_WHEEL;
        } else {
            return DAY_WHEEL;
        }
    }
    
    /**
     * 计算任务在指定层级的槽位索引
     */
    private int calculateSlotIndex(int level, long delayMs) {
        long currentTimeMs = System.currentTimeMillis();
        long targetTimeMs = currentTimeMs + delayMs;
        
        // 计算目标时间在该层的槽位索引
        long timeUnit = TIME_UNITS[level];
        long wheelSize = WHEEL_SIZES[level];
        
        long ticksFromStart = (targetTimeMs - startTimeMs) / timeUnit;
        return (int) (ticksFromStart % wheelSize);
    }
    
    /**
     * 推进时间轮指针
     */
    public void advanceClock() {
        long currentTimeMs = System.currentTimeMillis();
        
        // 推进毫秒轮
        advanceWheel(MILLISECOND_WHEEL, currentTimeMs);
    }
    
    /**
     * 推进指定层级的时间轮
     */
    private void advanceWheel(int level, long currentTimeMs) {
        long timeUnit = TIME_UNITS[level];
        long currentTick = (currentTimeMs - startTimeMs) / timeUnit;
        long lastTick = currentTicks[level].get();
        
        if (currentTick <= lastTick) {
            return; // 时间没有推进
        }
        
        // 推进时间指针
        for (long tick = lastTick + 1; tick <= currentTick; tick++) {
            int slotIndex = (int) (tick % WHEEL_SIZES[level]);
            
            // 处理当前槽位的任务
            processSlot(level, slotIndex);
            
            // 更新时间指针
            currentTicks[level].set(tick);
            
            // 检查是否需要推进上一层时间轮
            if (slotIndex == 0 && level < 4) {
                advanceWheel(level + 1, currentTimeMs);
            }
        }
    }
    
    /**
     * 处理指定槽位的任务
     */
    private void processSlot(int level, int slotIndex) {
        TimeSlot slot = wheels[level][slotIndex];
        TaskNode expiredTasks = slot.pollExpiredTasks();
        
        if (expiredTasks == null) {
            return;
        }
        
        // 处理过期任务
        TaskNode current = expiredTasks;
        while (current != null) {
            TaskNode next = current.getNext();
            
            if (current.isCancelled()) {
                // 释放取消的任务节点
                nodePool.release(current);
            } else {
                // 重新调度或执行任务
                rescheduleOrExecuteTask(current);
            }
            
            current = next;
        }
    }
    
    /**
     * 重新调度或执行任务
     */
    private void rescheduleOrExecuteTask(TaskNode taskNode) {
        ScheduledTask task = taskNode.getTask();
        if (task == null) {
            nodePool.release(taskNode);
            return;
        }
        
        long executionTimeMs = calculateDelayMs(task.getNextExecutionTime());
        
        if (executionTimeMs <= 0) {
            // 任务到期，提交执行
            submitTaskForExecution(taskNode);
        } else {
            // 任务需要重新调度到更精确的时间轮层级
            addTaskNode(taskNode, executionTimeMs);
        }
    }
    
    /**
     * 提交任务执行（需要在调度器中实现）
     */
    protected void submitTaskForExecution(TaskNode taskNode) {
        throw new UnsupportedOperationException("Need to implement the task execution logic in the scheduler");
    }
    
    /**
     * 计算延迟时间（毫秒）
     */
    private long calculateDelayMs(LocalDateTime targetTime) {
        if (targetTime == null) {
            return -1;
        }
        
        long targetMs = targetTime.atZone(java.time.ZoneId.systemDefault())
                                  .toInstant()
                                  .toEpochMilli();
        return targetMs - System.currentTimeMillis();
    }
    
    /**
     * 取消任务
     */
    public boolean cancelTask(long taskId) {
        // 遍历所有时间轮查找并取消任务
        for (int level = 0; level < 5; level++) {
            for (TimeSlot slot : wheels[level]) {
                if (cancelTaskInSlot(slot, taskId)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 在指定槽位中取消任务
     */
    private boolean cancelTaskInSlot(TimeSlot slot, long taskId) {
        final boolean[] found = {false};
        
        slot.forEach(taskNode -> {
            if (taskNode.getTask() != null && 
                taskNode.getTask().getTaskId() == taskId) {
                taskNode.cancel();
                found[0] = true;
            }
        });
        
        return found[0];
    }
    
    /**
     * 获取时间轮统计信息
     */
    public TimingWheelStats getStats() {
        int totalTasks = 0;
        int[] tasksByLevel = new int[5];
        
        for (int level = 0; level < 5; level++) {
            for (TimeSlot slot : wheels[level]) {
                int count = slot.getTaskCount();
                tasksByLevel[level] += count;
                totalTasks += count;
            }
        }
        
        return new TimingWheelStats(totalTasks, tasksByLevel, nodePool.getAvailableCount());
    }
    
    /**
     * 关闭时间轮，清理资源
     */
    public void shutdown() {
        // 清空所有时间轮
        for (int level = 0; level < 5; level++) {
            for (TimeSlot slot : wheels[level]) {
                slot.clear();
            }
        }
        
        // 清理节点池
        nodePool.clear();
    }
    
    /**
     * 时间轮统计信息
     */
    public static class TimingWheelStats {
        private final int totalTasks;
        private final int[] tasksByLevel;
        private final int availableNodes;
        
        public TimingWheelStats(int totalTasks, int[] tasksByLevel, int availableNodes) {
            this.totalTasks = totalTasks;
            this.tasksByLevel = tasksByLevel;
            this.availableNodes = availableNodes;
        }
        
        public int getTotalTasks() { return totalTasks; }
        public int[] getTasksByLevel() { return tasksByLevel; }
        public int getAvailableNodes() { return availableNodes; }
        
        @Override
        public String toString() {
            return String.format("TimingWheelStats[total=%d, byLevel=%s, availableNodes=%d]",
                               totalTasks, java.util.Arrays.toString(tasksByLevel), availableNodes);
        }
    }
}