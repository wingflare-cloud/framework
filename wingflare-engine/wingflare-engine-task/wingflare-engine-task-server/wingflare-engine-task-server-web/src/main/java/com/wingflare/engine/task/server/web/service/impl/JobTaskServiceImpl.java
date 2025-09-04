package com.wingflare.engine.task.server.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wingflare.engine.task.common.core.util.StreamUtils;
import com.wingflare.engine.task.server.web.model.base.PageResult;
import com.wingflare.engine.task.server.web.model.request.JobTaskQueryVO;
import com.wingflare.engine.task.server.web.model.response.JobTaskResponseVO;
import com.wingflare.engine.task.server.web.service.JobTaskService;
import com.wingflare.engine.task.server.web.service.convert.JobTaskResponseVOConverter;
import com.wingflare.engine.task.datasource.template.persistence.mapper.JobTaskMapper;
import com.wingflare.engine.task.datasource.template.persistence.po.JobTask;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author: opensnail
 * @date : 2023-10-12 09:55
 * @since ：2.4.0
 */
@Service
public class JobTaskServiceImpl implements JobTaskService {
    private final JobTaskMapper jobTaskMapper;

    public JobTaskServiceImpl(JobTaskMapper jobTaskMapper) {
        this.jobTaskMapper = jobTaskMapper;
    }

    @Override
    public PageResult<List<JobTaskResponseVO>> getJobTaskPage(final JobTaskQueryVO queryVO) {

        PageDTO<JobTask> pageDTO = new PageDTO<>(queryVO.getPage(), queryVO.getSize());
        PageDTO<JobTask> selectPage = jobTaskMapper.selectPage(pageDTO,
                new LambdaQueryWrapper<JobTask>()
                        .eq(Objects.nonNull(queryVO.getJobId()), JobTask::getJobId, queryVO.getJobId())
                        .eq(Objects.nonNull(queryVO.getTaskBatchId()), JobTask::getTaskBatchId, queryVO.getTaskBatchId())
                        .eq(Objects.nonNull(queryVO.getTaskStatus()), JobTask::getTaskStatus, queryVO.getTaskStatus())
                        .eq(JobTask::getParentId, 0)
                        // SQLServer 分页必须 ORDER BY
                        .orderByAsc(JobTask::getId));

        List<JobTask> records = selectPage.getRecords();

        return new PageResult<>(pageDTO, convertJobTaskList(records));
    }

    @Override
    public List<JobTaskResponseVO> getTreeJobTask(final JobTaskQueryVO queryVO) {
        List<JobTask> taskList = jobTaskMapper.selectList(
                new LambdaQueryWrapper<JobTask>()
                        .eq(Objects.nonNull(queryVO.getParentId()), JobTask::getParentId, queryVO.getParentId())
                        .eq(Objects.nonNull(queryVO.getJobId()), JobTask::getJobId, queryVO.getJobId())
                        .eq(Objects.nonNull(queryVO.getTaskBatchId()), JobTask::getTaskBatchId, queryVO.getTaskBatchId())
                        // SQLServer 分页必须 ORDER BY
                        .orderByAsc(JobTask::getJobId));

        return convertJobTaskList(taskList);
    }

    private List<JobTaskResponseVO> convertJobTaskList(List<JobTask> taskList) {
        if (CollUtil.isEmpty(taskList)) {
            return new ArrayList<>();
        }

        List<JobTaskResponseVO> jobTaskResponseVOs = JobTaskResponseVOConverter.INSTANCE.convertList(
                taskList);

        Set<Long> parentIds = StreamUtils.toSet(jobTaskResponseVOs, JobTaskResponseVO::getId);
        List<JobTask> jobTasks = jobTaskMapper.selectList(new LambdaQueryWrapper<JobTask>()
                .select(JobTask::getParentId).in(JobTask::getParentId, parentIds));
        Set<Long> jobTaskParentIds = StreamUtils.toSet(jobTasks, JobTask::getParentId);
        jobTaskResponseVOs.forEach(jobTask -> jobTask.setChildNode(!jobTaskParentIds.contains(jobTask.getId())));

        return jobTaskResponseVOs;
    }

}
