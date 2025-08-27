package com.wingflare.task.admin.core.alarm;


import com.wingflare.task.admin.core.model.TaskInfo;
import com.wingflare.task.admin.core.model.TaskLog;

/**
 * @author xuxueli 2020-01-19
 */
public interface TaskAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param taskLog
     * @return
     */
    public boolean doAlarm(TaskInfo info, TaskLog taskLog);

}
