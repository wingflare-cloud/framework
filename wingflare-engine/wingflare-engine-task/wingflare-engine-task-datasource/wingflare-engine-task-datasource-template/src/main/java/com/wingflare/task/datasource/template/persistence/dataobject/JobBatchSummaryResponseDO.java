package com.wingflare.task.datasource.template.persistence.dataobject;


/**
 * stop => 已经向客户端下发了执行任务指令
 * cancel => 未向客户端下发指令
 * fail => 客户端上报执行失败，或者服务端执行失败
 *
 * @author wodeyangzipingpingwuqi
 * @date 2023-11-22 11:39
 * @since 2.5.0
 */
public class JobBatchSummaryResponseDO {

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 任务id
     */
    private Long jobId;

    /**
     * 工作流任务id
     */
    //private Long workflowId;

    /**
     * 任务批次状态
     */
    private int taskBatchStatus;

    /**
     * 操作原因
     */
    private Long operationReason;

    /**
     * 操作原因总数
     */
    private Integer operationReasonTotal;

    /**
     * 执行成功-日志数量
     */
    private Integer successNum;

    /**
     * cancel执行失败-日志数量
     */
    private Integer cancelNum;

    /**
     * stop执行失败-日志数量
     */
    private Integer stopNum;

    /**
     * fail执行失败-日志数量
     */
    private Integer failNum;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public int getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(int taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }

    public Long getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Long operationReason) {
        this.operationReason = operationReason;
    }

    public Integer getOperationReasonTotal() {
        return operationReasonTotal;
    }

    public void setOperationReasonTotal(Integer operationReasonTotal) {
        this.operationReasonTotal = operationReasonTotal;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getCancelNum() {
        return cancelNum;
    }

    public void setCancelNum(Integer cancelNum) {
        this.cancelNum = cancelNum;
    }

    public Integer getStopNum() {
        return stopNum;
    }

    public void setStopNum(Integer stopNum) {
        this.stopNum = stopNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }
}
