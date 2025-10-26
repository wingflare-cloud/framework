package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.server.web.service.WorkflowNodeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaowoniu
 * @date 2023-12-23 17:47:51
 * @since 2.6.0
 */
@RestController
@RequestMapping("/workflow/node")
public class WorkflowNodeController {
    private final WorkflowNodeService workflowNodeService;

    public WorkflowNodeController(WorkflowNodeService workflowNodeService) {
        this.workflowNodeService = workflowNodeService;
    }

    @PostMapping("/stop/{nodeId}/{workflowTaskBatchId}")
    @RequiresLogin
    @RequiresPermissions("task.workflow.node.stop")
    public Boolean stop(@PathVariable("nodeId") Long nodeId,
                        @PathVariable("workflowTaskBatchId") Long workflowTaskBatchId) {
        return workflowNodeService.stop(nodeId, workflowTaskBatchId);
    }

    @PostMapping("/retry/{nodeId}/{workflowTaskBatchId}")
    @RequiresLogin
    @RequiresPermissions("task.workflow.node.retry")
    public Boolean retry(@PathVariable("nodeId") Long nodeId,
                         @PathVariable("workflowTaskBatchId") Long workflowTaskBatchId) {
        return workflowNodeService.retry(nodeId, workflowTaskBatchId);
    }
}
