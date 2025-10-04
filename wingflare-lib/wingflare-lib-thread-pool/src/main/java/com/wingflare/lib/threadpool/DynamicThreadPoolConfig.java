package com.wingflare.lib.threadpool;


import java.util.concurrent.TimeUnit;

/**
 * 线程池配置类
 */
public record DynamicThreadPoolConfig(int corePoolSize, int maximumPoolSize, int queueCapacity, long keepAliveTime,
                                      TimeUnit timeUnit, boolean allowCoreThreadTimeout, String namePrefix) {
    public DynamicThreadPoolConfig {
        // 校验配置合法性
        if (corePoolSize < 0) throw new IllegalArgumentException("核心线程数不能为负");
        if (maximumPoolSize <= 0 || maximumPoolSize < corePoolSize)
            throw new IllegalArgumentException("最大线程数必须大于核心线程数且为正数");
        if (queueCapacity < 0) throw new IllegalArgumentException("队列容量不能为负");
        if (keepAliveTime < 0) throw new IllegalArgumentException("存活时间不能为负");
        if (timeUnit == null) throw new NullPointerException("时间单位不能为null");
        if (namePrefix == null || namePrefix.isBlank()) throw new IllegalArgumentException("线程池前缀名不能为空");
    }

}
