package com.wingflare.engine.task.server.retry.support;

import com.wingflare.engine.task.server.retry.support.result.RetryResultContext;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-02
 */
public interface RetryResultHandler {

    boolean supports(RetryResultContext context);

    void handle(RetryResultContext context);
}
