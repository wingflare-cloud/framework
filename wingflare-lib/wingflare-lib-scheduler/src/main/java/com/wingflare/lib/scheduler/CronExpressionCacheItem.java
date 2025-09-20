package com.wingflare.lib.scheduler;


/**
 * Cron表达式缓存项
 */
public class CronExpressionCacheItem {

    private final String expression;
    private final long timestamp;

    public CronExpressionCacheItem(String expression) {
        this.expression = expression;
        this.timestamp = System.currentTimeMillis();
    }

    public String getExpression() {
        return expression;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
