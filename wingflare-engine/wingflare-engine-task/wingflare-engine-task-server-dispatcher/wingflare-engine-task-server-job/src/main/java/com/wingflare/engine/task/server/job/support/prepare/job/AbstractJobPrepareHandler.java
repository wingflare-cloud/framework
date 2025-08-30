package com.wingflare.engine.task.server.job.support.prepare.job;

import com.wingflare.engine.task.server.job.dto.JobTaskPrepareDTO;
import com.wingflare.engine.task.server.job.support.JobPrepareHandler;

/**
 * @author opensnail
 * @date 2023-10-02 09:57:55
 * @since 2.4.0
 */
public abstract class AbstractJobPrepareHandler implements JobPrepareHandler {

    @Override
    public void handle(JobTaskPrepareDTO jobPrepareDTO) {

        doHandle(jobPrepareDTO);
    }

    protected abstract void doHandle(JobTaskPrepareDTO jobPrepareDTO);
}
