package com.wingflare.engine.task.client.retry.core.log;


import com.wingflare.engine.task.client.common.log.report.LogMeta;

/**
 * @author: xiaowoniu
 * @date : 2024-03-21
 * @since : 3.2.0
 */
public class RetryLogMeta extends LogMeta {

    private Long retryTaskId;

    private Long retryId;

    public Long getRetryTaskId() {
        return retryTaskId;
    }

    public void setRetryTaskId(Long retryTaskId) {
        this.retryTaskId = retryTaskId;
    }

    public Long getRetryId() {
        return retryId;
    }

    public void setRetryId(Long retryId) {
        this.retryId = retryId;
    }

}
