package com.wingflare.engine.task.server.web.model.request;


import java.util.Set;

/**
 * @author opensnail
 * @date 2024-05-30 21:49:19
 * @since sj_1.0.0
 */
public class ExportJobVO {

    private Set<Long> jobIds;
    private String groupName;
    private String jobName;
    private Integer jobStatus;

    public Set<Long> getJobIds() {
        return jobIds;
    }

    public void setJobIds(Set<Long> jobIds) {
        this.jobIds = jobIds;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }
}
