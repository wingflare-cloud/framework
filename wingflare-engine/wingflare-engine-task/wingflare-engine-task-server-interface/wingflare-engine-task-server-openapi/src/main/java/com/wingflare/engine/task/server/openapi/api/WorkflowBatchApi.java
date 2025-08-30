package com.wingflare.engine.task.server.openapi.api;

import com.wingflare.engine.task.common.core.constant.SystemConstants;
import com.wingflare.engine.task.common.model.response.WorkflowDetailApiResponse;
import com.wingflare.engine.task.server.openapi.service.impl.WorkflowBatchApiServiceImpl;
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
public class WorkflowBatchApi {

    private final WorkflowBatchApiServiceImpl workflowBatchService;

    public WorkflowBatchApi(WorkflowBatchApiServiceImpl workflowBatchService) {
        this.workflowBatchService = workflowBatchService;
    }

    @GetMapping(SystemConstants.HTTP_PATH.OPENAPI_GET_WORKFLOW_BATCH_DETAIL_V2)
    public WorkflowDetailApiResponse getWorkflowBatchById(@RequestParam("id") Long id) {
        return workflowBatchService.getWorkflowBatchById(id, WorkflowDetailApiResponse.class);
    }
}
