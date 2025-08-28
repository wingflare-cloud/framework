package com.wingflare.lib.task.model;


/**
 * 定时任务 sj_job_task的args_str对应的
 * 参数模型
 *
 * @author: opensnail
 * @date : 2024-06-19
 * @since : sj_1.1.0
 */
public class JobArgsHolder {

    /**
     * sj_job表输入的参数
     */
    private Object jobParams;

    /**
     * 动态分片 map节点的结果
     */
    private Object maps;

    /**
     * 动态分片 reduce执行的结果
     */
    private Object reduces;

    public Object getJobParams() {
        return jobParams;
    }

    public void setJobParams(Object jobParams) {
        this.jobParams = jobParams;
    }

    public Object getMaps() {
        return maps;
    }

    public void setMaps(Object maps) {
        this.maps = maps;
    }

    public Object getReduces() {
        return reduces;
    }

    public void setReduces(Object reduces) {
        this.reduces = reduces;
    }
}
