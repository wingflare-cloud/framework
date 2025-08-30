package com.wingflare.engine.task.server.service.service;

import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;
import com.wingflare.engine.task.common.model.request.base.WorkflowTriggerRequest;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-06
 */
public interface WorkflowService {

    boolean deleteWorkflowByIds(Set<Long> ids);

    boolean triggerWorkFlow(WorkflowTriggerRequest request);

    boolean updateWorkFlowStatus(StatusUpdateRequest requestDTO);

}
