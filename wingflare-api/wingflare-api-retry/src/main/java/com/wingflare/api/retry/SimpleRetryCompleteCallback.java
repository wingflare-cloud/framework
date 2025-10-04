package com.wingflare.api.retry;


/**
 * @author: opensnail
 * @date : 2023-01-10 14:47
 */
public class SimpleRetryCompleteCallback implements RetryCompleteCallback {

    @Override
    public void doSuccessCallback(String sceneName, String executorName, Object[] params) {

    }

    @Override
    public void doMaxRetryCallback(String sceneName, String executorName, Object[] params) {
    }
}
