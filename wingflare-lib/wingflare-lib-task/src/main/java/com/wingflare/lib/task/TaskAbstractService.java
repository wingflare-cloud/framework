package com.wingflare.lib.task;


import com.wingflare.facade.lib.task.ScheduleConstants;
import com.wingflare.facade.lib.task.ScheduleStatus;
import com.wingflare.facade.lib.task.TaskBO;
import com.wingflare.facade.lib.task.TaskService;
import com.wingflare.lib.task.utils.ScheduleUtil;
import jakarta.annotation.Resource;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * 任务抽象类
 */
public abstract class TaskAbstractService implements TaskService {

    @Resource
    protected Scheduler scheduler;


    @Override
    public void pauseTask(TaskBO task) throws SchedulerException {
        scheduler.pauseJob(ScheduleUtil.getTaskKey(task.getTaskId(), task.getTaskGroup()));
    }

    @Override
    public void resumeTask(TaskBO task) throws SchedulerException {
        scheduler.resumeJob(ScheduleUtil.getTaskKey(task.getTaskId(), task.getTaskGroup()));
    }

    @Override
    public void deleteTask(TaskBO task) throws SchedulerException {
        scheduler.deleteJob(ScheduleUtil.getTaskKey(task.getTaskId(), task.getTaskGroup()));
    }

    @Override
    public void runTask(TaskBO task) throws SchedulerException {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, task);
        JobKey jobKey = ScheduleUtil.getTaskKey(task.getTaskId(), task.getTaskGroup());

        if (scheduler.checkExists(jobKey)) {
            scheduler.triggerJob(jobKey, dataMap);
        }
    }

    @Override
    public void createTask(TaskBO task) throws Exception {
        task.setStatus(ScheduleStatus.PAUSE.getValue());
        ScheduleUtil.createScheduleJob(scheduler, task);
    }

    @Override
    public void updateTask(TaskBO task) throws Exception {
        JobKey jobKey = ScheduleUtil.getTaskKey(task.getTaskId(), task.getTaskGroup());

        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }

        ScheduleUtil.createScheduleJob(scheduler, task);
    }


}
