package com.wingflare.task.datasource.template.persistence.dataobject;


import java.util.List;

/**
 * @author opensnail
 * @date 2023-12-23 23:03:01
 * @since 2.6.0
 */
public class WorkflowBatchQueryDO {

    private List<String> groupNames;
    private Integer taskBatchStatus;
    private String workflowName;
    private Long workflowId;
    /**
     * 命名空间id
     */
    private String namespaceId;

    public List<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }

    public Integer getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(Integer taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
}
