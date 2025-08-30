package com.wingflare.engine.task.server.web.model.request;

import com.wingflare.engine.task.common.core.enums.RetryStatusEnum;
import jakarta.validation.constraints.NotBlank;

/**
 * 重试数据模型
 *
 * @author opensnail
 * @date 2023-09-29
 * @since 2.0
 */
public class RetrySaveRequestVO {

    /**
     * 组名称
     */
    @NotBlank(message = "Group name cannot be null")
    private String groupName;

    /**
     * 场景名称
     */
    @NotBlank(message = "Scene name cannot be null")
    private String sceneName;

    /**
     * 幂等id(同一个场景下正在重试中的idempotentId不能重复)
     */
    @NotBlank(message = "Idempotent ID cannot be empty")
    private String idempotentId;

    /**
     * 业务编号
     */
    private String bizNo;

    /**
     * 重试参数
     */
    private String argsStr;

    /**
     * 扩展重试参数
     */
    private String extAttrs;

    /**
     * 执行器名称(全类路径)
     */
    @NotBlank(message = "Executor name cannot be empty")
    private String executorName;

    /**
     * 重试状态 {@link RetryStatusEnum}
     */
    private Integer retryStatus;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getIdempotentId() {
        return idempotentId;
    }

    public void setIdempotentId(String idempotentId) {
        this.idempotentId = idempotentId;
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public String getArgsStr() {
        return argsStr;
    }

    public void setArgsStr(String argsStr) {
        this.argsStr = argsStr;
    }

    public String getExtAttrs() {
        return extAttrs;
    }

    public void setExtAttrs(String extAttrs) {
        this.extAttrs = extAttrs;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public Integer getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Integer retryStatus) {
        this.retryStatus = retryStatus;
    }
}
