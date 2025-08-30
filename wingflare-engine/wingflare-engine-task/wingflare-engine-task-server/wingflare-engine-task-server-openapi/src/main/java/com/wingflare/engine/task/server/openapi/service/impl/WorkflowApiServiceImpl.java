package com.wingflare.engine.task.server.openapi.service.impl;

import com.wingflare.engine.task.server.openapi.service.WorkflowApiService;
import com.wingflare.engine.task.server.openapi.util.OpenApiSessionUtils;
import com.wingflare.engine.task.server.service.service.impl.AbstractWorkflowService;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-07-06
 */
@Component("workflowApiCommonService")
public class WorkflowApiServiceImpl extends AbstractWorkflowService implements WorkflowApiService {

    @Override
    protected String getNamespaceId() {
        return OpenApiSessionUtils.getNamespaceId();
    }
}
