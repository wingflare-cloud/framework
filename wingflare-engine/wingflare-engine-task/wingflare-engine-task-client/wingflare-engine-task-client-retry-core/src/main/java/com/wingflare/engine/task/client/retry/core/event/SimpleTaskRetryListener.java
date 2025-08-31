package com.wingflare.engine.task.client.retry.core.event;


import com.wingflare.engine.task.common.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: opensnail
 * @date : 2022-03-04 16:55
 */
public class SimpleTaskRetryListener implements TaskListener {

    private final static Logger log = LoggerFactory.getLogger(SimpleTaskRetryListener.class);

    @Override
    public void beforeRetry(String sceneName, String executorClassName, Object[] params) {
        log.debug("------> beforeRetry sceneName:[{}] executorClassName:[{}] params:[{}]",
                sceneName, executorClassName, JsonUtil.toJsonString(params));
    }

    @Override
    public void successOnRetry(Object result, String sceneName, String executorClassName) {
        log.debug("------> successOnRetry sceneName:[{}] executorClassName:[{}] result:[{}]",
                sceneName, executorClassName, JsonUtil.toJsonString(result));
    }

    @Override
    public void failureOnRetry(String sceneName, String executorClassName, Throwable e) {
        log.debug("------> failureOnRetry sceneName:[{}] executorClassName:[{}]", sceneName, executorClassName, e);
    }
}
