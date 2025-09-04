package com.wingflare.engine.task.datasource.template.persistence.dataobject;


import java.util.List;

/**
 * @author zuoJunLin
 * @date 2023-12-02 23:03:01
 * @since 2.4.0
 */
public class JobNotifyConfigQueryDO {

    private List<String> groupNames;

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
