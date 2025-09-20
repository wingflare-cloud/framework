package com.wingflare.lib.scheduler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务节点池 - 用于预分配和复用TaskNode，避免频繁GC
 * 无锁化设计，提高并发性能
 */
public class NodePool {
    
    // 节点池容量
    private final int capacity;
    
    // 可用节点队列
    private final BlockingQueue<TaskNode> availableNodes;
    
    // 当前池中可用节点数量
    private final AtomicInteger availableCount;
    
    // 总共创建的节点数量
    private final AtomicInteger totalCreated;
    
    /**
     * 创建节点池
     * @param capacity 池容量
     */
    public NodePool(int capacity) {
        this.capacity = capacity;
        this.availableNodes = new ArrayBlockingQueue<>(capacity);
        this.availableCount = new AtomicInteger(0);
        this.totalCreated = new AtomicInteger(0);
        
        // 预分配节点
        preAllocateNodes();
    }
    
    /**
     * 预分配节点
     */
    private void preAllocateNodes() {
        for (int i = 0; i < capacity; i++) {
            TaskNode node = new TaskNode();
            availableNodes.offer(node);
            availableCount.incrementAndGet();
            totalCreated.incrementAndGet();
        }
    }
    
    /**
     * 获取一个可用节点
     * @return 可用节点，如果池为空则返回null
     */
    public TaskNode acquire() {
        TaskNode node = availableNodes.poll();
        if (node != null) {
            availableCount.decrementAndGet();
            // 重置节点状态
            node.reset();
        }
        return node;
    }
    
    /**
     * 释放节点回池
     * @param node 要释放的节点
     * @return 是否成功释放
     */
    public boolean release(TaskNode node) {
        if (node == null) {
            return false;
        }
        
        // 重置节点状态
        node.reset();
        
        // 尝试放回池中
        boolean offered = availableNodes.offer(node);
        if (offered) {
            availableCount.incrementAndGet();
        }
        
        return offered;
    }
    
    /**
     * 强制释放节点回池（阻塞等待）
     * @param node 要释放的节点
     */
    public void forceRelease(TaskNode node) {
        if (node == null) {
            return;
        }
        
        try {
            // 重置节点状态
            node.reset();
            
            // 阻塞等待直到成功放入池中
            availableNodes.put(node);
            availableCount.incrementAndGet();
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            // 如果中断，直接丢弃节点
        }
    }
    
    /**
     * 获取池中可用节点数量
     */
    public int getAvailableCount() {
        return availableCount.get();
    }
    
    /**
     * 获取池的总容量
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * 获取总共创建的节点数量
     */
    public int getTotalCreated() {
        return totalCreated.get();
    }
    
    /**
     * 获取池的使用率
     */
    public double getUtilizationRate() {
        return 1.0 - (double) availableCount.get() / capacity;
    }
    
    /**
     * 检查池是否为空
     */
    public boolean isEmpty() {
        return availableCount.get() == 0;
    }
    
    /**
     * 检查池是否已满
     */
    public boolean isFull() {
        return availableCount.get() >= capacity;
    }
    
    /**
     * 清空池
     */
    public void clear() {
        availableNodes.clear();
        availableCount.set(0);
    }
    
    /**
     * 扩容池（添加更多节点）
     * @param additionalCapacity 要添加的节点数量
     */
    public void expand(int additionalCapacity) {
        for (int i = 0; i < additionalCapacity; i++) {
            if (availableNodes.remainingCapacity() > 0) {
                TaskNode node = new TaskNode();
                if (availableNodes.offer(node)) {
                    availableCount.incrementAndGet();
                    totalCreated.incrementAndGet();
                }
            } else {
                break; // 池已满，无法继续扩容
            }
        }
    }
    
    /**
     * 获取池统计信息
     */
    public PoolStats getStats() {
        return new PoolStats(
            capacity,
            availableCount.get(),
            totalCreated.get(),
            getUtilizationRate()
        );
    }
    
    /**
     * 池统计信息
     */
    public static class PoolStats {
        private final int capacity;
        private final int available;
        private final int totalCreated;
        private final double utilizationRate;
        
        public PoolStats(int capacity, int available, int totalCreated, double utilizationRate) {
            this.capacity = capacity;
            this.available = available;
            this.totalCreated = totalCreated;
            this.utilizationRate = utilizationRate;
        }
        
        public int getCapacity() { return capacity; }
        public int getAvailable() { return available; }
        public int getTotalCreated() { return totalCreated; }
        public double getUtilizationRate() { return utilizationRate; }
        public int getInUse() { return capacity - available; }
        
        @Override
        public String toString() {
            return String.format("PoolStats[capacity=%d, available=%d, inUse=%d, utilization=%.2f%%, totalCreated=%d]",
                               capacity, available, getInUse(), utilizationRate * 100, totalCreated);
        }
    }
    
    @Override
    public String toString() {
        return String.format("NodePool[capacity=%d, available=%d, utilization=%.2f%%]",
                           capacity, availableCount.get(), getUtilizationRate() * 100);
    }
}