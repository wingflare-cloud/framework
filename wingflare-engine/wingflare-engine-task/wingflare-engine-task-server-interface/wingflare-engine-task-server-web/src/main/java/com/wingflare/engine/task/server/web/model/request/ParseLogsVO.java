package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 解析参数模型
 *
 * @author: opensnail
 * @date: 2023-07-15 23:15
 */
public class ParseLogsVO {

    /**
     * 客户端打印的上报日志信息
     */
    @NotBlank(message = "Group name cannot be null")
    private String logStr;

    /**
     * 组名称
     */
    @NotBlank(message = "Group name cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String groupName;

    @NotNull(message = "Retry status cannot be null")
    private Integer retryStatus;

    public String getLogStr() {
        return logStr;
    }

    public void setLogStr(String logStr) {
        this.logStr = logStr;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Integer retryStatus) {
        this.retryStatus = retryStatus;
    }
}
