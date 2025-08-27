package com.wingflare.task.admin.core.thread;


import com.wingflare.task.admin.core.conf.TaskAdminConfig;
import com.wingflare.task.admin.core.model.TaskInfo;
import com.wingflare.task.admin.core.model.TaskLog;
import com.wingflare.task.admin.core.trigger.TriggerTypeEnum;
import com.wingflare.task.admin.core.util.I18nUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * job monitor instance
 *
 * @author xuxueli 2015-9-1 18:05:56
 */
public class JobFailMonitorHelper {
	private static final Logger logger = LoggerFactory.getLogger(JobFailMonitorHelper.class);
	
	private static final JobFailMonitorHelper instance = new JobFailMonitorHelper();
	public static JobFailMonitorHelper getInstance(){
		return instance;
	}

	// ---------------------- monitor ----------------------

	private Thread monitorThread;
	private volatile boolean toStop = false;
	public void start(){
		monitorThread = new Thread(new Runnable() {

			@Override
			public void run() {

				// monitor
				while (!toStop) {
					try {

						List<Long> failLogIds = TaskAdminConfig.getAdminConfig().getTaskLogDao().findFailTaskLogIds(1000);
						if (failLogIds!=null && !failLogIds.isEmpty()) {
							for (long failLogId: failLogIds) {

								// lock log
								int lockRet = TaskAdminConfig.getAdminConfig().getTaskLogDao().updateAlarmStatus(failLogId, 0, -1);
								if (lockRet < 1) {
									continue;
								}
								TaskLog log = TaskAdminConfig.getAdminConfig().getTaskLogDao().load(failLogId);
								TaskInfo info = TaskAdminConfig.getAdminConfig().getXxlJobInfoDao().loadById(log.gettaskId());

								// 1、fail retry monitor
								if (log.getExecutorFailRetryCount() > 0) {
									JobTriggerPoolHelper.trigger(log.gettaskId(), TriggerTypeEnum.RETRY, (log.getExecutorFailRetryCount()-1), log.getExecutorShardingParam(), log.getExecutorParam(), null);
									String retryMsg = "<br><br><span style=\"color:#F39C12;\" > >>>>>>>>>>>"+ I18nUtil.getString("jobconf_trigger_type_retry") +"<<<<<<<<<<< </span><br>";
									log.setTriggerMsg(log.getTriggerMsg() + retryMsg);
									TaskAdminConfig.getAdminConfig().getTaskLogDao().updateTriggerInfo(log);
								}

								// 2、fail alarm monitor
								int newAlarmStatus = 0;		// 告警状态：0-默认、-1=锁定状态、1-无需告警、2-告警成功、3-告警失败
								if (info != null) {
									boolean alarmResult = TaskAdminConfig.getAdminConfig().getJobAlarmer().alarm(info, log);
									newAlarmStatus = alarmResult?2:3;
								} else {
									newAlarmStatus = 1;
								}

								TaskAdminConfig.getAdminConfig().getTaskLogDao().updateAlarmStatus(failLogId, -1, newAlarmStatus);
							}
						}

					} catch (Throwable e) {
						if (!toStop) {
							logger.error(">>>>>>>>>>> xxl-job, job fail monitor thread error:{}", e);
						}
					}

                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (Throwable e) {
                        if (!toStop) {
                            logger.error(e.getMessage(), e);
                        }
                    }

                }

				logger.info(">>>>>>>>>>> xxl-job, job fail monitor thread stop");

			}
		});
		monitorThread.setDaemon(true);
		monitorThread.setName("xxl-job, admin JobFailMonitorHelper");
		monitorThread.start();
	}

	public void toStop(){
		toStop = true;
		// interrupt and wait
		monitorThread.interrupt();
		try {
			monitorThread.join();
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

}
