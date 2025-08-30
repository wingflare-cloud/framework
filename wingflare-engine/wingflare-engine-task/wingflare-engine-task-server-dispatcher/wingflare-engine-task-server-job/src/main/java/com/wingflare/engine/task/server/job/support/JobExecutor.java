package com.wingflare.engine.task.server.job.support;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.job.support.executor.job.JobExecutorContext;

/**
 * @author opensnail
 * @date 2023-09-24 11:40:21
 * @since 2.4.0
 */
public interface JobExecutor {

    JobTaskTypeEnum getTaskInstanceType();

    void execute(JobExecutorContext context);
}
