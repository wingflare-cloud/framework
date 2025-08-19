package com.wingflare.lib.task.utils;


import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.spring.utils.SpringUtil;
import com.wingflare.lib.task.QuartzDisallowConcurrentExecution;
import com.wingflare.lib.task.QuartzTaskExecution;
import com.wingflare.facade.lib.task.ScheduleConstants;
import com.wingflare.facade.lib.task.ScheduleStatus;
import com.wingflare.facade.lib.task.ScheduleStrategy;
import com.wingflare.lib.task.TaskException;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import java.math.BigInteger;

/**
 * 定时任务工具类
 *
 */
public class ScheduleUtil {
    /**
     * 得到quartz任务类
     *
     * @param taskBO 执行计划
     * @return 具体执行任务类
     */
    private static Class<? extends Job> getQuartzJobClass(TaskBO taskBO) {
        boolean isConcurrent = "0".equals(taskBO.getConcurrent());
        return isConcurrent ? QuartzTaskExecution.class : QuartzDisallowConcurrentExecution.class;
    }

    /**
     * 构建任务触发对象
     */
    public static TriggerKey getTriggerKey(BigInteger jobId, String jobGroup) {
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }

    /**
     * 构建任务键对象
     */
    public static JobKey getTaskKey(BigInteger jobId, String taskGroup) {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId, taskGroup);
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, TaskBO task) throws SchedulerException, TaskException {
        Class<? extends Job> jobClass = getQuartzJobClass(task);
        // 构建job信息
        BigInteger taskId = task.getTaskId();
        String taskGroup = task.getTaskGroup();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(getTaskKey(taskId, taskGroup)).build();

        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
        cronScheduleBuilder = handleCronScheduleMisfirePolicy(task, cronScheduleBuilder);

        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(taskId, taskGroup))
                .withSchedule(cronScheduleBuilder).build();

        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, task);

        // 判断是否存在
        if (scheduler.checkExists(getTaskKey(taskId, taskGroup))) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(getTaskKey(taskId, taskGroup));
        }

        // 判断任务是否过期
        if (CronUtil.getNextExecution(task.getCronExpression()) != null) {
            // 执行调度任务
            scheduler.scheduleJob(jobDetail, trigger);
        }

        // 暂停任务
        if (task.getStatus().equals(ScheduleStatus.PAUSE.getValue())) {
            scheduler.pauseJob(ScheduleUtil.getTaskKey(taskId, taskGroup));
        }
    }

    /**
     * 设置定时任务策略
     */
    public static CronScheduleBuilder handleCronScheduleMisfirePolicy(TaskBO job, CronScheduleBuilder cb)
            throws TaskException {
        return switch (job.getMisfirePolicy()) {
            case ScheduleStrategy.MISFIRE_DEFAULT -> cb;
            case ScheduleStrategy.MISFIRE_IGNORE_MISFIRES -> cb.withMisfireHandlingInstructionIgnoreMisfires();
            case ScheduleStrategy.MISFIRE_FIRE_AND_PROCEED -> cb.withMisfireHandlingInstructionFireAndProceed();
            case ScheduleStrategy.MISFIRE_DO_NOTHING -> cb.withMisfireHandlingInstructionDoNothing();
            default -> throw new TaskException("The task misfire policy '" + job.getMisfirePolicy()
                    + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
        };
    }

    /**
     * 检查包名是否为白名单配置
     *
     * @param invokeTarget 目标字符串
     * @return 结果
     */
    public static boolean whiteList(String invokeTarget) {
        String packageName = StringUtil.substringBefore(invokeTarget, "(");
        int count = StringUtil.countMatches(packageName, ".");

        if (count > 1) {
            return StringUtil.startsWithAny(invokeTarget, ScheduleConstants.JOB_WHITELIST_STR);
        }

        Object obj = SpringUtil.getBean(StringUtil.split(invokeTarget, ".")[0]);

        String beanPackageName = obj.getClass().getPackage().getName();

        return StringUtil.startsWithAny(beanPackageName, ScheduleConstants.JOB_WHITELIST_STR)
                && !StringUtil.startsWithAny(beanPackageName, ScheduleConstants.JOB_ERROR_STR);
    }
}
