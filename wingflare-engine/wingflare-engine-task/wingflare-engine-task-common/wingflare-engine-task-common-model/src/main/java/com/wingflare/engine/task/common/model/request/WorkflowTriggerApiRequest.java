package com.wingflare.engine.task.common.model.request;


import com.wingflare.engine.task.common.model.request.base.WorkflowTriggerRequest;
import jakarta.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-27
 */
public class WorkflowTriggerApiRequest extends WorkflowTriggerRequest {

    @NotNull(message = "jobId cannot be null")
    @Deprecated
    private Long jobId;

    /**
     * 临时任务参数
     */
    @Deprecated
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
