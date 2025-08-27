package com.wingflare.task.admin.core.alarm;


import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.task.admin.core.model.TaskInfo;
import com.wingflare.task.admin.core.model.TaskLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class TaskAlarmer implements ApplicationContextAware, InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(TaskAlarmer.class);

    private ApplicationContext applicationContext;
    private List<TaskAlarm> taskAlarmList;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, TaskAlarm> serviceBeanMap = applicationContext.getBeansOfType(TaskAlarm.class);
        if (CollectionUtil.isNotEmpty(serviceBeanMap)) {
            taskAlarmList = new ArrayList<TaskAlarm>(serviceBeanMap.values());
        }
    }

    /**
     * job alarm
     *
     * @param info
     * @param taskLog
     * @return
     */
    public boolean alarm(TaskInfo info, TaskLog taskLog) {

        boolean result = false;
        if (CollectionUtil.isNotEmpty(taskAlarmList)) {
            result = true;  // success means all-success
            for (TaskAlarm alarm: taskAlarmList) {
                boolean resultItem = false;
                try {
                    resultItem = alarm.doAlarm(info, taskLog);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
                if (!resultItem) {
                    result = false;
                }
            }
        }

        return result;
    }

}
