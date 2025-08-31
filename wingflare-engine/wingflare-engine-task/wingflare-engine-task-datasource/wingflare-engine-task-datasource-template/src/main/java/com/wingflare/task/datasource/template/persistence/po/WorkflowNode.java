package com.wingflare.task.datasource.template.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 工作流节点
 *
 * @author xiaowoniu
 * @since 2023-12-12
 */
@TableName("wf_task_workflow_node")
public class WorkflowNode extends CreateUpdateDt {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 命名空间id
     */
    private String namespaceId;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 任务信息id
     */
    private Long jobId;

    /**
     * 工作流ID
     */
    private Long workflowId;

    /**
     * 1、任务节点 2、条件节点 3、回调节点
     */
    private Integer nodeType;

    /**
     * 节点信息
     */
    private String nodeInfo;

    /**
     * 失败策略 1、跳过 2、阻塞
     */
    private Integer failStrategy;

    /**
     * 优先级
     */
    private Integer priorityLevel;

    /**
     * 工作流节点状态 0、关闭、1、开启
     */
    private Integer workflowNodeStatus;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 扩展字段
     */
    private String extAttrs;

    /**
     * 逻辑删除 1、删除
     */
    private Integer deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
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

    public Long getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(Long workflowId) {
        this.workflowId = workflowId;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(String nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public Integer getFailStrategy() {
        return failStrategy;
    }

    public void setFailStrategy(Integer failStrategy) {
        this.failStrategy = failStrategy;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getExtAttrs() {
        return extAttrs;
    }

    public void setExtAttrs(String extAttrs) {
        this.extAttrs = extAttrs;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
