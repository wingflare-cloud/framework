package com.wingflare.lib.task;

import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.lib.task.utils.TaskInvokeUtil;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 */
@DisallowConcurrentExecution
public class QuartzDisallowConcurrentExecution extends AbstractQuartzTask
{

    @Override
    protected void doExecute(JobExecutionContext context, TaskBO taskBO) throws Exception
    {
        TaskInvokeUtil.invokeMethod(taskBO);
    }

}