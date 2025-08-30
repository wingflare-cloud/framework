package com.wingflare.engine.task.server.common.dto;

import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;

/**
 * @author xiaowoniu
 * @date 2024-01-10 22:56:33
 * @since 2.6.0
 */
public class JobLogMetaDTO extends LogMetaDTO {

    public JobLogMetaDTO() {
        setLogType(LogTypeEnum.JOB);
    }

    /**
     * 任务信息id
     */
    private Long jobId;

    /**
     * 任务实例id
     */
    private Long taskBatchId;

    /**
     * 调度任务id
     */
    private Long taskId;

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

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
