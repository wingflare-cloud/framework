package com.wingflare.engine.task.client.core.dto;


import cn.hutool.core.util.StrUtil;

import java.util.Map;
import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2023-10-18 16:53
 * @since : 2.4.0
 */
public class JobArgs {

    private Object jobParams;

    private String executorInfo;

    private Long taskBatchId;

    private Long workflowTaskBatchId;

    private Long workflowNodeId;

    private Long jobId;

    private Map<String, Object> wfContext;

    private Map<String, Object> changeWfContext;

    public Object getJobParams() {
        return jobParams;
    }

    public void setJobParams(Object jobParams) {
        this.jobParams = jobParams;
    }

    public String getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(String executorInfo) {
        this.executorInfo = executorInfo;
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

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
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

    public void appendContext(String key, Object value) {
        if (Objects.isNull(wfContext) || StrUtil.isBlank(key) || Objects.isNull(value)) {
            return;
        }

        changeWfContext.put(key, value);
    }

    public Object getWfContext(String key) {
        if (Objects.isNull(wfContext) || StrUtil.isBlank(key)) {
            return null;
        }

        return wfContext.get(key);
    }

}
