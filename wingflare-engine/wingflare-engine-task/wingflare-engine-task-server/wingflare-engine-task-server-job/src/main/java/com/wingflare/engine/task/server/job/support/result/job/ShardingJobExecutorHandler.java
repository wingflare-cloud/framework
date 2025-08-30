package com.wingflare.engine.task.server.job.support.result.job;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.server.job.support.handler.WorkflowBatchHandler;
import com.wingflare.task.datasource.template.persistence.mapper.GroupConfigMapper;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.task.datasource.template.persistence.mapper.JobTaskMapper;
import org.springframework.stereotype.Component;

/**
 * @author: opensnail
 * @date : 2024-09-04
 * @since :1.2.0
 */
@Component
public class ShardingJobExecutorHandler extends AbstractJobExecutorResultHandler {

    public ShardingJobExecutorHandler(
        final JobTaskMapper jobTaskMapper,
        final JobTaskBatchMapper jobTaskBatchMapper,
        final WorkflowBatchHandler workflowBatchHandler,
        final GroupConfigMapper groupConfigMapper) {
        super(jobTaskMapper, jobTaskBatchMapper, workflowBatchHandler, groupConfigMapper);
    }

    @Override
    public JobTaskTypeEnum getTaskInstanceType() {
        return JobTaskTypeEnum.SHARDING;
    }

    @Override
    protected void doHandleSuccess(final JobExecutorResultContext context) {
    }

    @Override
    protected void doHandleStop(final JobExecutorResultContext context) {

    }

    @Override
    protected void doHandleFail(final JobExecutorResultContext context) {

    }

}
