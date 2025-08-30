package com.wingflare.engine.task.server.job.dto;

/**
 * @author opensnail
 * @date 2023-09-25 22:42:21
 * @since 2.4.0
 */
public class JobTaskPrepareDTO {

    private Long jobId;

    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 下次触发时间
     */
    private long nextTriggerAt;

    /**
     * 阻塞策略 1、丢弃 2、覆盖 3、并行
     */
    private Integer blockStrategy;

    /**
     * 任务类型
     */
    private Integer taskType;

    /**
     * 任务执行超时时间，单位秒
     */
    private Integer executorTimeout;

    private Long taskBatchId;

    private String clientId;

    /**
     * 任务执行时间
     */
    private Long executionAt;

    private boolean onlyTimeoutCheck;

    /**
     * 执行策略 1、auto_job 2、manual_job 3、auto_workflow 4、manual_workflow
     */
    private Integer taskExecutorScene;

    /**
     * 工作流任务批次id
     */
    private Long workflowTaskBatchId;

    /**
     * 工作流节点id
     */
    private Long workflowNodeId;

    /**
     * 工作流父节点id
     */
    private Long parentWorkflowNodeId;

    /**
     * 临时任务参数
     */
    private String tmpArgsStr;

    /**
     * 标签
     * json格式，如：{"key1":"value1","key2":"value2"}
     */
    private String labels;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

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

    public long getNextTriggerAt() {
        return nextTriggerAt;
    }

    public void setNextTriggerAt(long nextTriggerAt) {
        this.nextTriggerAt = nextTriggerAt;
    }

    public Integer getBlockStrategy() {
        return blockStrategy;
    }

    public void setBlockStrategy(Integer blockStrategy) {
        this.blockStrategy = blockStrategy;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getExecutionAt() {
        return executionAt;
    }

    public void setExecutionAt(Long executionAt) {
        this.executionAt = executionAt;
    }

    public boolean isOnlyTimeoutCheck() {
        return onlyTimeoutCheck;
    }

    public void setOnlyTimeoutCheck(boolean onlyTimeoutCheck) {
        this.onlyTimeoutCheck = onlyTimeoutCheck;
    }

    public Integer getTaskExecutorScene() {
        return taskExecutorScene;
    }

    public void setTaskExecutorScene(Integer taskExecutorScene) {
        this.taskExecutorScene = taskExecutorScene;
    }

    public Long getWorkflowTaskBatchId() {
        return workflowTaskBatchId;
    }

    public void setWorkflowTaskBatchId(Long workflowTaskBatchId) {
        this.workflowTaskBatchId = workflowTaskBatchId;
    }

    public Long getWorkflowNodeId() {
        return workflowNodeId;
    }

    public void setWorkflowNodeId(Long workflowNodeId) {
        this.workflowNodeId = workflowNodeId;
    }

    public Long getParentWorkflowNodeId() {
        return parentWorkflowNodeId;
    }

    public void setParentWorkflowNodeId(Long parentWorkflowNodeId) {
        this.parentWorkflowNodeId = parentWorkflowNodeId;
    }

    public String getTmpArgsStr() {
        return tmpArgsStr;
    }

    public void setTmpArgsStr(String tmpArgsStr) {
        this.tmpArgsStr = tmpArgsStr;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
