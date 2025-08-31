package com.wingflare.engine.task.client.starter;

import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.client.common.config.TaskProperties;
import com.wingflare.lib.task.annotation.TaskExecutor;
import com.wingflare.engine.task.common.core.util.SnailJobNetworkUtils;
import com.wingflare.engine.task.common.log.TaskEngineLog;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ComponentScan({"com.wingflare.engine.task.client.core.*", "com.wingflare.engine.task.client.common.*"})
@ConditionalOnClass(TaskExecutor.class)
@ConditionalOnProperty(prefix = "task", name = "enabled", havingValue = "true")
public class TaskEngineClientJobCoreAutoConfiguration {
    private static final String SNAIL_JOB_CLIENT_HOST = "snail-job.host";

    @Bean
    public Object configureSnailJobHost(SnailJobNetworkUtils networkUtils, TaskProperties taskProperties) {
        String host = taskProperties.getHost();
        if (StrUtil.isBlank(host)) {
            host = System.getProperty(SNAIL_JOB_CLIENT_HOST);
            if (StrUtil.isNotBlank(host)) {
                taskProperties.setHost(host);
            }
        }

        if (StrUtil.isBlank(host)) {
            // 获取首选IP地址
            host = networkUtils.findPreferredHostAddress();
            taskProperties.setHost(host);
            System.setProperty(SNAIL_JOB_CLIENT_HOST, host);
        }

        TaskEngineLog.LOCAL.info("Wingflare-task 客户端IP为: {}" , host);
        return null; // 不需要实际的bean实例，只是触发配置
    }
}
