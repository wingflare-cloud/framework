package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.server.common.vo.WorkflowBatchResponseVO;
import com.wingflare.engine.task.server.service.service.WorkflowBatchService;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.WorkflowBatchQueryVO;
import com.wingflare.engine.task.server.web.model.response.WorkflowDetailResponseWebVO;
import com.wingflare.engine.task.server.web.service.WorkflowWebBatchService;
import com.wingflare.engine.task.server.web.service.impl.WorkflowWebBatchServiceImpl;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


/**
 * @author xiaowoniu
 * @date 2023-12-23 17:47:51
 * @since 2.6.0
 */
@RestController
@RequestMapping("/workflow/batch")
public class WorkflowBatchController {
    private final WorkflowWebBatchService workflowWebBatchService;
    private final WorkflowBatchService workflowBatchService;

    public WorkflowBatchController(WorkflowWebBatchService workflowWebBatchService, WorkflowWebBatchServiceImpl workflowBatchService) {
        this.workflowWebBatchService = workflowWebBatchService;
        this.workflowBatchService = workflowBatchService;
    }

    @RequiresLogin
    @RequiresPermissions("task.workflow.recordList")
    @GetMapping("/page/list")
    public PageResult<List<WorkflowBatchResponseVO>> listPage(WorkflowBatchQueryVO queryVO) {
        return workflowWebBatchService.listPage(queryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.workflow.recordDetail")
    @GetMapping("{id}")
    public WorkflowDetailResponseWebVO getWorkflowBatchDetail(@PathVariable("id") Long id) {
        return workflowBatchService.getWorkflowBatchById(id, WorkflowDetailResponseWebVO.class);
    }

    @PostMapping("/stop/{id}")
    @RequiresLogin
    @RequiresPermissions("task.workflow.recordStop")
    public Boolean stop(@PathVariable("id") Long id) {
        return workflowWebBatchService.stop(id);
    }

    @DeleteMapping("/ids")
    @RequiresLogin
    @RequiresPermissions("task.workflow.recordDeleteIds")
    public Boolean deleteByIds(@RequestBody
                               @NotEmpty(message = "ids cannot be null")
                               @Size(max = 100, message = "Maximum {max} deletions")
                               Set<Long> ids) {
        return workflowWebBatchService.deleteByIds(ids);
    }
}
