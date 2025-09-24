package com.wingflare.engine.task.client.core.openapi;

import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.JobApiRequest;
import com.wingflare.engine.task.common.model.request.JobTriggerApiRequest;
import com.wingflare.engine.task.common.model.request.WorkflowTriggerApiRequest;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;

import java.util.Set;

@Deprecated(since = "1.7.0")
public interface JobOpenApiClient {
    @Mapping(method = RequestMethod.POST, path = "/api/job/add")
    Result<Object> addJob(JobApiRequest jobApiRequest);

    @Mapping(method = RequestMethod.POST, path = "/api/job/update")
    Result<Object> updateJob(JobApiRequest jobApiRequest);

    @Mapping(method = RequestMethod.POST, path = "/api/job/getJobDetail")
    Result<Object> getJobDetail(Long jobId);

    @Mapping(method = RequestMethod.POST, path = "/api/job/getJobBatchDetail")
    Result<Object> getJobBatchDetail(Long jobBatchId);

    @Mapping(method = RequestMethod.POST, path = "/api/job/getWorkflowBatchDetail")
    Result<Object> getWorkflowBatchDetail(Long jobBatchId);

    @Mapping(method = RequestMethod.POST, path = "/api/job/triggerJob")
    Result<Object> triggerJob(JobTriggerApiRequest request);

    @Mapping(method = RequestMethod.POST, path = "/api/job/triggerWorkFlow")
    Result<Object> triggerWorkFlow(WorkflowTriggerApiRequest jobTriggerDTO);

    @Mapping(method = RequestMethod.POST, path = "/api/job/updateJobStatus")
    Result<Object> updateJobStatus(StatusUpdateRequest statusDTO);

    @Mapping(method = RequestMethod.POST, path = "/api/job/updateWorkFlowStatus")
    Result<Object> updateWorkFlowStatus(StatusUpdateRequest statusDTO);

    @Mapping(method = RequestMethod.POST, path = "/api/job/deleteJob")
    Result<Object> deleteJob(Set<Long> toDeleteIds);

    @Mapping(method = RequestMethod.POST, path = "/api/job/deleteWorkFlow")
    Result<Object> deleteWorkflow(Set<Long> toDeleteIds);
}
