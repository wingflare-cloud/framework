package com.wingflare.engine.task.server.service.service;

import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;
import com.wingflare.engine.task.common.model.request.base.TriggerRetryRequest;
import com.wingflare.engine.task.common.model.response.base.RetryResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-25
 */
public interface RetryService {

    <T extends RetryResponse> T getRetryById(Long id, Class<T> clazz);

    boolean triggerRetry(TriggerRetryRequest triggerRetryRequest);

    boolean updateRetryStatus(StatusUpdateRequest requestDTO);
}
