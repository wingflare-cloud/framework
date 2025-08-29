package com.wingflare.engine.task.common.model.request;

import com.wingflare.engine.task.common.log.dto.TaskLogFieldDTO;

import java.io.Serializable;
import java.util.List;

/**
 * 日志上报DTO
 *
 * @author: wodeyangzipingpingwuqi
 * @date : 2023-12-26
 */
public class LogTaskRequest implements Serializable {

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 命名空间
     */
    private String namespaceId;

    /**
     * 组名称
     */
    private String groupName;

    /**
     * 任务信息id
     */
    @Deprecated
    private Long jobId;

    /**
     * 任务实例id
     */
    @Deprecated
    private Long taskBatchId;

    /**
     * 调度任务id
     */
    @Deprecated
    private Long taskId;

//    /**
//     * 创建时间
//     */
//    private LocalDateTime createDt;
//
//    /**
//     * 调度信息
//     */
//    private String message;

    /**
     * 上报时间
     */
    private Long realTime;

    /**
     * 日志模型集合
     */
    private List<TaskLogFieldDTO> fieldList;


    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
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

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getRealTime() {
        return realTime;
    }

    public void setRealTime(Long realTime) {
        this.realTime = realTime;
    }

    public List<TaskLogFieldDTO> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<TaskLogFieldDTO> fieldList) {
        this.fieldList = fieldList;
    }
}
