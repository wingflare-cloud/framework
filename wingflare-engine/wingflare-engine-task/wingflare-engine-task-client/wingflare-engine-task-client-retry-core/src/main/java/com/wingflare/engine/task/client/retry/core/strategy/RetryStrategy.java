package com.wingflare.engine.task.client.retry.core.strategy;

import com.wingflare.engine.task.client.retry.core.retryer.RetryType;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerResultContext;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:33
 */
public interface RetryStrategy {

    boolean supports(int stage, RetryType retryType);

    RetryerResultContext openRetry(String sceneName, String executorClassName, Object[] params);

}
