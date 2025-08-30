package com.wingflare.engine.task.server.job.support.prepare.job;

import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.JobTaskConverter;
import com.wingflare.engine.task.server.job.support.generator.batch.JobTaskBatchGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum.COMPLETED;

/**
 * 处理处于已完成 {@link JobTaskBatchStatusEnum::COMPLETED} 状态的任务
 *
 * @author opensnail
 * @date 2023-10-02 10:16:28
 * @since 2.4.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class TerminalJobPrepareHandler extends AbstractJobPrepareHandler {

    private static final Logger log = LoggerFactory.getLogger(TerminalJobPrepareHandler.class);
    @Autowired
    private JobTaskBatchGenerator jobTaskBatchGenerator;

    @Override
    public boolean matches(Integer status) {
        return COMPLETED.contains(status);
    }

    @Override
    protected void doHandle(JobTaskPrepareDTO jobPrepareDTO) {
        log.debug("No data being processed. Job ID:[{}]", jobPrepareDTO.getJobId());

        // 生成任务批次
        jobTaskBatchGenerator.generateJobTaskBatch(JobTaskConverter.INSTANCE.toJobTaskGeneratorContext(jobPrepareDTO));
    }
}
