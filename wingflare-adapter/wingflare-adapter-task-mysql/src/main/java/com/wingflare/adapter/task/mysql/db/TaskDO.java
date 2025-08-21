package com.wingflare.adapter.task.mysql.db;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 定时任务实体
 */
@TableName("sys_task")
public class TaskDO extends BaseDoAbstract {

    /**
     * 任务ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private BigInteger taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务组名
     */
    private String taskGroup;

    /**
     * 执行器
     */
    private String actuator;

    /**
     * 调用目标字符串
     */
    private String invokeTarget;

    /**
     * cron执行表达式
     */
    private String cronExpression;

    /**
     * cron计划策略
     */
    private String misfirePolicy;

    /**
     * 是否并发执行（0允许 1禁止）
     */
    private String concurrent;

    /**
     * 任务状态（0正常 1暂停）
     */
    private String status;

    /**
     * 任务备注
     */
    private String remark;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT)
    private BigInteger createUserId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private BigInteger updateUserId;

    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDelete;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;


    @Override
    public void setPk(BigInteger taskId)
    {
        setTaskId(taskId);
    }

    @Override
    public BigInteger getPk()
    {
        return getTaskId();
    }

    public TaskDO setTaskId(BigInteger taskId)
    {
        addNewField("taskId");
        this.taskId = taskId;
        return this;
    }

    public BigInteger getTaskId() {
        return taskId;
    }

    public TaskDO setTaskName(String taskName)
    {
        addNewField("taskName");
        this.taskName = taskName;
        return this;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskDO setTaskGroup(String taskGroup)
    {
        addNewField("taskGroup");
        this.taskGroup = taskGroup;
        return this;
    }

    public String getTaskGroup() {
        return taskGroup;
    }

    public String getActuator() {
        return actuator;
    }

    public TaskDO setActuator(String actuator) {
        addNewField("actuator");
        this.actuator = actuator;
        return this;
    }

    public TaskDO setInvokeTarget(String invokeTarget)
    {
        addNewField("invokeTarget");
        this.invokeTarget = invokeTarget;
        return this;
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public TaskDO setCronExpression(String cronExpression)
    {
        addNewField("cronExpression");
        this.cronExpression = cronExpression;
        return this;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public TaskDO setMisfirePolicy(String misfirePolicy)
    {
        addNewField("misfirePolicy");
        this.misfirePolicy = misfirePolicy;
        return this;
    }

    public String getMisfirePolicy() {
        return misfirePolicy;
    }

    public TaskDO setConcurrent(String concurrent)
    {
        addNewField("concurrent");
        this.concurrent = concurrent;
        return this;
    }

    public String getConcurrent() {
        return concurrent;
    }

    public TaskDO setStatus(String status)
    {
        addNewField("status");
        this.status = status;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskDO setRemark(String remark)
    {
        addNewField("remark");
        this.remark = remark;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public TaskDO setCreatedTime(Date createdTime)
    {
        addNewField("createdTime");
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public TaskDO setUpdatedTime(Date updatedTime)
    {
        addNewField("updatedTime");
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public TaskDO setCreateUser(String createUser)
    {
        addNewField("createUser");
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public TaskDO setCreateUserId(BigInteger createUserId)
    {
        addNewField("createUserId");
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId() {
        return createUserId;
    }

    public TaskDO setUpdateUser(String updateUser)
    {
        addNewField("updateUser");
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public TaskDO setUpdateUserId(BigInteger updateUserId)
    {
        addNewField("updateUserId");
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId() {
        return updateUserId;
    }

    public TaskDO setIsDelete(Integer isDelete)
    {
        addNewField("isDelete");
        this.isDelete = isDelete;
        return this;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public TaskDO setVersion(Integer version)
    {
        addNewField("version");
        this.version = version;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public void clearNullNewField()
    {
        if (getTaskId() == null) {
            removeNewField("taskId");
        }

        if (getTaskName() == null) {
            removeNewField("taskName");
        }

        if (getTaskGroup() == null) {
            removeNewField("taskGroup");
        }

        if (getActuator() == null) {
            removeNewField("actuator");
        }

        if (getInvokeTarget() == null) {
            removeNewField("invokeTarget");
        }

        if (getCronExpression() == null) {
            removeNewField("cronExpression");
        }

        if (getMisfirePolicy() == null) {
            removeNewField("misfirePolicy");
        }

        if (getConcurrent() == null) {
            removeNewField("concurrent");
        }

        if (getStatus() == null) {
            removeNewField("status");
        }

        if (getRemark() == null) {
            removeNewField("remark");
        }

        if (getCreatedTime() == null) {
            removeNewField("createdTime");
        }

        if (getUpdatedTime() == null) {
            removeNewField("updatedTime");
        }

        if (getCreateUser() == null) {
            removeNewField("createUser");
        }

        if (getCreateUserId() == null) {
            removeNewField("createUserId");
        }

        if (getUpdateUser() == null) {
            removeNewField("updateUser");
        }

        if (getUpdateUserId() == null) {
            removeNewField("updateUserId");
        }

        if (getIsDelete() == null) {
            removeNewField("isDelete");
        }

        if (getVersion() == null) {
            removeNewField("version");
        }
    }

    public TaskDO setOnNew(TaskDO newDo)
    {
        TaskDO oldFieldObj = new TaskDO();

        if (newDo.getTaskName() != null && !newDo.getTaskName().equals(getTaskName())) {
            oldFieldObj.setTaskName(getTaskName());
            setTaskName(newDo.getTaskName());
        }

        if (newDo.getTaskGroup() != null && !newDo.getTaskGroup().equals(getTaskGroup())) {
            oldFieldObj.setTaskGroup(getTaskGroup());
            setTaskGroup(newDo.getTaskGroup());
        }

        if (newDo.getActuator() != null && !newDo.getActuator().equals(getActuator())) {
            oldFieldObj.setActuator(getActuator());
            setActuator(newDo.getActuator());
        }

        if (newDo.getInvokeTarget() != null && !newDo.getInvokeTarget().equals(getInvokeTarget())) {
            oldFieldObj.setInvokeTarget(getInvokeTarget());
            setInvokeTarget(newDo.getInvokeTarget());
        }

        if (newDo.getCronExpression() != null && !newDo.getCronExpression().equals(getCronExpression())) {
            oldFieldObj.setCronExpression(getCronExpression());
            setCronExpression(newDo.getCronExpression());
        }

        if (newDo.getMisfirePolicy() != null && !newDo.getMisfirePolicy().equals(getMisfirePolicy())) {
            oldFieldObj.setMisfirePolicy(getMisfirePolicy());
            setMisfirePolicy(newDo.getMisfirePolicy());
        }

        if (newDo.getConcurrent() != null && !newDo.getConcurrent().equals(getConcurrent())) {
            oldFieldObj.setConcurrent(getConcurrent());
            setConcurrent(newDo.getConcurrent());
        }

        if (newDo.getStatus() != null && !newDo.getStatus().equals(getStatus())) {
            oldFieldObj.setStatus(getStatus());
            setStatus(newDo.getStatus());
        }

        if (newDo.getRemark() != null && !newDo.getRemark().equals(getRemark())) {
            oldFieldObj.setRemark(getRemark());
            setRemark(newDo.getRemark());
        }

        if (newDo.getVersion() != null && !newDo.getVersion().equals(getVersion())) {
            oldFieldObj.setVersion(getVersion());
            setVersion(newDo.getVersion());
        }

        if (!oldFieldObj.hasNewField()) {
            return null;
        }

        return oldFieldObj;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("taskId", getTaskId())
                .append("taskName", getTaskName())
                .append("taskGroup", getTaskGroup())
                .append("actuator", getActuator())
                .append("invokeTarget", getInvokeTarget())
                .append("cronExpression", getCronExpression())
                .append("misfirePolicy", getMisfirePolicy())
                .append("concurrent", getConcurrent())
                .append("status", getStatus())
                .append("remark", getRemark())
                .append("createdTime", getCreatedTime())
                .append("updatedTime", getUpdatedTime())
                .append("createUser", getCreateUser())
                .append("createUserId", getCreateUserId())
                .append("updateUser", getUpdateUser())
                .append("updateUserId", getUpdateUserId())
                .append("isDelete", getIsDelete())
                .append("version", getVersion())
                .toString();
    }

}
