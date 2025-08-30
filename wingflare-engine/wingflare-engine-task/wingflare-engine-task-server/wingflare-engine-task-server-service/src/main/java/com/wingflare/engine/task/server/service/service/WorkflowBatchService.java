package com.wingflare.engine.task.server.service.service;

import com.wingflare.engine.task.common.model.response.base.WorkflowDetailResponse;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-06
 */
public interface WorkflowBatchService {

    <T extends WorkflowDetailResponse> T getWorkflowBatchById(Long id, Class<T> clazz);
}
