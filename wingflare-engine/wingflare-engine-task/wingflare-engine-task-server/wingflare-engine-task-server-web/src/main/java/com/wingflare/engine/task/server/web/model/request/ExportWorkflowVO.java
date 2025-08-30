package com.wingflare.engine.task.server.web.model.request;


import java.util.Set;

/**
 * @author: opensnail
 * @date : 2024-05-30
 * @since : sj_1.0.0
 */
public class ExportWorkflowVO {

    private Set<Long> workflowIds;

    private String groupName;

    private String workflowName;

    private Integer workflowStatus;

    public Set<Long> getWorkflowIds() {
        return workflowIds;
    }

    public void setWorkflowIds(Set<Long> workflowIds) {
        this.workflowIds = workflowIds;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public Integer getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(Integer workflowStatus) {
        this.workflowStatus = workflowStatus;
    }
}
