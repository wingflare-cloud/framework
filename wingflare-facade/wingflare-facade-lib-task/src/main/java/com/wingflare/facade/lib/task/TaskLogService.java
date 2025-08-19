package com.wingflare.facade.lib.task;

/**
 * 任务日志服务
 */
public interface TaskLogService {

    /**
     * 新增任务日志
     *
     * @param taskLog 调度日志信息
     */
    void addTaskLog(TaskLogBO taskLog);

}
