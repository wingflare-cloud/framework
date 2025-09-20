package com.wingflare.lib.scheduler;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 任务节点 - 用于时间轮的双向链表节点
 * 预分配节点避免频繁GC
 */
public class TaskNode {
    
    // 全局任务ID生成器
    private static final AtomicLong TASK_ID_GENERATOR = new AtomicLong(0);
    
    // 任务唯一ID
    private final long taskId;
    
    // 任务信息
    private ScheduledTask task;
    
    // 双向链表指针
    private TaskNode prev;
    private TaskNode next;
    
    // 在时间轮中的位置信息
    private int wheelLevel;     // 在哪一层时间轮
    private int slotIndex;      // 在时间轮的哪个槽位
    
    // 任务状态
    private volatile boolean cancelled;
    private volatile boolean executing;
    
    // 超时相关
    private long timeoutMs;
    private LocalDateTime createTime;
    
    /**
     * 创建任务节点
     */
    public TaskNode() {
        this.taskId = TASK_ID_GENERATOR.incrementAndGet();
        this.createTime = LocalDateTime.now();
        this.cancelled = false;
        this.executing = false;
        this.wheelLevel = -1;
        this.slotIndex = -1;
    }
    
    /**
     * 创建带任务的节点
     */
    public TaskNode(ScheduledTask task) {
        this();
        this.task = task;
        this.timeoutMs = task.getTimeoutMs();
    }
    
    /**
     * 重置节点状态，用于节点复用
     */
    public void reset() {
        this.task = null;
        this.prev = null;
        this.next = null;
        this.wheelLevel = -1;
        this.slotIndex = -1;
        this.cancelled = false;
        this.executing = false;
        this.timeoutMs = 0;
        this.createTime = LocalDateTime.now();
    }
    
    /**
     * 设置任务
     */
    public void setTask(ScheduledTask task) {
        this.task = task;
        if (task != null) {
            this.timeoutMs = task.getTimeoutMs();
        }
    }
    
    /**
     * 从双向链表中移除当前节点
     */
    public void unlink() {
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
        prev = null;
        next = null;
    }
    
    /**
     * 在当前节点后插入新节点
     */
    public void insertAfter(TaskNode newNode) {
        newNode.next = this.next;
        newNode.prev = this;
        
        if (this.next != null) {
            this.next.prev = newNode;
        }
        this.next = newNode;
    }
    
    /**
     * 在当前节点前插入新节点
     */
    public void insertBefore(TaskNode newNode) {
        newNode.prev = this.prev;
        newNode.next = this;
        
        if (this.prev != null) {
            this.prev.next = newNode;
        }
        this.prev = newNode;
    }
    
    /**
     * 检查任务是否超时
     */
    public boolean isTimeout() {
        if (timeoutMs <= 0) {
            return false;
        }
        
        long elapsedMs = java.time.Duration.between(createTime, LocalDateTime.now()).toMillis();
        return elapsedMs > timeoutMs;
    }
    
    /**
     * 标记任务已取消
     */
    public void cancel() {
        this.cancelled = true;
    }
    
    /**
     * 标记任务正在执行
     */
    public void markExecuting() {
        this.executing = true;
    }
    
    /**
     * 标记任务执行完成
     */
    public void markCompleted() {
        this.executing = false;
    }
    
    // Getters and Setters
    
    public long getTaskId() {
        return taskId;
    }
    
    public ScheduledTask getTask() {
        return task;
    }
    
    public TaskNode getPrev() {
        return prev;
    }
    
    public void setPrev(TaskNode prev) {
        this.prev = prev;
    }
    
    public TaskNode getNext() {
        return next;
    }
    
    public void setNext(TaskNode next) {
        this.next = next;
    }
    
    public int getWheelLevel() {
        return wheelLevel;
    }
    
    public void setWheelLevel(int wheelLevel) {
        this.wheelLevel = wheelLevel;
    }
    
    public int getSlotIndex() {
        return slotIndex;
    }
    
    public void setSlotIndex(int slotIndex) {
        this.slotIndex = slotIndex;
    }
    
    public boolean isCancelled() {
        return cancelled;
    }
    
    public boolean isExecuting() {
        return executing;
    }
    
    public long getTimeoutMs() {
        return timeoutMs;
    }
    
    public void setTimeoutMs(long timeoutMs) {
        this.timeoutMs = timeoutMs;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    @Override
    public String toString() {
        return String.format("TaskNode[id=%d, level=%d, slot=%d, cancelled=%b, executing=%b]", 
                           taskId, wheelLevel, slotIndex, cancelled, executing);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TaskNode taskNode = (TaskNode) obj;
        return taskId == taskNode.taskId;
    }
    
    @Override
    public int hashCode() {
        return Long.hashCode(taskId);
    }
}