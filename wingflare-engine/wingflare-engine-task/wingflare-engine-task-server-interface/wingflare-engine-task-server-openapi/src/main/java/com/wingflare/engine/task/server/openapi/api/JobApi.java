package com.wingflare.engine.task.server.openapi.api;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.model.request.JobApiRequest;
import com.wingflare.engine.task.common.model.request.JobTriggerApiRequest;
import com.wingflare.engine.task.common.model.request.StatusUpdateApiRequest;
import com.wingflare.engine.task.common.model.response.JobApiResponse;
import com.wingflare.engine.task.server.openapi.service.impl.JobApiServiceImpl;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-05
 */
@RestController
public class JobApi {

    private final JobApiServiceImpl jobService;

    public JobApi(JobApiServiceImpl jobService) {
        this.jobService = jobService;
    }

    @PostMapping(SystemConstants.HTTP_PATH.OPENAPI_ADD_JOB)
    public Long addJob(@RequestBody @Validated JobApiRequest jobRequest) {
        return jobService.addJob(jobRequest);
    }

    @PutMapping(SystemConstants.HTTP_PATH.OPENAPI_UPDATE_JOB)
    public boolean updateJob(@RequestBody @Validated JobApiRequest jobRequest) {
        return jobService.updateJob(jobRequest);
    }

    @DeleteMapping(SystemConstants.HTTP_PATH.OPENAPI_DELETE_JOB_V2)
    public boolean deleteJobByIds(@RequestBody
                                  @NotEmpty(message = "ids cannot be null")
                                  @Size(max = 100, message = "Maximum {max} deletions") Set<Long> ids) {
        return jobService.deleteJobByIds(ids);
    }

    @PostMapping(SystemConstants.HTTP_PATH.OPENAPI_TRIGGER_JOB_V2)
    public Boolean trigger(@RequestBody @Validated JobTriggerApiRequest jobTrigger) {
        return jobService.trigger(jobTrigger);
    }

    @PutMapping(SystemConstants.HTTP_PATH.OPENAPI_UPDATE_JOB_STATUS_V2)
    public Boolean updateJobStatus(@RequestBody @Validated StatusUpdateApiRequest requestDTO) {
        return jobService.updateJobStatus(requestDTO);
    }

    @GetMapping(SystemConstants.HTTP_PATH.OPENAPI_GET_JOB_DETAIL_V2)
    public JobApiResponse getJobById(@RequestParam("id") Long id) {
        return jobService.getJobById(id, JobApiResponse.class);
    }

}
