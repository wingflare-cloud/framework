package com.wingflare.task.datasource.template.persistence.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 任务统计
 *
 * @author zhengweilin
 * @version 2.6.0
 * @date 2023/11/22
 */
@TableName("wf_task_job_summary")
public class JobSummary extends CreateUpdateDt {

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * '任务信息id'
     */
    private Long businessId;

    /**
     * '统计时间'
     */
    private LocalDateTime triggerAt;

    /**
     * 执行成功-日志数量
     */
    private Integer successNum;

    /**
     * 执行失败-日志数量
     */
    private Integer failNum;

    /**
     * 失败原因
     */
    private String failReason;

    /**
     * 执行失败-日志数量
     */
    private Integer stopNum;

    /**
     * 失败原因
     */
    private String stopReason;

    /**
     * 执行失败-日志数量
     */
    private Integer cancelNum;

    /**
     * 失败原因
     */
    private String cancelReason;

    /**
     * 任务类型 3、JOB任务 4、WORKFLOW任务
     */
    private Integer systemTaskType;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public LocalDateTime getTriggerAt() {
        return triggerAt;
    }

    public void setTriggerAt(LocalDateTime triggerAt) {
        this.triggerAt = triggerAt;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Integer getStopNum() {
        return stopNum;
    }

    public void setStopNum(Integer stopNum) {
        this.stopNum = stopNum;
    }

    public String getStopReason() {
        return stopReason;
    }

    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public Integer getCancelNum() {
        return cancelNum;
    }

    public void setCancelNum(Integer cancelNum) {
        this.cancelNum = cancelNum;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public Integer getSystemTaskType() {
        return systemTaskType;
    }

    public void setSystemTaskType(Integer systemTaskType) {
        this.systemTaskType = systemTaskType;
    }
}
