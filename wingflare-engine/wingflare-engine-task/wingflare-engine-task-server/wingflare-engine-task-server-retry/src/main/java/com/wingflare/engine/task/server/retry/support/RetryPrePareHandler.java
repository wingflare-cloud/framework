package com.wingflare.engine.task.server.retry.support;

import com.wingflare.engine.task.server.retry.dto.RetryTaskPrepareDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-01-25
 */
public interface RetryPrePareHandler {

    boolean matches(Integer status);

    void handle(RetryTaskPrepareDTO jobPrepareDTO);
}
