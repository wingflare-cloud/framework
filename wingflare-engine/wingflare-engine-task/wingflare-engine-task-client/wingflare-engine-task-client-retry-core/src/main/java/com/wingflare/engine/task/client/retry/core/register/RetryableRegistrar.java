package com.wingflare.engine.task.client.retry.core.register;

import com.wingflare.engine.task.client.common.Lifecycle;
import com.wingflare.engine.task.client.retry.core.Scanner;
import com.wingflare.engine.task.client.retry.core.cache.RetryerInfoCache;
import com.wingflare.engine.task.client.retry.core.exception.TaskRetryClientException;
import com.wingflare.engine.task.client.retry.core.retryer.RetryerInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2022-02-10 09:12
 */
@Component
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
