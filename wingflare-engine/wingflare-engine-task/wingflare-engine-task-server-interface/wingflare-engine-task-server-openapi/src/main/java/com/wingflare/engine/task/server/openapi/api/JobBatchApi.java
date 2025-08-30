package com.wingflare.engine.task.server.openapi.api;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.model.response.JobBatchApiResponse;
import com.wingflare.engine.task.server.openapi.service.JobBatchApiService;
import com.wingflare.engine.task.server.service.service.JobBatchService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-06
 */
@RestController
public class JobBatchApi {
    @Qualifier("jobBatchApiCommonService")
    private final JobBatchService jobBatchService;
    private final JobBatchApiService jobBatchApiService;

    public JobBatchApi(JobBatchService jobBatchService, JobBatchApiService jobBatchApiService) {
        this.jobBatchService = jobBatchService;
        this.jobBatchApiService = jobBatchApiService;
    }

    @GetMapping(SystemConstants.HTTP_PATH.OPENAPI_GET_JOB_BATCH_DETAIL_V2)
    public JobBatchApiResponse getJobBatchByIds(@RequestParam("id") Long id) {
        return jobBatchService.getJobBatchById(id, JobBatchApiResponse.class);
    }
}
