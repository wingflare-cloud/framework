package com.wingflare.engine.task.server.web.controller;


import cn.hutool.core.lang.Pair;
import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.server.common.vo.WorkflowResponseVO;
import com.wingflare.engine.task.server.common.vo.request.WorkflowRequestVO;
import com.wingflare.engine.task.server.service.service.WorkflowService;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.server.web.model.response.WorkflowDetailResponseWebVO;
import com.wingflare.engine.task.server.web.service.WorkflowWebService;
import com.wingflare.engine.task.server.web.service.impl.WorkflowWebServiceImpl;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;


/**
 * @author xiaowoniu
 * @date 2023-12-12 21:50:46
 * @since 2.6.0
 */
@RestController
@RequestMapping("/workflow")
public class WorkflowController {
    private final WorkflowWebService workflowWebService;
    private final WorkflowService workflowService;

    public WorkflowController(WorkflowWebService workflowWebService, WorkflowWebServiceImpl workflowService) {
        this.workflowWebService = workflowWebService;
        this.workflowService = workflowService;
    }

    @PostMapping
    @RequiresLogin
    @RequiresPermissions("task.workflow.save")
    public Boolean saveWorkflow(@RequestBody @Validated WorkflowRequestVO workflowRequestVO) {
        return workflowWebService.saveWorkflow(workflowRequestVO);
    }

    @GetMapping("/page/list")
    @RequiresLogin
    @RequiresPermissions("task.workflow.list")
    public PageResult<List<WorkflowResponseVO>> listPage(WorkflowQueryVO queryVO) {
        return workflowWebService.listPage(queryVO);
    }

    @PutMapping
    @RequiresLogin
    @RequiresPermissions("task.workflow.update")
    public Boolean updateWorkflow(@RequestBody @Validated WorkflowRequestVO workflowRequestVO) {
        return workflowWebService.updateWorkflow(workflowRequestVO);
    }

    @GetMapping("{id}")
    @RequiresLogin
    @RequiresPermissions("task.workflow.detail")
    public WorkflowDetailResponseWebVO getWorkflowDetail(@PathVariable("id") Long id) throws IOException {
        return workflowWebService.getWorkflowDetail(id);
    }

    @PutMapping("/update/status")
    @RequiresLogin
    @RequiresPermissions("task.workflow.updateStatus")
    public Boolean updateStatus(@RequestBody @Validated StatusUpdateRequestWebVO requestVO) {
        return workflowService.updateWorkFlowStatus(requestVO);
    }

    @DeleteMapping("/ids")
    @RequiresLogin
    @RequiresPermissions("task.workflow.deleteIds")
    public Boolean deleteByIds(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return workflowService.deleteWorkflowByIds(ids);
    }

    @PostMapping("/trigger")
    @RequiresLogin
    @RequiresPermissions("task.workflow.trigger")
    public Boolean trigger(@RequestBody @Validated WorkflowTriggerVO triggerVO) {
        return workflowService.triggerWorkFlow(triggerVO);
    }

    @GetMapping("/workflow-name/list")
    @RequiresLogin
    @RequiresPermissions("task.workflow.list")
    public List<WorkflowResponseVO> getWorkflowNameList(
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "workflowId", required = false) Long workflowId,
            @RequestParam(value = "groupName", required = false) String groupName) {
        return workflowWebService.getWorkflowNameList(keywords, workflowId, groupName);
    }

    @PostMapping("/check-node-expression")
    @RequiresLogin
    @RequiresPermissions("task.workflow.checkNodeExpression")
    public Pair<Integer, Object> checkNodeExpression(@RequestBody @Validated CheckDecisionVO checkDecisionVO) {
        return workflowWebService.checkNodeExpression(checkDecisionVO);
    }


}
