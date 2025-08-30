package com.wingflare.engine.task.server.retry.support.result;

import com.wingflare.engine.task.common.core.enums.RetryTaskStatusEnum;
import com.wingflare.engine.task.server.retry.dto.BaseDTO;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-02-02
 */
public class RetryResultContext extends BaseDTO {

//    /**
//     * 客户端返回的结果
//     * @see RetryResultStatusEnum
//     */
//    private Integer resultStatus;

    /**
     * 重试任务状态
     * @see RetryTaskStatusEnum
     */
    private Integer taskStatus;
    private Integer operationReason;

    private boolean incrementRetryCount;
    private String resultJson;
    private String idempotentId;
    private String exceptionMsg;

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public boolean isIncrementRetryCount() {
        return incrementRetryCount;
    }

    public void setIncrementRetryCount(boolean incrementRetryCount) {
        this.incrementRetryCount = incrementRetryCount;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public String getIdempotentId() {
        return idempotentId;
    }

    public void setIdempotentId(String idempotentId) {
        this.idempotentId = idempotentId;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
