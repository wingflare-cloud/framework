package com.wingflare.engine.task.server.retry.support.result;

import com.wingflare.engine.task.server.retry.support.RetryResultHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-11
 */
public abstract class AbstractRetryResultHandler implements RetryResultHandler {

    @Override
    public void handle(RetryResultContext context) {
        doHandler(context);
    }


    protected abstract void doHandler(RetryResultContext context);
}
