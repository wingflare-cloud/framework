package com.wingflare.engine.task.client.retry.cache;

import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * @author: opensnail
 * @date : 2022-03-03 17:43
 */
public class RetryerInfoCache {

    private static Table<String, String, RetryerInfo> RETRY_HANDLER_REPOSITORY = HashBasedTable.create();

    public static RetryerInfo put(RetryerInfo retryerInfo) {
        return RETRY_HANDLER_REPOSITORY.put(retryerInfo.getScene(), retryerInfo.getExecutorClassName(), retryerInfo);
    }

    public static RetryerInfo get(String sceneName, String executorClassName) {
        return RETRY_HANDLER_REPOSITORY.get(sceneName, executorClassName);
    }

}
