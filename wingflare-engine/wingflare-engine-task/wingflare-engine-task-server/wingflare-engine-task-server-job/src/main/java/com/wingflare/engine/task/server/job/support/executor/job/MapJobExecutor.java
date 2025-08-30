package com.wingflare.engine.task.server.job.support.executor.job;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import org.springframework.stereotype.Component;

/**
 * @author: shuguang.zhang
 * @date : 2024-06-19
 */
@Component
public class MapJobExecutor extends MapReduceJobExecutor {

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.MAP;
    }

    @Override
    protected void doExecute(final JobExecutorContext context) {
        super.doExecute(context);
    }
}
