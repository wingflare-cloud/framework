package com.wingflare.engine.task.client.retry.core;

public interface RetryCondition {

    class NoRetry implements RetryCondition {
        @Override
        public boolean shouldRetry(Object returnResult) {
            return false;
        }
    }

    boolean shouldRetry(Object returnResult);
}
