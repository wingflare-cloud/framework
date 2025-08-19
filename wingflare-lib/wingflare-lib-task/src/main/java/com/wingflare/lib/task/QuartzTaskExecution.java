package com.wingflare.lib.task;


import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.lib.task.utils.TaskInvokeUtil;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（允许并发执行）
 */
public class QuartzTaskExecution extends AbstractQuartzTask
{
    @Override
    protected void doExecute(JobExecutionContext context, TaskBO taskBO) throws Exception
    {
        TaskInvokeUtil.invokeMethod(taskBO);
    }
}