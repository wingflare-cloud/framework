package com.wingflare.lib.scheduler;


import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 简单的本地缓存实现
 */
public class SimpleCache<K, V> {

    private final Map<K, CacheEntry> cache = new ConcurrentHashMap<>();
    private final int maxSize;
    private final long expireAfterWriteMs;
    private ScheduledExecutorService cleanupScheduler;

    public SimpleCache(int maxSize, long expireAfterWriteMs) {
        this.maxSize = maxSize;
        this.expireAfterWriteMs = expireAfterWriteMs;

        // 启动定期清理过期项的线程
        initCleanupScheduler();
    }

    private void initCleanupScheduler() {
        cleanupScheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "cache-cleanup-thread");
            thread.setDaemon(true); // 设置为守护线程，避免阻止JVM退出
            return thread;
        });
        cleanupScheduler.scheduleAtFixedRate(this::cleanup, 1, 1, TimeUnit.MINUTES);
    }

    public V get(K key) {
        CacheEntry entry = cache.get(key);
        if (entry == null) {
            return null;
        }

        // 检查是否过期
        if (System.currentTimeMillis() - entry.getTimestamp() > expireAfterWriteMs) {
            cache.remove(key);
            return null;
        }

        return (V) entry.getValue();
    }

    public void put(K key, V value) {
        // 如果缓存已满，删除最旧的项
        if (cache.size() >= maxSize) {
            cleanup();
            // 如果仍然满，删除最旧的项
            if (cache.size() >= maxSize) {
                K oldestKey = null;
                long oldestTime = Long.MAX_VALUE;
                for (Map.Entry<K, CacheEntry> entry : cache.entrySet()) {
                    if (entry.getValue().getTimestamp() < oldestTime) {
                        oldestTime = entry.getValue().getTimestamp();
                        oldestKey = entry.getKey();
                    }
                }
                if (oldestKey != null) {
                    cache.remove(oldestKey);
                }
            }
        }

        cache.put(key, new CacheEntry(value));
    }

    // 清理过期项
    private void cleanup() {
        long now = System.currentTimeMillis();
        for (Iterator<Map.Entry<K, CacheEntry>> iterator = cache.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<K, CacheEntry> entry = iterator.next();
            if (now - entry.getValue().getTimestamp() > expireAfterWriteMs) {
                iterator.remove();
            }
        }
    }

    // 添加关闭方法
    public void shutdown() {
        if (cleanupScheduler != null) {
            cleanupScheduler.shutdownNow();
            try {
                if (!cleanupScheduler.awaitTermination(1, TimeUnit.SECONDS)) {
                    System.err.println("Cache cleanup scheduler did not terminate properly");
                }
            } catch (InterruptedException e) {
                cleanupScheduler.shutdownNow();
            }
            cleanupScheduler = null;
        }
        cache.clear();
    }

}
