package com.wingflare.facade.module.task.response.base;


import com.wingflare.facade.module.task.request.CallbackConfig;
import com.wingflare.facade.module.task.request.DecisionConfigRequest;
import com.wingflare.facade.module.task.request.JobTaskConfigRequest;

import java.util.List;
import java.util.Set;

/**
 * @author xiaowoniu
 * @date 2023-12-14 22:59:33
 * @since 2.6.0
 */
public class WorkflowDetailResponse {

    /**
     * 工作流ID
     */
    private Long id;

    /**
     * 组名称
     */
    private String workflowName;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 触发类型
     */
    private Integer triggerType;

    /**
     * 阻塞策略
     */
    private Integer blockStrategy;

    /**
     * 触发间隔
     */
    private String triggerInterval;

    /**
     * 超时时间
     */
    private Integer executorTimeout;

    /**
     * 0、关闭、1、开启
     */
    private Integer workflowStatus;

    /**
     * see: {@link com.wingflare.lib.task.enums.JobTaskBatchStatusEnum}
     */
    private Integer workflowBatchStatus;

    /**
     * 工作流上下文
     */
    private String wfContext;

    /**
     * DAG节点配置
     */
    private NodeConfig nodeConfig;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(Integer triggerType) {
        this.triggerType = triggerType;
    }

    public Integer getBlockStrategy() {
        return blockStrategy;
    }

    public void setBlockStrategy(Integer blockStrategy) {
        this.blockStrategy = blockStrategy;
    }

    public String getTriggerInterval() {
        return triggerInterval;
    }

    public void setTriggerInterval(String triggerInterval) {
        this.triggerInterval = triggerInterval;
    }

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Integer getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(Integer workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public Integer getWorkflowBatchStatus() {
        return workflowBatchStatus;
    }

    public void setWorkflowBatchStatus(Integer workflowBatchStatus) {
        this.workflowBatchStatus = workflowBatchStatus;
    }

    public String getWfContext() {
        return wfContext;
    }

    public void setWfContext(String wfContext) {
        this.wfContext = wfContext;
    }

    public NodeConfig getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(NodeConfig nodeConfig) {
        this.nodeConfig = nodeConfig;
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

    public static class NodeConfig {

        /**
         * 1、任务节点 2、条件节点 3、回调节点
         */
        private Integer nodeType;

        /**
         * 节点信息
         */
        private List<NodeInfo> conditionNodes;

        /**
         * 子节点
         */
        private NodeConfig childNode;

        public Integer getNodeType() {
            return nodeType;
        }

        public void setNodeType(Integer nodeType) {
            this.nodeType = nodeType;
        }

        public List<NodeInfo> getConditionNodes() {
            return conditionNodes;
        }

        public void setConditionNodes(List<NodeInfo> conditionNodes) {
            this.conditionNodes = conditionNodes;
        }

        public NodeConfig getChildNode() {
            return childNode;
        }

        public void setChildNode(NodeConfig childNode) {
            this.childNode = childNode;
        }
    }

    public static class NodeInfo {

        /**
         * 节点ID
         */
        private Long id;

        /**
         * 1、任务节点 2、条件节点 3、回调节点
         */
        private Integer nodeType;

        /**
         * 节点名称
         */
        private String nodeName;

        /**
         * 优先级
         */
        private Integer priorityLevel;

        /**
         * 工作流状态  0、关闭、1、开启
         */
        private Integer workflowNodeStatus;

        /**
         * 失败策略 1、跳过 2、阻塞
         */
        private Integer failStrategy;

        /**
         * 任务批次状态
         */
        private Integer taskBatchStatus;

        /**
         * 判定配置
         */
        private DecisionConfigRequest decision;

        /**
         * 回调配置
         */
        private CallbackConfig callback;

        /**
         * 任务配置
         */
        private JobTaskConfigRequest jobTask;

        /**
         * 定时任务批次信息
         */
        private List<JobBatchResponse> jobBatchList;

        /**
         * 子节点
         */
        private NodeConfig childNode;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getNodeType() {
            return nodeType;
        }

        public void setNodeType(Integer nodeType) {
            this.nodeType = nodeType;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public Integer getPriorityLevel() {
            return priorityLevel;
        }

        public void setPriorityLevel(Integer priorityLevel) {
            this.priorityLevel = priorityLevel;
        }

        public Integer getWorkflowNodeStatus() {
            return workflowNodeStatus;
        }

        public void setWorkflowNodeStatus(Integer workflowNodeStatus) {
            this.workflowNodeStatus = workflowNodeStatus;
        }

        public Integer getFailStrategy() {
            return failStrategy;
        }

        public void setFailStrategy(Integer failStrategy) {
            this.failStrategy = failStrategy;
        }

        public Integer getTaskBatchStatus() {
            return taskBatchStatus;
        }

        public void setTaskBatchStatus(Integer taskBatchStatus) {
            this.taskBatchStatus = taskBatchStatus;
        }

        public DecisionConfigRequest getDecision() {
            return decision;
        }

        public void setDecision(DecisionConfigRequest decision) {
            this.decision = decision;
        }

        public CallbackConfig getCallback() {
            return callback;
        }

        public void setCallback(CallbackConfig callback) {
            this.callback = callback;
        }

        public JobTaskConfigRequest getJobTask() {
            return jobTask;
        }

        public void setJobTask(JobTaskConfigRequest jobTask) {
            this.jobTask = jobTask;
        }

        public List<JobBatchResponse> getJobBatchList() {
            return jobBatchList;
        }

        public void setJobBatchList(List<JobBatchResponse> jobBatchList) {
            this.jobBatchList = jobBatchList;
        }

        public NodeConfig getChildNode() {
            return childNode;
        }

        public void setChildNode(NodeConfig childNode) {
            this.childNode = childNode;
        }
    }

    /**
     * 通知告警场景配置id列表
     */
    private Set<Long> notifyIds;

    /**
     * 负责人id
     */
    private Long ownerId;

}
