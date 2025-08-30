package com.wingflare.engine.task.server.web.controller;

import cn.hutool.core.lang.Pair;
import com.wingflare.engine.task.common.core.annotation.OriginalControllerReturnValue;
import com.wingflare.engine.task.server.common.vo.WorkflowResponseVO;
import com.wingflare.engine.task.server.common.vo.request.WorkflowRequestVO;
import com.wingflare.engine.task.server.service.service.WorkflowService;
import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.annotation.RoleEnum;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.server.web.model.response.WorkflowDetailResponseWebVO;
import com.wingflare.engine.task.server.web.service.WorkflowWebService;
import com.wingflare.engine.task.server.web.service.impl.WorkflowWebServiceImpl;
import com.wingflare.engine.task.server.web.util.ExportUtils;
import com.wingflare.engine.task.server.web.util.ImportUtils;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    @LoginRequired(role = RoleEnum.USER)
    public Boolean saveWorkflow(@RequestBody @Validated WorkflowRequestVO workflowRequestVO) {
        return workflowWebService.saveWorkflow(workflowRequestVO);
    }

    @GetMapping("/page/list")
    @LoginRequired(role = RoleEnum.USER)
    public PageResult<List<WorkflowResponseVO>> listPage(WorkflowQueryVO queryVO) {
        return workflowWebService.listPage(queryVO);
    }

    @PutMapping
    @LoginRequired(role = RoleEnum.USER)
    public Boolean updateWorkflow(@RequestBody @Validated WorkflowRequestVO workflowRequestVO) {
        return workflowWebService.updateWorkflow(workflowRequestVO);
    }

    @GetMapping("{id}")
    @LoginRequired(role = RoleEnum.USER)
    public WorkflowDetailResponseWebVO getWorkflowDetail(@PathVariable("id") Long id) throws IOException {
        return workflowWebService.getWorkflowDetail(id);
    }

    @PutMapping("/update/status")
    @LoginRequired(role = RoleEnum.USER)
    public Boolean updateStatus(@RequestBody @Validated StatusUpdateRequestWebVO requestVO) {
        return workflowService.updateWorkFlowStatus(requestVO);
    }

    @DeleteMapping("/ids")
    @LoginRequired(role = RoleEnum.USER)
    public Boolean deleteByIds(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return workflowService.deleteWorkflowByIds(ids);
    }

    @PostMapping("/trigger")
    @LoginRequired(role = RoleEnum.USER)
    public Boolean trigger(@RequestBody @Validated WorkflowTriggerVO triggerVO) {
        return workflowService.triggerWorkFlow(triggerVO);
    }

    @GetMapping("/workflow-name/list")
    @LoginRequired(role = RoleEnum.USER)
    public List<WorkflowResponseVO> getWorkflowNameList(
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "workflowId", required = false) Long workflowId,
            @RequestParam(value = "groupName", required = false) String groupName) {
        return workflowWebService.getWorkflowNameList(keywords, workflowId, groupName);
    }

    @PostMapping("/check-node-expression")
    @LoginRequired(role = RoleEnum.USER)
    public Pair<Integer, Object> checkNodeExpression(@RequestBody @Validated CheckDecisionVO checkDecisionVO) {
        return workflowWebService.checkNodeExpression(checkDecisionVO);
    }

    @LoginRequired
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void importScene(@RequestPart("file") MultipartFile file) throws IOException {
        // 写入数据
        workflowWebService.importWorkflowTask(ImportUtils.parseList(file, WorkflowRequestVO.class));
    }

    @LoginRequired
    @PostMapping("/export")
    @OriginalControllerReturnValue
    public ResponseEntity<String> export(@RequestBody ExportWorkflowVO exportWorkflowVO) {
        return ExportUtils.doExport(workflowWebService.exportWorkflowTask(exportWorkflowVO));
    }

}
