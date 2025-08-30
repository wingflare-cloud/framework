package com.wingflare.engine.task.server.job.support.prepare.job;

import com.wingflare.engine.task.common.core.enums.JobTaskBatchStatusEnum;
import com.wingflare.engine.task.server.common.util.DateUtils;
import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.dto.JobTimerTaskDTO;
import com.wingflare.engine.task.server.job.support.timer.JobTimerTask;
import com.wingflare.engine.task.server.job.support.timer.JobTimerWheel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;

/**
 * 处理处于{@link JobTaskBatchStatusEnum::WAIT}状态的任务
 *
 * @author opensnail
 * @date 2023-10-05 18:29:22
 * @since 2.4.0
 */
@Component
public class WaitJobPrepareHandler extends AbstractJobPrepareHandler {

    private static final Logger log = LoggerFactory.getLogger(WaitJobPrepareHandler.class);

    @Override
    public boolean matches(Integer status) {
        return JobTaskBatchStatusEnum.WAITING.getStatus() == status;
    }

    @Override
    protected void doHandle(JobTaskPrepareDTO jobPrepareDTO) {
        log.debug("Pending tasks exist. Task batch ID:[{}]", jobPrepareDTO.getTaskBatchId());

        // 若时间轮中数据不存在则重新加入
        if (!JobTimerWheel.isExisted(MessageFormat.format(JobTimerTask.IDEMPOTENT_KEY_PREFIX, jobPrepareDTO.getTaskBatchId()))) {
            log.info("Pending tasks exist and taskBatchId:[{}] does not exist in the time wheel", jobPrepareDTO.getTaskBatchId());

            // 进入时间轮
            long delay = jobPrepareDTO.getNextTriggerAt() - DateUtils.toNowMilli();
            JobTimerTaskDTO jobTimerTaskDTO = new JobTimerTaskDTO();
            jobTimerTaskDTO.setTaskBatchId(jobPrepareDTO.getTaskBatchId());
            jobTimerTaskDTO.setJobId(jobPrepareDTO.getJobId());

            JobTimerWheel.registerWithJob(() -> new JobTimerTask(jobTimerTaskDTO), Duration.ofMillis(delay));
        }
    }

}
