package com.wingflare.engine.task.datasource.template.persistence.dataobject;


import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-15 23:03:01
 * @since 2.4.0
 */
public class JobBatchQueryDO {

    private List<String> groupNames;
    private Integer taskBatchStatus;
    private String jobName;
    private Long jobId;
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

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
}
