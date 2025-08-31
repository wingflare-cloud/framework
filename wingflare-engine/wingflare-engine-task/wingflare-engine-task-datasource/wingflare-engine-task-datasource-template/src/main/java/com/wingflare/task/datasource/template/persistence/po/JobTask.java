package com.wingflare.task.datasource.template.persistence.po;

import com.wingflare.engine.task.common.core.enums.JobArgsTypeEnum;
import com.wingflare.engine.task.common.core.enums.JobTaskStatusEnum;
import com.wingflare.engine.task.common.core.enums.MapReduceStageEnum;
import com.wingflare.engine.task.common.core.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 任务实例
 *
 * @author opensnail
 * @since 2023-09-24
 */
@TableName("wf_task_job_task")
public class JobTask extends CreateUpdateDt {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务信息id
     */
    private Long jobId;

    /**
     * 调度任务id
     */
    private Long taskBatchId;

    /**
     * 父执行器id
     */
    private Long parentId;

    /**
     * 执行状态
     * {@link JobTaskStatusEnum}
     */
    private Integer taskStatus;

    /**
     * 重试次数
     */
    @TableField(value = "retry_count", update = "%s+1")
    private Integer retryCount;

    /**
     * 执行结果
     */
    private String resultMessage;

    /**
     * 客户端ID
     */
    private String clientInfo;

    /**
     * 执行方法参数
     */
    private String argsStr;

    /**
     * 参数类型 text、json
     * {@link JobArgsTypeEnum}
     */
    private Integer argsType;

    /**
     * 叶子节点(0:非叶子节点 1:叶子节点)
     * {@link StatusEnum}
     */
    private Integer leaf;

    /**
     * 动态分片使用
     * 1:map 2:reduce 3:mergeReduce
     * {@link MapReduceStageEnum}
     */
    private Integer mrStage;

    /**
     * 冗余工作流上下文
     * 注: 采用空间换时间的方式冗余部分上下文，减少更新并发
     */
    private String wfContext;

    /**
     * 扩展字段
     */
    private String extAttrs;

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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
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

    public Integer getLeaf() {
        return leaf;
    }

    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }

    public Integer getMrStage() {
        return mrStage;
    }

    public void setMrStage(Integer mrStage) {
        this.mrStage = mrStage;
    }

    public String getWfContext() {
        return wfContext;
    }

    public void setWfContext(String wfContext) {
        this.wfContext = wfContext;
    }

    public String getExtAttrs() {
        return extAttrs;
    }

    public void setExtAttrs(String extAttrs) {
        this.extAttrs = extAttrs;
    }
}
