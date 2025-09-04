package com.wingflare.engine.task.server.web.service;

import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.*;
import com.wingflare.engine.task.datasource.template.persistence.po.JobExecutor;

import java.util.List;
import java.util.Set;

/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.web.service
 * @Project：wingflare-task
 * @Date：2025/6/3 13:19
 * @Filename：JobExecutorService
 */
public interface JobExecutorService {
    PageResult<List<JobExecutor>> getJobExecutorPage(JobExecutorQueryVO jobQueryVO);

    JobExecutor getJobExecutorDetail(Long id);

    Set<String> getJobExecutorList(JobExecutorQueryVO jobQueryVO);

    Boolean deleteJobExecutorByIds(Set<Long> ids);
}
