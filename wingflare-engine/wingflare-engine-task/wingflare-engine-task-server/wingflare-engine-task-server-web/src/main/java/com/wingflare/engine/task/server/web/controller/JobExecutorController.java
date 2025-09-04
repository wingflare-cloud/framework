package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.JobExecutorQueryVO;
import com.wingflare.engine.task.server.web.service.JobExecutorService;
import com.wingflare.engine.task.datasource.template.persistence.po.JobExecutor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.web.controller
 * @Project：wingflare-task
 * @Date：2025/6/3 13:17
 * @Filename：JobExecutorController
 * @since 1.6.0
 */
@RestController
@RequestMapping("/job/executor")
public class JobExecutorController {
    @Resource
    private JobExecutorService jobExecutorService;

    @GetMapping("/page/list")
    @LoginRequired
    public PageResult<List<JobExecutor>> getJobPage(JobExecutorQueryVO executorQueryVO) {
        return jobExecutorService.getJobExecutorPage(executorQueryVO);
    }

    @GetMapping("/list")
    @LoginRequired
    public Set<String> getJobList(JobExecutorQueryVO executorQueryVO) {
        return jobExecutorService.getJobExecutorList(executorQueryVO);
    }

    @GetMapping("{id}")
    @LoginRequired
    public JobExecutor getJobExecutorsDetail(@PathVariable("id") Long id) {
        return jobExecutorService.getJobExecutorDetail(id);
    }

    @DeleteMapping("/ids")
    @LoginRequired
    public Boolean deleteJobExecutorsById(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return jobExecutorService.deleteJobExecutorByIds(ids);
    }
}
