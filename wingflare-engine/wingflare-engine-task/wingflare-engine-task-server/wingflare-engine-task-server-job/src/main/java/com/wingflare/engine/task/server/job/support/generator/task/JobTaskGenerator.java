package com.wingflare.engine.task.server.job.support.generator.task;

import com.wingflare.engine.task.common.core.enums.JobTaskTypeEnum;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-10-02 10:43:58
 * @since 2.4.0
 */
public interface JobTaskGenerator {

    JobTaskTypeEnum getTaskInstanceType();

    List<JobTask> generate(JobTaskGenerateContext context);

}
