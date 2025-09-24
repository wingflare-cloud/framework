package com.wingflare.engine.task.client.core.dto;


/**
 * Task执行结果
 *
 * @author: opensnail
 * @date : 2024-06-12 13:59
 */
public class MapArgs extends JobArgs {

    private String taskName;

    private Object mapResult;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Object getMapResult() {
        return mapResult;
    }

    public void setMapResult(Object mapResult) {
        this.mapResult = mapResult;
    }
}
