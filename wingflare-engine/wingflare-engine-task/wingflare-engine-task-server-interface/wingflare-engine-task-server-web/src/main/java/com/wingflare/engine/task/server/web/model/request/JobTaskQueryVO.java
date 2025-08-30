package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

/**
 * @author opensnail
 * @date 2023-10-11 22:28:07
 * @since 2.4.0
 */
public class JobTaskQueryVO extends BaseQueryVO {
    private Long jobId;
    private Long taskBatchId;
    private Long parentId;
    private Integer taskStatus;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }
}
