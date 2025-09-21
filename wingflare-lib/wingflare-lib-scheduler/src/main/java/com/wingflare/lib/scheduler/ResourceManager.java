package com.wingflare.lib.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 资源管理器
 * 负责管理调度器的资源分配和回收
 */
public class ResourceManager {


    
    // 默认配置
    private static final int DEFAULT_INITIAL_NODE_POOL_SIZE = 1000;
    private static final int DEFAULT_MAX_NODE_POOL_SIZE = 10000;
    private static final double DEFAULT_POOL_EXPANSION_THRESHOLD = 0.8; // 80%使用率时扩容
    private static final double DEFAULT_POOL_SHRINK_THRESHOLD = 0.2;    // 20%使用率时缩容
    private static final Logger logger = LoggerFactory.getLogger(ResourceManager.class);

    // 节点池
    private final NodePool nodePool;
    
    // 资源监控
    private final ScheduledExecutorService resourceMonitor;
    private final AtomicBoolean monitoring = new AtomicBoolean(false);
    
    // 配置参数
    private final int maxNodePoolSize;
    private final double expansionThreshold;
    private final double shrinkThreshold;
    
    // 统计信息
    private final AtomicLong totalExpansions = new AtomicLong(0);
    private final AtomicLong totalShrinks = new AtomicLong(0);
    
    /**
     * 创建资源管理器
     */
    public ResourceManager() {
        this(DEFAULT_INITIAL_NODE_POOL_SIZE, DEFAULT_MAX_NODE_POOL_SIZE, 
             DEFAULT_POOL_EXPANSION_THRESHOLD, DEFAULT_POOL_SHRINK_THRESHOLD);
    }
    
    /**
     * 创建资源管理器（自定义配置）
     */
    public ResourceManager(int initialPoolSize, int maxPoolSize, 
                          double expansionThreshold, double shrinkThreshold) {
        this.nodePool = new NodePool(initialPoolSize);
        this.maxNodePoolSize = maxPoolSize;
        this.expansionThreshold = expansionThreshold;
        this.shrinkThreshold = shrinkThreshold;
        this.resourceMonitor = Executors.newSingleThreadScheduledExecutor(
            new ResourceMonitorThreadFactory());
    }
    
    /**
     * 启动资源监控
     */
    public void startMonitoring() {
        if (monitoring.compareAndSet(false, true)) {
            resourceMonitor.scheduleAtFixedRate(
                this::monitorResources,
                10, // 延迟10秒启动
                30, // 每30秒检查一次
                TimeUnit.SECONDS
            );
        }
    }
    
    /**
     * 停止资源监控
     */
    public void stopMonitoring() {
        if (monitoring.compareAndSet(true, false)) {
            resourceMonitor.shutdown();
            try {
                if (!resourceMonitor.awaitTermination(10, TimeUnit.SECONDS)) {
                    resourceMonitor.shutdownNow();
                }
            } catch (InterruptedException e) {
                resourceMonitor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
    
    /**
     * 获取节点池
     */
    public NodePool getNodePool() {
        return nodePool;
    }
    
    /**
     * 监控资源使用情况
     */
    private void monitorResources() {
        try {
            monitorNodePool();
        } catch (Exception e) {
            logger.warn("Resource monitoring exception: {}", e.getMessage());
        }
    }
    
    /**
     * 监控节点池
     */
    private void monitorNodePool() {
        double utilizationRate = nodePool.getUtilizationRate();
        int currentCapacity = nodePool.getCapacity();
        
        // 检查是否需要扩容
        if (utilizationRate > expansionThreshold && 
            currentCapacity < maxNodePoolSize) {
            
            int expandSize = Math.min(currentCapacity / 2, maxNodePoolSize - currentCapacity);
            if (expandSize > 0) {
                nodePool.expand(expandSize);
                totalExpansions.incrementAndGet();

                if (logger.isDebugEnabled()) {
                    logger.info(String.format(
                            "节点池扩容: %d -> %d (使用率: %.2f%%)",
                            currentCapacity, nodePool.getCapacity(), utilizationRate * 100));
                }

            }
        }
        
        // 检查是否需要缩容（这里暂不实现自动缩容，因为可能影响性能）
        if (utilizationRate < shrinkThreshold) {
            // 可以记录低使用率情况，但不自动缩容
            // 因为频繁的扩容缩容可能影响性能
        }
    }
    
    /**
     * 手动触发资源清理
     */
    public void cleanup() {
        // 清理节点池中的无效节点
        // 这里可以实现更复杂的清理逻辑
        System.gc(); // 建议JVM进行垃圾回收
    }
    
    /**
     * 获取资源统计信息
     */
    public ResourceStats getResourceStats() {
        NodePool.PoolStats poolStats = nodePool.getStats();
        
        return new ResourceStats(
            poolStats,
            totalExpansions.get(),
            totalShrinks.get(),
            monitoring.get()
        );
    }
    
    /**
     * 关闭资源管理器
     */
    public void shutdown() {
        stopMonitoring();
        nodePool.clear();
    }
    
    /**
     * 资源统计信息
     */
    public static class ResourceStats {
        private final NodePool.PoolStats poolStats;
        private final long totalExpansions;
        private final long totalShrinks;
        private final boolean monitoring;
        
        public ResourceStats(NodePool.PoolStats poolStats, long totalExpansions, 
                           long totalShrinks, boolean monitoring) {
            this.poolStats = poolStats;
            this.totalExpansions = totalExpansions;
            this.totalShrinks = totalShrinks;
            this.monitoring = monitoring;
        }
        
        public NodePool.PoolStats getPoolStats() { return poolStats; }
        public long getTotalExpansions() { return totalExpansions; }
        public long getTotalShrinks() { return totalShrinks; }
        public boolean isMonitoring() { return monitoring; }
        
        @Override
        public String toString() {
            return String.format(
                "ResourceStats[%s, expansions=%d, shrinks=%d, monitoring=%b]",
                poolStats, totalExpansions, totalShrinks, monitoring);
        }
    }
    
    /**
     * 资源监控线程工厂
     */
    private static class ResourceMonitorThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "ResourceMonitor-" + threadNumber.getAndIncrement());
            t.setDaemon(true);
            return t;
        }
    }
}