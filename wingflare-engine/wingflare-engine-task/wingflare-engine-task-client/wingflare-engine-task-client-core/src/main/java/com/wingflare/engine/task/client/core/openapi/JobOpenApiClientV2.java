package com.wingflare.engine.task.client.core.openapi;

import com.wingflare.engine.task.client.common.annotation.Mapping;
import com.wingflare.engine.task.client.common.annotation.Param;
import com.wingflare.engine.task.client.common.rpc.client.RequestMethod;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.common.model.request.JobTriggerApiRequest;
import com.wingflare.engine.task.common.model.request.base.JobRequest;
import com.wingflare.engine.task.common.model.request.base.StatusUpdateRequest;
import com.wingflare.engine.task.common.model.request.base.WorkflowTriggerRequest;

import java.util.Set;

import static com.wingflare.engine.task.common.core.constant.SystemConstants.HTTP_PATH.*;

public interface JobOpenApiClientV2 {

    @Mapping(method = RequestMethod.POST, path = OPENAPI_ADD_JOB)
    Result<Object> addJob(JobRequest jobRequest);

    @Mapping(method = RequestMethod.PUT, path = OPENAPI_UPDATE_JOB)
    Result<Object> updateJob(JobRequest jobRequest);

    @Mapping(method = RequestMethod.GET, path = OPENAPI_GET_JOB_DETAIL_V2)
    Result<Object> getJobDetail(@Param("id") Long jobId);

    @Mapping(method = RequestMethod.GET, path = OPENAPI_GET_JOB_BATCH_DETAIL_V2)
    Result<Object> getJobBatchDetail(@Param("id") Long jobBatchId);

    @Mapping(method = RequestMethod.GET, path = OPENAPI_GET_WORKFLOW_BATCH_DETAIL_V2)
    Result<Object> getWorkflowBatchDetail(@Param("id") Long jobBatchId);

    @Mapping(method = RequestMethod.POST, path = OPENAPI_TRIGGER_JOB_V2)
    Result<Object> triggerJob(JobTriggerApiRequest request);

    @Mapping(method = RequestMethod.POST, path = OPENAPI_TRIGGER_WORKFLOW_V2)
    Result<Object> triggerWorkFlow(WorkflowTriggerRequest jobTriggerDTO);

    @Mapping(method = RequestMethod.PUT, path = OPENAPI_UPDATE_JOB_STATUS_V2)
    Result<Object> updateJobStatus(StatusUpdateRequest statusDTO);

    @Mapping(method = RequestMethod.PUT, path = OPENAPI_UPDATE_WORKFLOW_STATUS_V2)
    Result<Object> updateWorkFlowStatus(StatusUpdateRequest statusDTO);

    @Mapping(method = RequestMethod.DELETE, path = OPENAPI_DELETE_JOB_V2)
    Result<Object> deleteJob(Set<Long> toDeleteIds);

    @Mapping(method = RequestMethod.DELETE, path = OPENAPI_DELETE_WORKFLOW_V2)
    Result<Object> deleteWorkflow(Set<Long> toDeleteIds);
}
