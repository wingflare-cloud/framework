package com.wingflare.engine.task.client.retry.core.callback.complete;


import org.springframework.stereotype.Component;

/**
 * @author: opensnail
 * @date : 2023-01-10 14:47
 */
@Component
public class SimpleRetryCompleteCallback implements RetryCompleteCallback {

    @Override
    public void doSuccessCallback(String sceneName, String executorName, Object[] params) {

    }

    @Override
    public void doMaxRetryCallback(String sceneName, String executorName, Object[] params) {
    }
}
