package com.wingflare.lib.task;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import com.wingflare.facade.lib.task.ScheduleConstants;
import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.facade.lib.task.TaskLogBO;
import com.wingflare.facade.lib.task.TaskLogService;
import com.wingflare.lib.core.utils.BeanUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.utils.SpringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务抽象类
 */
public abstract class AbstractQuartzTask implements Job
{

    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzTask.class);

    /**
     * 线程本地变量
     */
    private static final ThreadLocal<Date> threadLocal = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context)
    {
        TaskBO taskBO = new TaskBO();

        try {
            BeanUtil.copyProperties(taskBO, context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES));

            before(context, taskBO);
            doExecute(context, taskBO);
            after(context, taskBO, null);
        } catch (Exception e) {
            log.error("定时任务执行异常：", e);
            after(context, taskBO, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param taskBO 系统计划任务
     */
    protected void before(JobExecutionContext context, TaskBO taskBO)
    {
        threadLocal.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param taskBO 系统计划任务
     */
    protected void after(JobExecutionContext context, TaskBO taskBO, Exception e)
    {
        Date startTime = threadLocal.get();
        threadLocal.remove();

        final TaskLogBO taskLogBO = new TaskLogBO();
        taskLogBO.setTaskName(taskBO.getTaskName());
        taskLogBO.setTaskGroup(taskBO.getTaskGroup());
        taskLogBO.setInvokeTarget(taskBO.getInvokeTarget());
        taskLogBO.setStartTime(startTime);
        taskLogBO.setStopTime(new Date());
        long runMs = taskLogBO.getStopTime().getTime() - taskLogBO.getStartTime().getTime();
        taskLogBO.setTaskMessage(taskLogBO.getTaskName() + " 总共耗时：" + runMs + "毫秒");

        if (e != null) {
            taskLogBO.setStatus("1");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String errorMsg = StringUtil.substring(sw.toString(), 0, 2000);
            taskLogBO.setExceptionInfo(errorMsg);
        } else {
            taskLogBO.setStatus("0");
        }

        // 写入任务日志
        SpringUtil.getBean(TaskLogService.class).addTaskLog(taskLogBO);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param taskBO 系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, TaskBO taskBO) throws Exception;
}