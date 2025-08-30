package com.wingflare.engine.task.server.job.support;

import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;

/**
 * @author opensnail
 * @date 2023-10-22 09:34:00
 * @since 2.6.0
 */
public interface WorkflowPrePareHandler {

    boolean matches(Integer status);

    void handler(WorkflowTaskPrepareDTO workflowTaskPrepareDTO);
}
