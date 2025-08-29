package com.wingflare.engine.task.common.model.request.base;


import jakarta.validation.constraints.NotNull;


public class WorkflowTriggerRequest {

    @NotNull(message = "workflowId cannot be null")
    private Long workflowId;

    /**
     * 上下文
     */
    private String tmpWfContext;

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getTmpWfContext() {
        return tmpWfContext;
    }

    public void setTmpWfContext(String tmpWfContext) {
        this.tmpWfContext = tmpWfContext;
    }

}
