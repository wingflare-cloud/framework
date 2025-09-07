package com.wingflare.engine.task.datasource.template.access.log.config;

import com.wingflare.engine.task.datasource.template.access.JobLogAccess;
import com.wingflare.engine.task.datasource.template.access.RetryLogAccess;
import com.wingflare.engine.task.datasource.template.access.log.JobLogMessageAccess;
import com.wingflare.engine.task.datasource.template.access.log.RetryTaskLogMessageAccess;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.log.RetryTaskLogMessageDO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobLogMessageMapper;
import com.wingflare.engine.task.datasource.template.persistence.mapper.RetryTaskLogMessageMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class LogConfig {

    @ConditionalOnMissingBean
    @Bean
    public JobLogAccess<JobLogMessageDO> defaultJobLogAccess(JobLogMessageMapper jobLogMessageMapper, Environment env) {
        return new JobLogMessageAccess(jobLogMessageMapper, env);
    }

    @ConditionalOnMissingBean
    @Bean
    public RetryLogAccess<RetryTaskLogMessageDO> defaultRetryLogAccess(RetryTaskLogMessageMapper retryTaskLogMessageMapper, Environment env) {
        return new RetryTaskLogMessageAccess(retryTaskLogMessageMapper, env);
    }
}
