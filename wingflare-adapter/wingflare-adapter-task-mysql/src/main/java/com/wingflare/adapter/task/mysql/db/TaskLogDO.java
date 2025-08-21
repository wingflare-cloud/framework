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
 * 定时任务日志实体
 */
@TableName("sys_task_log")
public class TaskLogDO extends BaseDoAbstract {

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private BigInteger taskLogId;

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
     * 日志信息
     */
    private String taskMessage;

    /**
     * 执行状态（0正常 1失败）
     */
    private String status;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 停止时间
     */
    private Date stopTime;


    @Override
    public void setPk(BigInteger taskLogId) {
        setTaskLogId(taskLogId);
    }

    @Override
    public BigInteger getPk() {
        return getTaskLogId();
    }

    public TaskLogDO setTaskLogId(BigInteger taskLogId) {
        addNewField("taskLogId");
        this.taskLogId = taskLogId;
        return this;
    }

    public BigInteger getTaskLogId() {
        return taskLogId;
    }

    public TaskLogDO setTaskName(String taskName) {
        addNewField("taskName");
        this.taskName = taskName;
        return this;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskLogDO setTaskGroup(String taskGroup) {
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

    public TaskLogDO setActuator(String actuator) {
        addNewField("actuator");
        this.actuator = actuator;
        return this;
    }

    public TaskLogDO setInvokeTarget(String invokeTarget) {
        addNewField("invokeTarget");
        this.invokeTarget = invokeTarget;
        return this;
    }

    public String getInvokeTarget() {
        return invokeTarget;
    }

    public TaskLogDO setTaskMessage(String taskMessage) {
        addNewField("taskMessage");
        this.taskMessage = taskMessage;
        return this;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public TaskLogDO setStatus(String status) {
        addNewField("status");
        this.status = status;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public TaskLogDO setExceptionInfo(String exceptionInfo) {
        addNewField("exceptionInfo");
        this.exceptionInfo = exceptionInfo;
        return this;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public TaskLogDO setStartTime(Date startTime) {
        addNewField("startTime");
        this.startTime = startTime;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public TaskLogDO setStopTime(Date stopTime) {
        addNewField("stopTime");
        this.stopTime = stopTime;
        return this;
    }

    public Date getStopTime() {
        return stopTime;
    }


    @Override
    public void clearNullNewField() {
        if (getTaskLogId() == null) {
            removeNewField("taskLogId");
        }

        if (getTaskName() == null) {
            removeNewField("taskName");
        }

        if (getActuator() == null) {
            removeNewField("actuator");
        }

        if (getInvokeTarget() == null) {
            removeNewField("invokeTarget");
        }

        if (getTaskMessage() == null) {
            removeNewField("taskMessage");
        }

        if (getStatus() == null) {
            removeNewField("status");
        }

        if (getExceptionInfo() == null) {
            removeNewField("exceptionInfo");
        }

        if (getStartTime() == null) {
            removeNewField("startTime");
        }

        if (getStopTime() == null) {
            removeNewField("stopTime");
        }
    }

    public TaskLogDO setOnNew(TaskLogDO newDo) {
        TaskLogDO oldFieldObj = new TaskLogDO();

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

        if (newDo.getTaskMessage() != null && !newDo.getTaskMessage().equals(getTaskMessage())) {
            oldFieldObj.setTaskMessage(getTaskMessage());
            setTaskMessage(newDo.getTaskMessage());
        }

        if (newDo.getStatus() != null && !newDo.getStatus().equals(getStatus())) {
            oldFieldObj.setStatus(getStatus());
            setStatus(newDo.getStatus());
        }

        if (newDo.getExceptionInfo() != null && !newDo.getExceptionInfo().equals(getExceptionInfo())) {
            oldFieldObj.setExceptionInfo(getExceptionInfo());
            setExceptionInfo(newDo.getExceptionInfo());
        }

        if (newDo.getStartTime() != null && !newDo.getStartTime().equals(getStartTime())) {
            oldFieldObj.setStartTime(getStartTime());
            setStartTime(newDo.getStartTime());
        }

        if (newDo.getStopTime() != null && !newDo.getStopTime().equals(getStopTime())) {
            oldFieldObj.setStopTime(getStopTime());
            setStopTime(newDo.getStopTime());
        }

        if (!oldFieldObj.hasNewField()) {
            return null;
        }

        return oldFieldObj;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("taskLogId", getTaskLogId())
                .append("taskName", getTaskName())
                .append("taskGroup", getTaskGroup())
                .append("invokeTarget", getInvokeTarget())
                .append("taskMessage", getTaskMessage())
                .append("status", getStatus())
                .append("exceptionInfo", getExceptionInfo())
                .append("startTime", getStartTime())
                .append("stopTime", getStopTime())
                .toString();
    }
}