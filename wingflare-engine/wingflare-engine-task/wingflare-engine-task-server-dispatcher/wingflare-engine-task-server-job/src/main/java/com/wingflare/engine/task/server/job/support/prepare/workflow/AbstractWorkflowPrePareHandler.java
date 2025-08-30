package com.wingflare.engine.task.server.job.support.prepare.workflow;

import com.wingflare.engine.task.server.job.dto.WorkflowTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.WorkflowPrePareHandler;

/**
 * @author: xiaowoniu
 * @date : 2023-12-22 08:57
 * @since : 2.6.0
 */
public abstract class AbstractWorkflowPrePareHandler implements WorkflowPrePareHandler {

    @Override
    public void handler(WorkflowTaskPrepareDTO workflowTaskPrepareDTO) {

        doHandler(workflowTaskPrepareDTO);
    }

    protected abstract void doHandler(WorkflowTaskPrepareDTO jobPrepareDTO);

}
