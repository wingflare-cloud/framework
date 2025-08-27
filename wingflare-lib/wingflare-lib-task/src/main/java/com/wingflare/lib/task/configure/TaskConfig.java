package com.wingflare.lib.task.configure;

import com.wingflare.lib.task.executor.impl.TaskSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TaskConfig {

    private final Logger logger = LoggerFactory.getLogger(TaskConfig.class);

    @Value("${task.admin.addresses}")
    private String adminAddresses;

    @Value("${task.admin.accessToken}")
    private String accessToken;

    @Value("${task.admin.timeout}")
    private int timeout;

    @Value("${task.executor.appName}")
    private String appName;

    @Value("${task.executor.address}")
    private String address;

    @Value("${task.executor.ip}")
    private String ip;

    @Value("${task.executor.port}")
    private int port;

    @Value("${task.executor.logpath}")
    private String logPath;

    @Value("${task.executor.logretentiondays}")
    private int logRetentionDays;


    @Bean
    public TaskSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        TaskSpringExecutor taskSpringExecutor = new TaskSpringExecutor();
        taskSpringExecutor.setAdminAddresses(adminAddresses);
        taskSpringExecutor.setAppname(appName);
        taskSpringExecutor.setAddress(address);
        taskSpringExecutor.setIp(ip);
        taskSpringExecutor.setPort(port);
        taskSpringExecutor.setAccessToken(accessToken);
        taskSpringExecutor.setTimeout(timeout);
        taskSpringExecutor.setLogPath(logPath);
        taskSpringExecutor.setLogRetentionDays(logRetentionDays);

        return taskSpringExecutor;
    }

}
