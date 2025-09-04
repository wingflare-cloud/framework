package com.wingflare.engine.task.datasource.template.persistence.dataobject.log;

import com.wingflare.engine.task.datasource.template.persistence.dataobject.common.PageQueryDO;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-03-29
 */
public class LogPageQueryDO extends PageQueryDO {
    private Long startRealTime;
    private Long jobId;
    private Long taskBatchId;
    private Long taskId;
    private boolean searchCount;

    public Long getStartRealTime() {
        return startRealTime;
    }

    public void setStartRealTime(Long startRealTime) {
        this.startRealTime = startRealTime;
    }

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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }
}
