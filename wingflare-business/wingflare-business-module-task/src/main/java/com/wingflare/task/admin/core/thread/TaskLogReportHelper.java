package com.wingflare.task.admin.core.thread;


import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.task.admin.core.conf.TaskAdminConfig;
import com.wingflare.task.admin.core.model.TaskLogReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * job log report helper
 *
 * @author xuxueli 2019-11-22
 */
public class TaskLogReportHelper {
    private static final Logger logger = LoggerFactory.getLogger(TaskLogReportHelper.class);

    private static final TaskLogReportHelper instance = new TaskLogReportHelper();
    public static TaskLogReportHelper getInstance(){
        return instance;
    }


    private Thread logrThread;
    private volatile boolean toStop = false;
    public void start(){
        logrThread = new Thread(new Runnable() {

            @Override
            public void run() {

                // last clean log time
                long lastCleanLogTime = 0;


                while (!toStop) {

                    // 1、log-report refresh: refresh log report in 3 days
                    try {

                        for (int i = 0; i < 3; i++) {

                            // today
                            Calendar itemDay = Calendar.getInstance();
                            itemDay.add(Calendar.DAY_OF_MONTH, -i);
                            itemDay.set(Calendar.HOUR_OF_DAY, 0);
                            itemDay.set(Calendar.MINUTE, 0);
                            itemDay.set(Calendar.SECOND, 0);
                            itemDay.set(Calendar.MILLISECOND, 0);

                            Date todayFrom = itemDay.getTime();

                            itemDay.set(Calendar.HOUR_OF_DAY, 23);
                            itemDay.set(Calendar.MINUTE, 59);
                            itemDay.set(Calendar.SECOND, 59);
                            itemDay.set(Calendar.MILLISECOND, 999);

                            Date todayTo = itemDay.getTime();

                            // refresh log-report every minute
                            TaskLogReport taskLogReport = new TaskLogReport();
                            taskLogReport.setTriggerDay(todayFrom);
                            taskLogReport.setRunningCount(0);
                            taskLogReport.setSucCount(0);
                            taskLogReport.setFailCount(0);

                            Map<String, Object> triggerCountMap = TaskAdminConfig.getAdminConfig().getTaskLogDao().findLogReport(todayFrom, todayTo);
                            if (CollectionUtil.isNotEmpty(triggerCountMap)) {
                                int triggerDayCount = triggerCountMap.containsKey("triggerDayCount")?Integer.valueOf(String.valueOf(triggerCountMap.get("triggerDayCount"))):0;
                                int triggerDayCountRunning = triggerCountMap.containsKey("triggerDayCountRunning")?Integer.valueOf(String.valueOf(triggerCountMap.get("triggerDayCountRunning"))):0;
                                int triggerDayCountSuc = triggerCountMap.containsKey("triggerDayCountSuc")?Integer.valueOf(String.valueOf(triggerCountMap.get("triggerDayCountSuc"))):0;
                                int triggerDayCountFail = triggerDayCount - triggerDayCountRunning - triggerDayCountSuc;

                                taskLogReport.setRunningCount(triggerDayCountRunning);
                                taskLogReport.setSucCount(triggerDayCountSuc);
                                taskLogReport.setFailCount(triggerDayCountFail);
                            }

                            // do refresh
                            int ret = TaskAdminConfig.getAdminConfig().getTaskLogReportDao().update(taskLogReport);
                            if (ret < 1) {
                                TaskAdminConfig.getAdminConfig().getTaskLogReportDao().save(taskLogReport);
                            }
                        }

                    } catch (Throwable e) {
                        if (!toStop) {
                            logger.error(">>>>>>>>>>> xxl-job, job log report thread error:{}", e);
                        }
                    }

                    // 2、log-clean: switch open & once each day
                    if (TaskAdminConfig.getAdminConfig().getLogretentiondays()>0
                            && System.currentTimeMillis() - lastCleanLogTime > 24*60*60*1000) {

                        // expire-time
                        Calendar expiredDay = Calendar.getInstance();
                        expiredDay.add(Calendar.DAY_OF_MONTH, -1 * TaskAdminConfig.getAdminConfig().getLogretentiondays());
                        expiredDay.set(Calendar.HOUR_OF_DAY, 0);
                        expiredDay.set(Calendar.MINUTE, 0);
                        expiredDay.set(Calendar.SECOND, 0);
                        expiredDay.set(Calendar.MILLISECOND, 0);
                        Date clearBeforeTime = expiredDay.getTime();

                        // clean expired log
                        List<Long> logIds = null;
                        do {
                            logIds = TaskAdminConfig.getAdminConfig().getTaskLogDao().findClearTaskIds(0, 0, clearBeforeTime, 0, 1000);
                            if (CollectionUtil.isNotEmpty(logIds)) {
                                TaskAdminConfig.getAdminConfig().getTaskLogDao().clearLog(logIds);
                            }
                        } while (CollectionUtil.isNotEmpty(logIds));

                        // update clean time
                        lastCleanLogTime = System.currentTimeMillis();
                    }

                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (Throwable e) {
                        if (!toStop) {
                            logger.error(e.getMessage(), e);
                        }
                    }

                }

                logger.info(">>>>>>>>>>> xxl-job, job log report thread stop");

            }
        });
        logrThread.setDaemon(true);
        logrThread.setName("xxl-job, admin TaskLogReportHelper");
        logrThread.start();
    }

    public void toStop(){
        toStop = true;
        // interrupt and wait
        logrThread.interrupt();
        try {
            logrThread.join();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }

}
