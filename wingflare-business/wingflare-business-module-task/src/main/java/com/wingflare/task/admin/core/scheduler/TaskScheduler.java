package com.wingflare.task.admin.core.scheduler;

import com.wingflare.lib.task.biz.ExecutorBiz;
import com.wingflare.lib.task.biz.client.ExecutorBizClient;
import com.wingflare.lib.task.enums.ExecutorBlockStrategyEnum;
import com.wingflare.task.admin.core.conf.TaskAdminConfig;
import com.wingflare.task.admin.core.thread.JobCompleteHelper;
import com.wingflare.task.admin.core.thread.JobFailMonitorHelper;
import com.wingflare.task.admin.core.thread.TaskLogReportHelper;
import com.wingflare.task.admin.core.thread.JobRegistryHelper;
import com.wingflare.task.admin.core.thread.JobScheduleHelper;
import com.wingflare.task.admin.core.thread.JobTriggerPoolHelper;
import com.wingflare.task.admin.core.util.I18nUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author xuxueli 2018-10-28 00:18:17
 */

public class TaskScheduler {
    private static final Logger logger = LoggerFactory.getLogger(TaskScheduler.class);


    public void init() throws Exception {
        // init i18n
        initI18n();

        // admin trigger pool start
        JobTriggerPoolHelper.toStart();

        // admin registry monitor run
        JobRegistryHelper.getInstance().start();

        // admin fail-monitor run
        JobFailMonitorHelper.getInstance().start();

        // admin lose-monitor run ( depend on JobTriggerPoolHelper )
        JobCompleteHelper.getInstance().start();

        // admin log report start
        TaskLogReportHelper.getInstance().start();

        // start-schedule  ( depend on JobTriggerPoolHelper )
        JobScheduleHelper.getInstance().start();

        logger.info(">>>>>>>>> init xxl-job admin success.");
    }

    
    public void destroy() throws Exception {

        // stop-schedule
        JobScheduleHelper.getInstance().toStop();

        // admin log report stop
        TaskLogReportHelper.getInstance().toStop();

        // admin lose-monitor stop
        JobCompleteHelper.getInstance().toStop();

        // admin fail-monitor stop
        JobFailMonitorHelper.getInstance().toStop();

        // admin registry stop
        JobRegistryHelper.getInstance().toStop();

        // admin trigger pool stop
        JobTriggerPoolHelper.toStop();

    }

    // ---------------------- I18n ----------------------

    private void initI18n(){
        for (ExecutorBlockStrategyEnum item:ExecutorBlockStrategyEnum.values()) {
            item.setTitle(I18nUtil.getString("jobconf_block_".concat(item.name())));
        }
    }

    // ---------------------- executor-client ----------------------
    private static ConcurrentMap<String, ExecutorBiz> executorBizRepository = new ConcurrentHashMap<String, ExecutorBiz>();
    public static ExecutorBiz getExecutorBiz(String address) throws Exception {
        // valid
        if (address==null || address.trim().length()==0) {
            return null;
        }

        // load-cache
        address = address.trim();
        ExecutorBiz executorBiz = executorBizRepository.get(address);
        if (executorBiz != null) {
            return executorBiz;
        }

        // set-cache
        executorBiz = new ExecutorBizClient(address,
                TaskAdminConfig.getAdminConfig().getAccessToken(),
                TaskAdminConfig.getAdminConfig().getTimeout());

        executorBizRepository.put(address, executorBiz);
        return executorBiz;
    }

}
