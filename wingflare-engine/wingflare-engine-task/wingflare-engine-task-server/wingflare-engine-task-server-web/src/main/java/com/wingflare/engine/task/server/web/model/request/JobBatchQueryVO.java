package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-11 22:28:07
 * @since 2.4.0
 */
public class JobBatchQueryVO extends BaseQueryVO {
    private Long jobId;
    private List<Integer> taskBatchStatus;
    private String groupName;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public List<Integer> getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(List<Integer> taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
