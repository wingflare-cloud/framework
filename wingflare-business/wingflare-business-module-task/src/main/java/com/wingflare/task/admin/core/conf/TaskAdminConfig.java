package com.wingflare.task.admin.core.conf;


import com.wingflare.task.admin.core.alarm.TaskAlarmer;
import com.wingflare.task.admin.core.scheduler.TaskScheduler;
import com.wingflare.task.admin.dao.TaskGroupDao;
import com.wingflare.task.admin.dao.TaskInfoDao;
import com.wingflare.task.admin.dao.TaskLogDao;
import com.wingflare.task.admin.dao.TaskLogReportDao;
import com.wingflare.task.admin.dao.TaskRegistryDao;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * xxl-job config
 *
 * @author xuxueli 2017-04-28
 */

@Component
public class TaskAdminConfig implements InitializingBean, DisposableBean {

    private static TaskAdminConfig adminConfig = null;
    public static TaskAdminConfig getAdminConfig() {
        return adminConfig;
    }


    // ---------------------- XxlJobScheduler ----------------------

    private TaskScheduler taskScheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        adminConfig = this;

        taskScheduler = new TaskScheduler();
        taskScheduler.init();
    }

    @Override
    public void destroy() throws Exception {
        taskScheduler.destroy();
    }


    // ---------------------- XxlJobScheduler ----------------------

    // conf
    @Value("${task.i18n}")
    private String i18n;

    @Value("${task.accessToken}")
    private String accessToken;

    @Value("${task.timeout}")
    private int timeout;

    @Value("${spring.mail.from}")
    private String emailFrom;

    @Value("${task.triggerpool.fast.max}")
    private int triggerPoolFastMax;

    @Value("${task.triggerpool.slow.max}")
    private int triggerPoolSlowMax;

    @Value("${task.logretentiondays}")
    private int logretentiondays;

    // dao, service

    @Resource
    private TaskLogDao taskLogDao;
    @Resource
    private TaskInfoDao taskInfoDao;
    @Resource
    private TaskRegistryDao taskRegistryDao;
    @Resource
    private TaskGroupDao taskGroupDao;
    @Resource
    private TaskLogReportDao taskLogReportDao;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private DataSource dataSource;
    @Resource
    private TaskAlarmer taskAlarmer;


    public String getI18n() {
        if (!Arrays.asList("zh_CN", "zh_TC", "en").contains(i18n)) {
            return "zh_CN";
        }
        return i18n;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public int getTriggerPoolFastMax() {
        if (triggerPoolFastMax < 200) {
            return 200;
        }
        return triggerPoolFastMax;
    }

    public int getTriggerPoolSlowMax() {
        if (triggerPoolSlowMax < 100) {
            return 100;
        }
        return triggerPoolSlowMax;
    }

    public int getLogretentiondays() {
        if (logretentiondays < 3) {
            return -1;  // Limit greater than or equal to 3, otherwise close
        }
        return logretentiondays;
    }

    public TaskLogDao getTaskLogDao() {
        return taskLogDao;
    }

    public TaskInfoDao getXxlJobInfoDao() {
        return taskInfoDao;
    }

    public TaskRegistryDao getTaskRegistryDao() {
        return taskRegistryDao;
    }

    public TaskGroupDao getXxltaskGroupDao() {
        return taskGroupDao;
    }

    public TaskLogReportDao getTaskLogReportDao() {
        return taskLogReportDao;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public TaskAlarmer getJobAlarmer() {
        return taskAlarmer;
    }

}
