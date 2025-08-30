package com.wingflare.engine.task.server.web.controller;

import com.wingflare.engine.task.server.web.annotation.LoginRequired;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.JobTaskQueryVO;
import com.wingflare.engine.task.server.web.model.response.JobTaskResponseVO;
import com.wingflare.engine.task.server.web.service.JobTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2023-10-12 09:55
 * @since ： 2.4.0
 */
@RestController
@RequestMapping("/job/task")
public class JobTaskController {
    private final JobTaskService jobTaskService;

    public JobTaskController(JobTaskService jobTaskService) {
        this.jobTaskService = jobTaskService;
    }

    @GetMapping("/list")
    @LoginRequired
    public PageResult<List<JobTaskResponseVO>> getJobTaskPage(JobTaskQueryVO jobTaskQueryVO) {
        return jobTaskService.getJobTaskPage(jobTaskQueryVO);
    }

    @GetMapping("/tree/list")
    @LoginRequired
    public List<JobTaskResponseVO> getTreeJobTask(JobTaskQueryVO jobTaskQueryVO) {
        return jobTaskService.getTreeJobTask(jobTaskQueryVO);
    }

}
