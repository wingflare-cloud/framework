package com.wingflare.engine.task.server.job.support;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.job.support.result.job.JobExecutorResultContext;

/**
 * @author: opensnail
 * @date : 2024-09-04
 * @since :1.2.0
 */
public interface JobExecutorResultHandler {

    JobTaskTypeEnum getTaskInstanceType();

    void handleResult(JobExecutorResultContext context);
}
