package com.wingflare.engine.task.server.job.support.generator.task;


import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-02 13:02:57
 * @since 2.4.0
 */
public class JobTaskGenerateContext {
    /**
     * 命名空间id
     */
    private String namespaceId;

    private Long taskBatchId;
    private String groupName;
    private Long jobId;
    private Integer routeKey;
    /**
     * 执行方法参数
     */
    private String argsStr;

    /**
     * 参数类型 text/json
     */
    private Integer argsType;

    /**
     * 动态分片的Map任务
     */
    private List<?> mapSubTask;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 动态分片的阶段
     */
    private Integer mrStage;

    /**
     * 父任务id
     */
    private Long parentId;


    private String wfContext;

    /**
     * 标签
     * json格式，如：{"key1":"value1","key2":"value2"}
     */
    private String labels;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public Long getTaskBatchId() {
        return taskBatchId;
    }

    public void setTaskBatchId(Long taskBatchId) {
        this.taskBatchId = taskBatchId;
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

    public Integer getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(Integer routeKey) {
        this.routeKey = routeKey;
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

    public List<?> getMapSubTask() {
        return mapSubTask;
    }

    public void setMapSubTask(List<?> mapSubTask) {
        this.mapSubTask = mapSubTask;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getWfContext() {
        return wfContext;
    }

    public void setWfContext(String wfContext) {
        this.wfContext = wfContext;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
