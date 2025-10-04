package com.wingflare.engine.task.client.retry.strategy;

import com.wingflare.api.retry.enums.RetryType;
import com.wingflare.engine.task.client.retry.retryer.RetryerResultContext;

/**
 * @author: opensnail
 * @date : 2022-03-03 14:33
 */
public interface RetryStrategy {

    boolean supports(int stage, RetryType retryType);

    RetryerResultContext openRetry(String sceneName, String executorClassName, Object[] params);

}
