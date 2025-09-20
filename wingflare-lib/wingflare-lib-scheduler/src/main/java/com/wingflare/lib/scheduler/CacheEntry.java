package com.wingflare.lib.scheduler;


/**
 * 简单的缓存项，用于Cron表达式缓存
 */
public class CacheEntry {

    private final Object value;
    private final long timestamp;

    public CacheEntry(Object value) {
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    public Object getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
