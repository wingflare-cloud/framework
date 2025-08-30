package com.wingflare.engine.task.server.openapi.api;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.model.response.JobBatchApiResponse;
import com.wingflare.engine.task.server.openapi.service.impl.JobBatchApiServiceImpl;
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

    private final JobBatchApiServiceImpl jobBatchService;

    public JobBatchApi(JobBatchApiServiceImpl jobBatchService) {
        this.jobBatchService = jobBatchService;
    }

    @GetMapping(SystemConstants.HTTP_PATH.OPENAPI_GET_JOB_BATCH_DETAIL_V2)
    public JobBatchApiResponse getJobBatchByIds(@RequestParam("id") Long id) {
        return jobBatchService.getJobBatchById(id, JobBatchApiResponse.class);
    }
}
