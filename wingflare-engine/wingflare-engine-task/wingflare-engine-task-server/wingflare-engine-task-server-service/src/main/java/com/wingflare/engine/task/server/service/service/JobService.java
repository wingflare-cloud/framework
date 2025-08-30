package com.wingflare.engine.task.server.service.service;

import com.wingflare.engine.task.common.model.request.base.JobRequest;
import com.wingflare.engine.task.common.model.request.base.JobTriggerRequest;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;
import com.wingflare.engine.task.common.model.response.base.JobResponse;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
public interface JobService {

    Long addJob(JobRequest request);

    boolean updateJob(JobRequest jobRequestVO);

    boolean deleteJobByIds(Set<Long> ids);

    Boolean trigger(JobTriggerRequest jobTrigger);

    Boolean updateJobStatus(StatusUpdateRequest requestDTO);

    <T extends JobResponse> T getJobById(Long id, Class<T> clazz);
}
