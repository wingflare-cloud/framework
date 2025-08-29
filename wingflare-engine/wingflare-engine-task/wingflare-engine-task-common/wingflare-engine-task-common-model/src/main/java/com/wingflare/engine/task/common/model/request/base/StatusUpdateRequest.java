package com.wingflare.engine.task.common.model.request.base;


import jakarta.validation.constraints.NotNull;

/**
 * 此类为更新任务的状态 【job】【workflow】【retry】共用类
 *
 * @author opensnail
 * @date 2023-10-15 16:06:20
 * @since 2.4.0
 */
public class StatusUpdateRequest {

    @NotNull(message = "id cannot be null")
    private Long id;

    @Deprecated(since = "1.7.0")
    private Integer jobStatus;

    @Deprecated(since = "1.7.0")
    private Integer retryStatus;

    @NotNull(message = "status cannot be null")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Integer retryStatus) {
        this.retryStatus = retryStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
