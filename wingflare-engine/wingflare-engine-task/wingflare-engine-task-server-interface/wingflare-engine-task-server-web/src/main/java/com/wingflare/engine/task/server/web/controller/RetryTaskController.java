package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.RetryTaskQueryVO;
import com.wingflare.engine.task.server.web.model.response.RetryTaskResponseVO;
import com.wingflare.engine.task.server.web.service.RetryTaskService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
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
 * 重试日志接口
 *
 * @author opensnail
 * @date 2022-02-27
 */
@RestController
@RequestMapping("/retry-task")
public class RetryTaskController {

    @Autowired
    private RetryTaskService retryTaskService;

    @LoginRequired
    @GetMapping("list")
    public PageResult<List<RetryTaskResponseVO>> getRetryTaskPage(RetryTaskQueryVO queryVO) {
        return retryTaskService.getRetryTaskLogPage(queryVO);
    }

    @LoginRequired
    @GetMapping("{id}")
    public RetryTaskResponseVO getRetryTaskById(@PathVariable("id") Long id) {
        return retryTaskService.getRetryTaskById(id);
    }

    @LoginRequired
    @PostMapping("/stop/{id}")
    public Boolean stopById(@PathVariable("id") Long id) {
        return retryTaskService.stopById(id);
    }

    @LoginRequired
    @DeleteMapping("{id}")
    public Boolean deleteById(@PathVariable("id") Long id) {
        return retryTaskService.deleteById(id);
    }

    @LoginRequired
    @DeleteMapping("ids")
    public Boolean batchDelete(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return retryTaskService.batchDelete(ids);
    }


}
