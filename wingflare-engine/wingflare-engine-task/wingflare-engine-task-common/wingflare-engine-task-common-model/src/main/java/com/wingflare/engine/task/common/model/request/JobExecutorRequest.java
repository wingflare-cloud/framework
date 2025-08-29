package com.wingflare.engine.task.common.model.request;


/**
 * @author zhouxuangang
 * @date 2025/6/3 23:11
 */
public class JobExecutorRequest {

    /**
     * 定时任务执行器名称
     */
    private String executorInfo;


    public String getExecutorInfo() {
        return executorInfo;
    }

    public void setExecutorInfo(String executorInfo) {
        this.executorInfo = executorInfo;
    }
}
