package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.server.service.service.JobBatchService;
import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.JobBatchQueryVO;
import com.wingflare.engine.task.server.web.model.request.JobBatchResponseWebVO;
import com.wingflare.engine.task.server.web.service.JobWebBatchService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * @author: opensnail
 * @date : 2023-10-12 09:52
 * @since ： 2.4.0
 */
@RestController
@RequestMapping("/job/batch")
public class JobBatchController {
    @Qualifier("jobWebBatchCommonService")
    private final JobBatchService jobBatchService;
    private final JobWebBatchService jobWebBatchService;

    public JobBatchController(JobBatchService jobBatchService, JobWebBatchService jobWebBatchService) {
        this.jobBatchService = jobBatchService;
        this.jobWebBatchService = jobWebBatchService;
    }

    @GetMapping("/list")
    @LoginRequired
    public PageResult<List<JobBatchResponseWebVO>> getJobBatchPage(JobBatchQueryVO jobQueryVO) {
        return jobWebBatchService.getJobBatchPage(jobQueryVO);
    }

    @GetMapping("{id}")
    @LoginRequired
    public JobBatchResponseWebVO getJobBatchDetail(@PathVariable("id") Long id) {
        return jobBatchService.getJobBatchById(id, JobBatchResponseWebVO.class);
    }

    @PostMapping("/stop/{taskBatchId}")
    @LoginRequired
    public Boolean stop(@PathVariable("taskBatchId") Long taskBatchId) {
        return jobWebBatchService.stop(taskBatchId);
    }


    @PostMapping("/retry/{taskBatchId}")
    @LoginRequired
    public Boolean retry(@PathVariable("taskBatchId") Long taskBatchId) {
        return jobWebBatchService.retry(taskBatchId);
    }

    @DeleteMapping("/ids")
    @LoginRequired
    public Boolean deleteJobBatchByIds(@RequestBody
                                       @NotEmpty(message = "ids cannot be null")
                                       @Size(max = 100, message = "Maximum {max} deletions")
                                       Set<Long> ids) {
        return jobWebBatchService.deleteJobBatchByIds(ids);
    }
}
