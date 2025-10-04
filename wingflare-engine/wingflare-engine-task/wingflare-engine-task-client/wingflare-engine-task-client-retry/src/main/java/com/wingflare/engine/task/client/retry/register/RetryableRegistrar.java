package com.wingflare.engine.task.client.retry.register;


import com.wingflare.api.lifecycle.Lifecycle;
import com.wingflare.engine.task.client.retry.Scanner;
import com.wingflare.engine.task.client.retry.cache.RetryerInfoCache;
import com.wingflare.engine.task.client.retry.exception.TaskRetryClientException;
import com.wingflare.engine.task.client.retry.retryer.RetryerInfo;
import jakarta.annotation.Resource;

import java.util.List;
import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2022-02-10 09:12
 */
public class RetryableRegistrar implements Lifecycle {

    @Resource
    private List<Scanner> scanners;

    public void registerRetryHandler(RetryerInfo retryerInfo) {

        if (Objects.nonNull(RetryerInfoCache.get(retryerInfo.getScene(), retryerInfo.getExecutorClassName()))) {
            throw new TaskRetryClientException("Scene [{}] already exists in class [{}]", retryerInfo.getExecutorClassName(), retryerInfo.getScene());
        }

        RetryerInfoCache.put(retryerInfo);
    }

    public void registerRetryHandler(List<RetryerInfo> contextList) {
        for (RetryerInfo retryerInfo : contextList) {
            registerRetryHandler(retryerInfo);
        }
    }

    @Override
    public void start() {
        for (Scanner scanner : scanners) {
            this.registerRetryHandler(scanner.doScan());
        }
    }

    @Override
    public void close() {
    }
}
