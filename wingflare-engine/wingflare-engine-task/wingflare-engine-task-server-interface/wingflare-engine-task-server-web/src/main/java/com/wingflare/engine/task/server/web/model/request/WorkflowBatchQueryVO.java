package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

/**
 * @author xiaowoniu
 * @date 2023-12-23 17:49:59
 * @since 2.6.0
 */
public class WorkflowBatchQueryVO extends BaseQueryVO {
    private String groupName;
    private Long workflowId;
    private Integer taskBatchStatus;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(Integer taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }
}
