package com.wingflare.engine.task.server.service.service;

import com.wingflare.engine.task.common.model.response.base.JobBatchResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-06
 */
public interface JobBatchService {

    <T extends JobBatchResponse> T getJobBatchById(Long id, Class<T> clazz);


}
