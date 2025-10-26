package com.wingflare.engine.task.server.web.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.JobExecutorQueryVO;
import com.wingflare.engine.task.server.web.model.request.UserSessionVO;
import com.wingflare.engine.task.server.web.service.JobExecutorService;
import com.wingflare.engine.task.server.web.util.UserSessionUtils;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobExecutorMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobExecutor;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


/**
 * @Author：srzou
 * @Package：com.wingflare.engine.task.server.web.service.impl
 * @Project：wingflare-task
 * @Date：2025/6/3 13:27
 * @Filename：JobExecutorServiceImpl
 */
@Service
public class JobExecutorServiceImpl implements JobExecutorService {
    private final JobExecutorMapper jobExecutorMapper;

    public JobExecutorServiceImpl(JobExecutorMapper jobExecutorMapper) {
        this.jobExecutorMapper = jobExecutorMapper;
    }

    @Override
    public PageResult<List<JobExecutor>> getJobExecutorPage(JobExecutorQueryVO jobQueryVO) {
        PageDTO<JobExecutor> pageDTO = new PageDTO<>(jobQueryVO.getPage(), jobQueryVO.getSize());
        UserSessionVO userSessionVO = UserSessionUtils.currentUserSession();
        List<String> groupNames = UserSessionUtils.getGroupNames(jobQueryVO.getGroupName());

        PageDTO<JobExecutor> selectPage = jobExecutorMapper.selectPage(pageDTO,
                new LambdaQueryWrapper<JobExecutor>()
                        .eq(JobExecutor::getNamespaceId, userSessionVO.getNamespaceId())
                        .eq(StrUtil.isNotBlank(jobQueryVO.getExecutorType()), JobExecutor::getExecutorType, jobQueryVO.getExecutorType())
                        .in(CollUtil.isNotEmpty(groupNames), JobExecutor::getGroupName, groupNames)
                        .like(StrUtil.isNotBlank(jobQueryVO.getExecutorInfo()), JobExecutor::getExecutorInfo, StrUtil.trim(jobQueryVO.getExecutorInfo()))
                        .orderByAsc(JobExecutor::getId));

        return new PageResult<>(pageDTO, selectPage.getRecords());
    }

    @Override
    public JobExecutor getJobExecutorDetail(Long id) {
        return jobExecutorMapper.selectById(id);
    }

    @Override
    public Set<String> getJobExecutorList(JobExecutorQueryVO jobQueryVO) {
        UserSessionVO userSessionVO = UserSessionUtils.currentUserSession();
        List<String> groupNames = UserSessionUtils.getGroupNames(jobQueryVO.getGroupName());

            return StreamUtils.toSet(jobExecutorMapper.selectList(
                    new LambdaQueryWrapper<JobExecutor>()
                            .select(JobExecutor::getExecutorInfo)
                            .eq(JobExecutor::getNamespaceId, userSessionVO.getNamespaceId())
                            .eq(StrUtil.isNotBlank(jobQueryVO.getExecutorType()), JobExecutor::getExecutorType, jobQueryVO.getExecutorType())
                            .in(CollUtil.isNotEmpty(groupNames), JobExecutor::getGroupName, groupNames)
                            .like(StrUtil.isNotBlank(jobQueryVO.getExecutorInfo()), JobExecutor::getExecutorInfo, StrUtil.trim(jobQueryVO.getExecutorInfo()))
                            .orderByDesc(JobExecutor::getId)), JobExecutor::getExecutorInfo);
    }

    @Override
    @Transactional
    public Boolean deleteJobExecutorByIds(Set<Long> ids) {
        String namespaceId = UserSessionUtils.currentUserSession().getNamespaceId();
        Assert.isTrue(ids.size() == jobExecutorMapper.delete(
                new LambdaQueryWrapper<JobExecutor>()
                        .eq(JobExecutor::getNamespaceId, namespaceId)
                        .in(JobExecutor::getId, ids)
        ), () -> new TaskServerException("Failed to delete job executor"));
        return Boolean.TRUE;
    }
}
