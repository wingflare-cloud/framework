package com.wingflare.engine.task.server.job.support;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.job.support.stop.TaskStopJobContext;

/**
 * @author opensnail
 * @date 2023-10-02 10:43:58
 * @since 2.4.0
 */
public interface JobTaskStopHandler {

    JobTaskTypeEnum getTaskType();

    void stop(TaskStopJobContext context);

}
