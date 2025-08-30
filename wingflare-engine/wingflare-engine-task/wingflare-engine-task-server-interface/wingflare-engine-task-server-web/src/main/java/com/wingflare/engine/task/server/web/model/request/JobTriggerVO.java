package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotNull;


public class JobTriggerVO {

    @NotNull(message = "jobId cannot be null")
    private Long jobId;

    /**
     * 临时任务参数
     */
    private String tmpArgsStr;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getTmpArgsStr() {
        return tmpArgsStr;
    }

    public void setTmpArgsStr(String tmpArgsStr) {
        this.tmpArgsStr = tmpArgsStr;
    }
}
