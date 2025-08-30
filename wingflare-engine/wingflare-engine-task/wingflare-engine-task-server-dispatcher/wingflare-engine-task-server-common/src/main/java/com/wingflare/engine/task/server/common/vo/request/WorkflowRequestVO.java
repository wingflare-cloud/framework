package com.wingflare.engine.task.server.common.vo.request;

import com.wingflare.engine.task.common.model.request.CallbackConfig;
import com.wingflare.engine.task.common.model.request.DecisionConfigRequest;
import com.wingflare.engine.task.common.model.request.JobTaskConfigRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Set;

/**
 * @author xiaowoniu
 * @date 2023-12-12 21:53:17
 * @since 2.6.0
 */
@Deprecated
public class WorkflowRequestVO {

    private Long id;

    @NotBlank(message = "Group name cannot be null")
    @Pattern(regexp = "^[A-Za-z0-9_-]{1,64}$", message = "Only supports 1~64 characters, including numbers, letters, underscores, and hyphens")
    private String groupName;

    @NotBlank(message = "Workflow name cannot be empty")
    private String workflowName;

    @NotNull(message = "Trigger type cannot be empty")
    private Integer triggerType;

    @NotBlank(message = "Trigger interval cannot be empty")
    private String triggerInterval;

    @NotNull(message = "Execution timeout cannot be empty")
    private Integer executorTimeout;

    @NotNull(message = "Blocking strategy cannot be null")
    private Integer blockStrategy;

    /**
     * 工作流上下文
     */
    private String wfContext;

    /**
     * 0、关闭、1、开启
     */
    @NotNull(message = "Workflow status")
    private Integer workflowStatus;

    /**
     * 描述
     */
    private String description;

    /**
     * DAG节点配置
     */
    @NotNull(message = "DAG node configuration cannot be empty")
    private NodeConfig nodeConfig;

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

    public String getWorkflowName() {
        return workflowName;
    }

    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
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

    public Integer getExecutorTimeout() {
        return executorTimeout;
    }

    public void setExecutorTimeout(Integer executorTimeout) {
        this.executorTimeout = executorTimeout;
    }

    public Integer getBlockStrategy() {
        return blockStrategy;
    }

    public void setBlockStrategy(Integer blockStrategy) {
        this.blockStrategy = blockStrategy;
    }

    public String getWfContext() {
        return wfContext;
    }

    public void setWfContext(String wfContext) {
        this.wfContext = wfContext;
    }

    public Integer getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(Integer workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
         * 1、任务节点 2、条件节点
         */
        @NotNull(message = " Node type cannot be empty")
        private Integer nodeType;

        /**
         * 节点信息
         */
        @NotEmpty(message = "Node information cannot be empty")
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
         * 节点名称
         */
        @NotBlank(message = "Node name cannot be empty")
        private String nodeName;

        /**
         * 工作流状态  0、关闭、1、开启
         */
        @NotNull(message = "Workflow status cannot be empty")
        private Integer workflowNodeStatus;

        /**
         * 优先级
         */
        @NotNull(message = "Priority cannot be empty")
        private Integer priorityLevel;

        /**
         * 子节点
         */
        private NodeConfig childNode;

        /**
         * 1、跳过 2、阻塞
         */
        private Integer failStrategy;

        /**
         * 任务节点配置
         */
        private JobTaskConfigRequest jobTask;

        /**
         * 决策节点配置
         */
        private DecisionConfigRequest decision;

        /**
         * 回调配置
         */
        private CallbackConfig callback;

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public Integer getWorkflowNodeStatus() {
            return workflowNodeStatus;
        }

        public void setWorkflowNodeStatus(Integer workflowNodeStatus) {
            this.workflowNodeStatus = workflowNodeStatus;
        }

        public Integer getPriorityLevel() {
            return priorityLevel;
        }

        public void setPriorityLevel(Integer priorityLevel) {
            this.priorityLevel = priorityLevel;
        }

        public NodeConfig getChildNode() {
            return childNode;
        }

        public void setChildNode(NodeConfig childNode) {
            this.childNode = childNode;
        }

        public Integer getFailStrategy() {
            return failStrategy;
        }

        public void setFailStrategy(Integer failStrategy) {
            this.failStrategy = failStrategy;
        }

        public JobTaskConfigRequest getJobTask() {
            return jobTask;
        }

        public void setJobTask(JobTaskConfigRequest jobTask) {
            this.jobTask = jobTask;
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
