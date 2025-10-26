package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.server.service.service.RetryService;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.server.web.model.response.RetryResponseWebVO;
import com.wingflare.engine.task.server.web.service.RetryWebService;
import com.wingflare.engine.task.server.web.service.impl.RetryWebServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 重试数据管理接口
 *
 * @author opensnail
 * @date 2022-02-27
 */
@RestController
@RequestMapping("/retry")
public class RetryController {
    private final RetryWebService retryWebService;
    private final RetryService retryService;

    public RetryController(RetryWebService retryWebService, RetryWebServiceImpl retryService) {
        this.retryWebService = retryWebService;
        this.retryService = retryService;
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.list")
    @GetMapping("list")
    public PageResult<List<RetryResponseWebVO>> getRetryTaskPage(RetryQueryVO queryVO) {
        return retryWebService.getRetryPage(queryVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.detail")
    @GetMapping("{id}")
    public RetryResponseWebVO getRetryTaskById(@PathVariable("id") Long id) {
        return retryService.getRetryById(id, RetryResponseWebVO.class);
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.updateStatus")
    @PutMapping("status")
    public boolean updateRetryTaskStatus(@RequestBody StatusUpdateRequestWebVO retryUpdateStatusRequestVO) {
        return retryService.updateRetryStatus(retryUpdateStatusRequestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.save")
    @PostMapping
    public int saveRetryTask(@RequestBody @Validated RetrySaveRequestVO retryTaskRequestVO) {
        return retryWebService.saveRetryTask(retryTaskRequestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.generateIdempotentId")
    @PostMapping("/generate/idempotent-id")
    public Result<String> idempotentIdGenerate(@RequestBody @Validated GenerateRetryIdempotentIdVO generateRetryIdempotentIdVO) {
        return new Result<>(retryWebService.idempotentIdGenerate(generateRetryIdempotentIdVO));
    }


    @RequiresLogin
    @RequiresPermissions("task.retry.update")
    @PutMapping("/batch")
    public Integer updateRetryTaskExecutorName(@RequestBody @Validated RetryUpdateExecutorNameRequestVO requestVO) {
        return retryWebService.updateRetryExecutorName(requestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.delete")
    @DeleteMapping("/batch")
    public boolean batchDeleteRetry(@RequestBody @Validated BatchDeleteRetryTaskVO requestVO) {
        return retryWebService.batchDeleteRetry(requestVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.parseLogs")
    @PostMapping("/batch")
    public Integer parseLogs(@RequestBody @Validated ParseLogsVO parseLogsVO) {
        return retryWebService.parseLogs(parseLogsVO);
    }

    @RequiresLogin
    @RequiresPermissions("task.retry.manualTrigger")
    @PostMapping("/manual/trigger/retry/task")
    public boolean manualTriggerRetryTask(@RequestBody @Validated ManualTriggerTaskRequestVO requestVO) {
        return retryWebService.manualTriggerRetryTask(requestVO);
    }

}
