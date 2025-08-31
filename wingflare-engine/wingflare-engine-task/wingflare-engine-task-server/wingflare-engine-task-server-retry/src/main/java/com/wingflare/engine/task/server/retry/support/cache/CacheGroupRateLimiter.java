package com.wingflare.engine.task.server.retry.support.cache;

import com.wingflare.engine.task.common.log.TaskEngineLog;
import com.wingflare.engine.task.server.common.Lifecycle;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Component;

/**
 * 缓存组组限流组件
 *
 * @author opensnail
 * @date 2022-21-58
 * @since 2.0
 */
@Component
public class CacheGroupRateLimiter implements Lifecycle {

    private static Cache<String, RateLimiter> CACHE;

    public static Cache<String, RateLimiter> getCACHE() {
        return CACHE;
    }

    /**
     * 获取所有缓存
     *
     * @return 缓存对象
     */
    public static Cache<String, RateLimiter> getAll() {
        return CACHE;
    }

    /**
     * 获取所有缓存
     *
     * @return 缓存对象
     */
    public static RateLimiter getRateLimiterByKey(String hostId) {
        return CACHE.getIfPresent(hostId);
    }

    @Override
    public void start() {
        TaskEngineLog.LOCAL.info("CacheGroupRateLimiter start");
        CACHE = CacheBuilder.newBuilder()
                // 设置并发级别为cpu核心数
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    @Override
    public void close() {
        TaskEngineLog.LOCAL.info("CacheGroupRateLimiter stop");
    }
}
