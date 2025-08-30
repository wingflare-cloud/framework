package com.wingflare.engine.task.server.common.vo;

import com.wingflare.engine.task.server.common.vo.base.BaseQueryVO;

/**
 * @author opensnail
 * @date 2023-10-11 22:28:07
 * @since 2.4.0
 */
@Deprecated
public class JobLogQueryVO extends BaseQueryVO {
    private Long jobId;
    private Long taskBatchId;
    private Long taskId;
    private String sid;
    private Long startRealTime;

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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Long getStartRealTime() {
        return startRealTime;
    }

    public void setStartRealTime(Long startRealTime) {
        this.startRealTime = startRealTime;
    }
}
