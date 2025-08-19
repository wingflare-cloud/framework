package com.wingflare.facade.lib.task;

import java.math.BigInteger;
import java.util.List;


public interface TaskLogService {

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param taskLog 调度日志信息
     * @return 调度任务日志集合
     */
    public List<TaskLogBO> selectJobLogList(TaskLogBO taskLog);

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param taskLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    public TaskLogBO selectJobLogById(BigInteger taskLogId);

    /**
     * 新增任务日志
     *
     * @param taskLog 调度日志信息
     */
    public void addJobLog(TaskLogBO taskLog);

    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的日志ID
     * @return 结果
     */
    public int deleteJobLogByIds(BigInteger[] logIds);

    /**
     * 删除任务日志
     *
     * @param taskId 调度日志ID
     * @return 结果
     */
    public int deleteJobLogById(BigInteger taskId);

    /**
     * 清空任务日志
     */
    public void cleanJobLog();

}
