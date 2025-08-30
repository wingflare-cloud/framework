package com.wingflare.engine.task.server.common.dto;


/**
 * @author xiaowoniu
 * @date 2023-12-03 11:05:19
 * @since 2.5.0
 */
public class JobAlarmInfo extends AlarmInfo {

    private Long id;
    /**
     * 名称
     */
    private String jobName;

    /**
     * 任务信息id
     */
    private Long jobId;
    /**
     * 执行器名称
     */
    private String executorInfo;

    /**
     * 执行参数
     */
    private String argsStr;

    /**
     * 操作原因
     */
    private Integer operationReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(String executorInfo) {
        this.executorInfo = executorInfo;
    }

    public String getArgsStr() {
        return argsStr;
    }

    public void setArgsStr(String argsStr) {
        this.argsStr = argsStr;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }
}
