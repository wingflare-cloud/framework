package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.BatchDeleteRetryDeadLetterVO;
import com.wingflare.engine.task.server.web.model.request.BatchRollBackRetryDeadLetterVO;
import com.wingflare.engine.task.server.web.model.request.RetryDeadLetterQueryVO;
import com.wingflare.engine.task.server.web.model.response.RetryDeadLetterResponseVO;
import com.wingflare.engine.task.server.web.service.RetryDeadLetterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 死信队列接口
 *
 * @author: opensnail
 * @date : 2022-02-28 15:38
 */
@RestController
@RequestMapping("/retry-dead-letter")
public class RetryDeadLetterController {
    private final RetryDeadLetterService retryDeadLetterService;

    public RetryDeadLetterController(RetryDeadLetterService retryDeadLetterService) {
        this.retryDeadLetterService = retryDeadLetterService;
    }

    @LoginRequired
    @GetMapping("list")
    public PageResult<List<RetryDeadLetterResponseVO>> getRetryDeadLetterPage(RetryDeadLetterQueryVO queryVO) {
        return retryDeadLetterService.getRetryDeadLetterPage(queryVO);
    }

    @LoginRequired
    @GetMapping("{id}")
    public RetryDeadLetterResponseVO getRetryDeadLetterById(@RequestParam("groupName") String groupName,
                                                            @PathVariable("id") Long id) {
        return retryDeadLetterService.getRetryDeadLetterById(groupName, id);
    }

    @LoginRequired
    @PostMapping("/batch/rollback")
    public int rollback(@RequestBody @Validated BatchRollBackRetryDeadLetterVO rollBackRetryDeadLetterVO) {
        return retryDeadLetterService.rollback(rollBackRetryDeadLetterVO);
    }

    @LoginRequired
    @DeleteMapping("/batch")
    public boolean batchDelete(@RequestBody @Validated BatchDeleteRetryDeadLetterVO deadLetterVO) {
        return retryDeadLetterService.batchDelete(deadLetterVO);
    }
}
