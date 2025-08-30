package com.wingflare.engine.task.server.job.support.executor.workflow;

import com.wingflare.task.datasource.template.persistence.po.Job;

/**
 * @author xiaowoniu
 * @date 2023-12-24
 * @since 2.6.0
 */
public class WorkflowExecutorContext {

    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 任务id
     */
    private Long jobId;

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
     * TODO 父节点批次状态
     */
    private Integer parentJobTaskStatus;

    /**
     * 父节点批次操作原因状态
     */
    private Integer parentOperationReason;

    /**
     * 任务属性
     */
    private Job job;

    /**
     * 客户端返回的结果
     */
    private String taskResult;

    /**
     * 失败策略 1、跳过 2、阻塞
     */
    private Integer failStrategy;

    /**
     * 工作流节点状态 0、关闭、1、开启
     */
    private Integer workflowNodeStatus;

    /**
     * 条件节点的判定结果
     */
    private Object evaluationResult;

    /**
     * 调度任务id
     */
    private Long taskBatchId;

    /**
     * 节点信息
     */
    private String nodeInfo;

    /**
     * 任务批次状态
     */
    private Integer taskBatchStatus;

    /**
     * 操作原因
     */
    private Integer operationReason;

    /**
     * 任务状态
     */
    private Integer jobTaskStatus;

    /**
     * 日志信息
     */
    private String logMessage;

    /**
     * 执行策略 1、auto 2、manual 3、workflow
     */
    private Integer taskExecutorScene;

    /**
     * 1、任务节点 2、条件节点 3、回调节点
     */
    private Integer nodeType;

    /**
     * 工作流全局上下文
     */
    private String wfContext;

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

    public Integer getParentJobTaskStatus() {
        return parentJobTaskStatus;
    }

    public void setParentJobTaskStatus(Integer parentJobTaskStatus) {
        this.parentJobTaskStatus = parentJobTaskStatus;
    }

    public Integer getParentOperationReason() {
        return parentOperationReason;
    }

    public void setParentOperationReason(Integer parentOperationReason) {
        this.parentOperationReason = parentOperationReason;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(String taskResult) {
        this.taskResult = taskResult;
    }

    public Integer getFailStrategy() {
        return failStrategy;
    }

    public void setFailStrategy(Integer failStrategy) {
        this.failStrategy = failStrategy;
    }

    public Integer getWorkflowNodeStatus() {
        return workflowNodeStatus;
    }

    public void setWorkflowNodeStatus(Integer workflowNodeStatus) {
        this.workflowNodeStatus = workflowNodeStatus;
    }

    public Object getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(Object evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
    }

    public String getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(String nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public Integer getTaskBatchStatus() {
        return taskBatchStatus;
    }

    public void setTaskBatchStatus(Integer taskBatchStatus) {
        this.taskBatchStatus = taskBatchStatus;
    }

    public Integer getOperationReason() {
        return operationReason;
    }

    public void setOperationReason(Integer operationReason) {
        this.operationReason = operationReason;
    }

    public Integer getJobTaskStatus() {
        return jobTaskStatus;
    }

    public void setJobTaskStatus(Integer jobTaskStatus) {
        this.jobTaskStatus = jobTaskStatus;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    public Integer getTaskExecutorScene() {
        return taskExecutorScene;
    }

    public void setTaskExecutorScene(Integer taskExecutorScene) {
        this.taskExecutorScene = taskExecutorScene;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getWfContext() {
        return wfContext;
    }

    public void setWfContext(String wfContext) {
        this.wfContext = wfContext;
    }
}
