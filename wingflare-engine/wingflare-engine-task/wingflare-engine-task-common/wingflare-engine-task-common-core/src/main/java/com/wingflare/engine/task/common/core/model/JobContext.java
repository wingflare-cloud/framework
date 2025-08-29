package com.wingflare.engine.task.common.core.model;


import java.util.List;
import java.util.Map;

/**
 * @author: opensnail
 * @date : 2023-09-27 09:40
 */
public class JobContext {

    private Long jobId;

    private Long taskBatchId;

    private Long workflowTaskBatchId;

    private Long workflowNodeId;

    private Long taskId;

    private String namespaceId;

    private String groupName;

    private String executorInfo;

    private Integer taskType;

    private Integer parallelNum;

    private Integer shardingTotal;

    private Integer shardingIndex;

    private Integer executorTimeout;

//    private String argsStr;

    /**
     * 重试场景 auto、manual
     */
    private Integer retryScene;

    /**
     * 是否是重试流量
     */
    private Boolean retryStatus = Boolean.FALSE;

    /**
     * Map集合列表
     */
    private List<Object> taskList;

    /**
     * Map名称
     */
    private String taskName;

    /**
     * 动态分片所处的阶段
     */
    private Integer mrStage;

    /**
     * 工作流全局上下文
     */
    private Map<String, Object> wfContext;

    /**
     * 新增或者改动的上下文
     */
    private Map<String, Object> changeWfContext;

    /**
     * 定时任务参数
     */
    private JobArgsHolder jobArgsHolder;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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

    public String getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(String executorInfo) {
        this.executorInfo = executorInfo;
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

    public Integer getShardingTotal() {
        return shardingTotal;
    }

    public void setShardingTotal(Integer shardingTotal) {
        this.shardingTotal = shardingTotal;
    }

    public Integer getShardingIndex() {
        return shardingIndex;
    }

    public void setShardingIndex(Integer shardingIndex) {
        this.shardingIndex = shardingIndex;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Integer getRetryScene() {
        return retryScene;
    }

    public void setRetryScene(Integer retryScene) {
        this.retryScene = retryScene;
    }

    public Boolean getRetryStatus() {
        return retryStatus;
    }

    public void setRetryStatus(Boolean retryStatus) {
        this.retryStatus = retryStatus;
    }

    public List<Object> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Object> taskList) {
        this.taskList = taskList;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getMrStage() {
        return mrStage;
    }

    public void setMrStage(Integer mrStage) {
        this.mrStage = mrStage;
    }

    public Map<String, Object> getWfContext() {
        return wfContext;
    }

    public void setWfContext(Map<String, Object> wfContext) {
        this.wfContext = wfContext;
    }

    public Map<String, Object> getChangeWfContext() {
        return changeWfContext;
    }

    public void setChangeWfContext(Map<String, Object> changeWfContext) {
        this.changeWfContext = changeWfContext;
    }

    public JobArgsHolder getJobArgsHolder() {
        return jobArgsHolder;
    }

    public void setJobArgsHolder(JobArgsHolder jobArgsHolder) {
        this.jobArgsHolder = jobArgsHolder;
    }
}
