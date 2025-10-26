package com.wingflare.engine.task.server.web.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.wingflare.engine.task.server.common.enums.SyetemTaskTypeEnum;
import com.wingflare.engine.task.server.service.service.impl.AbstractJobBatchService;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.JobBatchQueryVO;
import com.wingflare.engine.task.server.web.model.request.JobBatchResponseWebVO;
import com.wingflare.engine.task.server.web.model.request.UserSessionVO;
import com.wingflare.engine.task.server.web.service.JobWebBatchService;
import com.wingflare.engine.task.server.web.service.convert.JobBatchResponseVOConverter;
import com.wingflare.engine.task.server.web.service.handler.JobHandler;
import com.wingflare.engine.task.server.web.util.UserSessionUtils;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.JobBatchResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskBatchMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTaskBatch;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;


/**
 * @author: opensnail
 * @date : 2023-10-12 09:55
 * @since ：2.4.0
 */
@Service("jobWebBatchCommonService")
public class JobWebBatchServiceImpl extends AbstractJobBatchService implements JobWebBatchService {
    private final JobTaskBatchMapper jobTaskBatchMapper;
    private final JobHandler jobHandler;

    public JobWebBatchServiceImpl(JobTaskBatchMapper jobTaskBatchMapper, JobHandler jobHandler) {
        this.jobTaskBatchMapper = jobTaskBatchMapper;
        this.jobHandler = jobHandler;
    }

    @Override
    public PageResult<List<JobBatchResponseWebVO>> getJobBatchPage(final JobBatchQueryVO queryVO) {
        PageDTO<JobTaskBatch> pageDTO = new PageDTO<>(queryVO.getPage(), queryVO.getSize());

        UserSessionVO userSessionVO = UserSessionUtils.currentUserSession();
        List<String> groupNames = UserSessionUtils.getGroupNames(queryVO.getGroupName());

        // 如果当前用户为普通用户, 且计算后组名条件为空, 不能查询
        if (userSessionVO.isUser() && CollUtil.isEmpty(groupNames)) {
            return new PageResult<>(pageDTO, Collections.emptyList());
        }

        QueryWrapper<JobTaskBatch> wrapper = new QueryWrapper<JobTaskBatch>()
                .eq("batch.namespace_id", userSessionVO.getNamespaceId())
                .eq("batch.system_task_type", SyetemTaskTypeEnum.JOB.getType())
                .eq(queryVO.getJobId() != null, "batch.job_id", queryVO.getJobId())
                .in(CollUtil.isNotEmpty(groupNames), "batch.group_name", groupNames)
                .in(ObjUtil.isNotEmpty(queryVO.getTaskBatchStatus()), "batch.task_batch_status", queryVO.getTaskBatchStatus())
                .between(ObjUtil.isAllNotEmpty(queryVO.getStartDt(), queryVO.getEndDt()),
                        "batch.create_dt", queryVO.getStartDt(), queryVO.getEndDt())
                .eq("batch.deleted", 0)
                .orderByDesc("batch.id");
        List<JobBatchResponseDO> batchResponseDOList = jobTaskBatchMapper.selectJobBatchPageList(pageDTO, wrapper);
        List<JobBatchResponseWebVO> batchResponseVOList = JobBatchResponseVOConverter.INSTANCE.convertList(
                batchResponseDOList);
        return new PageResult<>(pageDTO, batchResponseVOList);
    }

    @Override
    public boolean stop(Long taskBatchId) {
        return jobHandler.stop(taskBatchId);
    }

    @Override
    @Transactional
    public Boolean retry(Long taskBatchId) {
        return jobHandler.retry(taskBatchId);
    }

    @Override
    public Boolean deleteJobBatchByIds(Set<Long> ids) {
        String namespaceId = UserSessionUtils.currentUserSession().getNamespaceId();
        jobHandler.deleteJobTaskBatchByIds(ids, namespaceId);
        return Boolean.TRUE;
    }
}
