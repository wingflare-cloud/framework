package com.wingflare.engine.task.server.web.controller;


import com.wingflare.api.security.annotation.RequiresLogin;
import com.wingflare.api.security.annotation.RequiresPermissions;
import com.wingflare.engine.task.common.model.request.base.JobTriggerRequest;
import com.wingflare.engine.task.server.service.service.JobService;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.server.web.model.response.JobResponseWebVO;
import com.wingflare.engine.task.server.web.service.JobWebService;
import com.wingflare.engine.task.server.web.service.impl.JobWebServiceImpl;
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

import java.util.List;
import java.util.Set;


/**
 * @author opensnail
 * @date 2023-10-11 22:18:29
 * @since 2.4.0
 */
@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;
    private final JobWebService jobWebService;

    public JobController(JobWebServiceImpl jobService, JobWebService jobWebService) {
        this.jobService = jobService;
        this.jobWebService = jobWebService;
    }

    @GetMapping("/page/list")
    @RequiresLogin
    @RequiresPermissions("task.job.list")
    public PageResult<List<JobResponseWebVO>> getJobPage(JobQueryVO jobQueryVO) {
        return jobWebService.getJobPage(jobQueryVO);
    }

    @GetMapping("/list")
    @RequiresLogin
    @RequiresPermissions("task.job.list")
    public List<JobResponseWebVO> getJobList(@RequestParam("groupName") String groupName) {
        return jobWebService.getJobList(groupName);
    }

    @GetMapping("{id}")
    @RequiresLogin
    @RequiresPermissions("task.job.detail")
    public JobResponseWebVO getJobDetail(@PathVariable("id") Long id) {
        return jobService.getJobById(id, JobResponseWebVO.class);
    }

    @PostMapping
    @RequiresLogin
    @RequiresPermissions("task.job.save")
    public Long saveJob(@RequestBody @Validated JobRequestWebVO jobRequestWebVO) {
        return jobService.addJob(jobRequestWebVO);
    }

    @PutMapping
    @RequiresLogin
    @RequiresPermissions("task.job.update")
    public Boolean updateJob(@RequestBody @Validated JobRequestWebVO jobRequestWebVO) {
        return jobService.updateJob(jobRequestWebVO);
    }

    @PutMapping("/status")
    @RequiresLogin
    @RequiresPermissions("task.job.updateStatus")
    public Boolean updateJobStatus(@RequestBody @Validated StatusUpdateRequestWebVO jobRequestVO) {
        return jobService.updateJobStatus(jobRequestVO);
    }

    @DeleteMapping("/ids")
    @RequiresLogin
    @RequiresPermissions("task.job.batchDelete")
    public Boolean deleteJobById(@RequestBody @NotEmpty(message = "ids cannot be null") Set<Long> ids) {
        return jobService.deleteJobByIds(ids);
    }

    @GetMapping("/cron")
    @RequiresLogin
    @RequiresPermissions("task.job.cron")
    public List<String> getTimeByCron(@RequestParam("cron") String cron) {
        return jobWebService.getTimeByCron(cron);
    }

    @GetMapping("/job-name/list")
    @RequiresLogin
    @RequiresPermissions("task.job.list")
    public List<JobResponseWebVO> getJobNameList(
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "jobId", required = false) Long jobId,
            @RequestParam(value = "groupName", required = false) String groupName
    ) {
        return jobWebService.getJobNameList(keywords, jobId, groupName);
    }

    @PostMapping("/trigger")
    @RequiresLogin
    @RequiresPermissions("task.job.trigger")
    public Boolean trigger(@RequestBody @Validated JobTriggerVO jobTrigger) {
        JobTriggerRequest triggerDTO = new JobTriggerRequest();
        triggerDTO.setJobId(jobTrigger.getJobId());
        triggerDTO.setTmpArgsStr(jobTrigger.getTmpArgsStr());
        return jobService.trigger(triggerDTO);
    }

}
