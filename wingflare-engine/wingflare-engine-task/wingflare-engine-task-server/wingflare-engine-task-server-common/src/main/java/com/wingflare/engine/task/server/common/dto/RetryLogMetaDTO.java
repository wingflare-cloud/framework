package com.wingflare.engine.task.server.common.dto;

import com.wingflare.engine.task.common.core.util.JsonUtil;
import com.wingflare.engine.task.common.log.enums.LogTypeEnum;

/**
 * @author xiaowoniu
 * @date 2024-01-10 22:56:33
 * @since 3.2.0
 */
public class RetryLogMetaDTO extends LogMetaDTO {

    public RetryLogMetaDTO() {
        setLogType(LogTypeEnum.RETRY);
    }

    /**
     * 重试任务id
     */
    private Long retryTaskId;

    /**
     * 重试信息Id
     */
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

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}
