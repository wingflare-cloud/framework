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

@Configuration
public class LogConfig {

    @ConditionalOnMissingBean
    @Bean
    public JobLogAccess<JobLogMessageDO> defaultJobLogAccess(JobLogMessageMapper jobLogMessageMapper) {
        return new JobLogMessageAccess(jobLogMessageMapper);
    }

    @ConditionalOnMissingBean
    @Bean
    public RetryLogAccess<RetryTaskLogMessageDO> defaultRetryLogAccess(RetryTaskLogMessageMapper retryTaskLogMessageMapper) {
        return new RetryTaskLogMessageAccess(retryTaskLogMessageMapper);
    }
}
