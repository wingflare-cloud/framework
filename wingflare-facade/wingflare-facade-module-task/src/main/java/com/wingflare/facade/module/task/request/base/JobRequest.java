package com.wingflare.facade.module.task.request.base;


import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

/**
 * <p>
 * openapi与web共用的基类
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public class JobRequest {

    @NotNull(message = "id cannot be null", groups = Update.class)
    private Long id;

    /**
     * 组名称
     */
    @NotBlank(message = "groupName cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String groupName;

    /**
     * 名称
     */
    @NotBlank(message = "jobName cannot be null", groups = Create.class)
    private String jobName;

    /**
     * 重试状态 0、关闭、1、开启
     * {@link com.wingflare.lib.task.enums.StatusEnum}
     */
    @NotNull(message = "jobStatus cannot be null", groups = Create.class)
    private Integer jobStatus;

    /**
     * 执行方法参数
     */
    private String argsStr;

    /**
     * 参数类型 text/json
     */
    private Integer argsType;

    /**
     * 执行器路由策略
     */
    @NotNull(message = "routeKey cannot be null", groups = Create.class)
    private Integer routeKey;

    /**
     * 执行器类型
     * {@link com.wingflare.lib.task.enums.ExecutorTypeEnum}
     */
    @NotNull(message = "executorType cannot be null", groups = Create.class)
    private Integer executorType;

    /**
     * 执行器名称
     */
    @NotBlank(message = "executorInfo cannot be null", groups = Create.class)
    private String executorInfo;

    /**
     * 触发类型 2. 固定时间 3.CRON 表达式 99.工作流
     */
    @NotNull(message = "triggerType cannot be null", groups = Create.class)
    private Integer triggerType;

    /**
     * 间隔时长
     */
    @NotNull(message = "triggerInterval cannot be null", groups = Create.class)
    private String triggerInterval;

    /**
     * 阻塞策略 1、丢弃 2、覆盖 3、并行
     * {@link com.wingflare.lib.task.enums.JobBlockStrategyEnum}
     */
    @NotNull(message = "blockStrategy cannot be null", groups = Create.class)
    private Integer blockStrategy;

    /**
     * 任务执行超时时间，单位秒
     */
    @NotNull(message = "executorTimeout cannot be null", groups = Create.class)
    private Integer executorTimeout;

    /**
     * 最大重试次数
     */
    @NotNull(message = "maxRetryTimes cannot be null", groups = Create.class)
    private Integer maxRetryTimes;

    /**
     * 重试间隔(s)
     */
    @NotNull(message = "retryInterval cannot be null", groups = Create.class)
    private Integer retryInterval;

    /**
     * 任务类型
     * {@link com.wingflare.lib.task.enums.JobTaskTypeEnum}
     */
    @NotNull(message = "taskType cannot be null", groups = Create.class)
    private Integer taskType;

    /**
     * 并行数
     */
    @NotNull(message = "parallelNum cannot be null", groups = Create.class)
    private Integer parallelNum;

    /**
     * 描述
     */
    private String description;

    /**
     * 通知告警场景配置id列表
     */
    private Set<Long> notifyIds;

    /**
     * 负责人id
     */
    private Long ownerId;

    /**
     * 标签
     * json格式，如：{"key1":"value1","key2":"value2"}
     */
    private String labels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getArgsStr() {
        return argsStr;
    }

    public void setArgsStr(String argsStr) {
        this.argsStr = argsStr;
    }

    public Integer getArgsType() {
        return argsType;
    }

    public void setArgsType(Integer argsType) {
        this.argsType = argsType;
    }

    public Integer getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(Integer routeKey) {
        this.routeKey = routeKey;
    }

    public Integer getExecutorType() {
        return executorType;
    }

    public void setExecutorType(Integer executorType) {
        this.executorType = executorType;
    }

    public String getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(String executorInfo) {
        this.executorInfo = executorInfo;
    }

    public Integer getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(Integer triggerType) {
        this.triggerType = triggerType;
    }

    public String getTriggerInterval() {
        return triggerInterval;
    }

    public void setTriggerInterval(String triggerInterval) {
        this.triggerInterval = triggerInterval;
    }

    public Integer getBlockStrategy() {
        return blockStrategy;
    }

    public void setBlockStrategy(Integer blockStrategy) {
        this.blockStrategy = blockStrategy;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Integer getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setMaxRetryTimes(Integer maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

    public Integer getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getParallelNum() {
        return parallelNum;
    }

    public void setParallelNum(Integer parallelNum) {
        this.parallelNum = parallelNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Long> getNotifyIds() {
        return notifyIds;
    }

    public void setNotifyIds(Set<Long> notifyIds) {
        this.notifyIds = notifyIds;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
