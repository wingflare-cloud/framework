package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.common.core.model.Result;
import com.wingflare.engine.task.server.service.service.RetryService;
import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.server.web.model.response.RetryResponseWebVO;
import com.wingflare.engine.task.server.web.service.RetryWebService;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("retryWebCommonService")
    private final RetryService retryService;

    public RetryController(RetryWebService retryWebService, RetryService retryService) {
        this.retryWebService = retryWebService;
        this.retryService = retryService;
    }

    @LoginRequired
    @GetMapping("list")
    public PageResult<List<RetryResponseWebVO>> getRetryTaskPage(RetryQueryVO queryVO) {
        return retryWebService.getRetryPage(queryVO);
    }

    @LoginRequired
    @GetMapping("{id}")
    public RetryResponseWebVO getRetryTaskById(@PathVariable("id") Long id) {
        return retryService.getRetryById(id, RetryResponseWebVO.class);
    }

    @LoginRequired
    @PutMapping("status")
    public boolean updateRetryTaskStatus(@RequestBody StatusUpdateRequestWebVO retryUpdateStatusRequestVO) {
        return retryService.updateRetryStatus(retryUpdateStatusRequestVO);
    }

    @LoginRequired
    @PostMapping
    public int saveRetryTask(@RequestBody @Validated RetrySaveRequestVO retryTaskRequestVO) {
        return retryWebService.saveRetryTask(retryTaskRequestVO);
    }

    @LoginRequired
    @PostMapping("/generate/idempotent-id")
    public Result<String> idempotentIdGenerate(@RequestBody @Validated GenerateRetryIdempotentIdVO generateRetryIdempotentIdVO) {
        return new Result<>(retryWebService.idempotentIdGenerate(generateRetryIdempotentIdVO));
    }


    @LoginRequired
    @PutMapping("/batch")
    public Integer updateRetryTaskExecutorName(@RequestBody @Validated RetryUpdateExecutorNameRequestVO requestVO) {
        return retryWebService.updateRetryExecutorName(requestVO);
    }

    @LoginRequired
    @DeleteMapping("/batch")
    public boolean batchDeleteRetry(@RequestBody @Validated BatchDeleteRetryTaskVO requestVO) {
        return retryWebService.batchDeleteRetry(requestVO);
    }

    @LoginRequired
    @PostMapping("/batch")
    public Integer parseLogs(@RequestBody @Validated ParseLogsVO parseLogsVO) {
        return retryWebService.parseLogs(parseLogsVO);
    }

    @LoginRequired
    @PostMapping("/manual/trigger/retry/task")
    public boolean manualTriggerRetryTask(@RequestBody @Validated ManualTriggerTaskRequestVO requestVO) {
        return retryWebService.manualTriggerRetryTask(requestVO);
    }

}
