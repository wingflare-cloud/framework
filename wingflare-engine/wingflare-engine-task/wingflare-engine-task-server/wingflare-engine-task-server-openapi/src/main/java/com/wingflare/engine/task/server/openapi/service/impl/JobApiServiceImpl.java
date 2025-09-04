package com.wingflare.engine.task.server.openapi.service.impl;

import com.wingflare.engine.task.common.model.request.base.JobRequest;
import com.wingflare.engine.task.common.model.response.base.JobResponse;
import com.wingflare.engine.task.server.openapi.service.JobApiService;
import com.wingflare.engine.task.server.openapi.util.OpenApiSessionUtils;
import com.wingflare.engine.task.server.service.service.impl.AbstractJobService;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
@Service("jobApiCommonService")
public class JobApiServiceImpl extends AbstractJobService implements JobApiService {

    @Override
    protected void addJobPopulate(Job job, JobRequest request) {
    }

    @Override
    protected void addJobPreValidator(JobRequest request) {

    }

    @Override
    protected void getJobByIdAfter(JobResponse responseBaseDTO, Job job) {

    }

    @Override
    protected void updateJobPreValidator(JobRequest jobRequest) {

    }

    @Override
    protected String getNamespaceId() {
        return OpenApiSessionUtils.getNamespaceId();
    }

}
