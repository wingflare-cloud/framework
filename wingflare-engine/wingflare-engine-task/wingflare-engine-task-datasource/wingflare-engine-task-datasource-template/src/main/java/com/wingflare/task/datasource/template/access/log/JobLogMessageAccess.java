package com.wingflare.task.datasource.template.access.log;

import com.wingflare.task.datasource.template.access.JobLogAccess;
import com.wingflare.task.datasource.template.enums.DbTypeEnum;
import com.wingflare.task.datasource.template.enums.OperationTypeEnum;
import com.wingflare.task.datasource.template.persistence.dataobject.common.*;
import com.wingflare.task.datasource.template.persistence.dataobject.log.JobLogMessageDO;
import com.wingflare.task.datasource.template.persistence.dataobject.log.LogPageQueryDO;
import com.wingflare.task.datasource.template.persistence.mapper.JobLogMessageMapper;
import com.wingflare.task.datasource.template.persistence.po.JobLogMessage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import java.io.Serializable;
import java.util.List;

import static com.wingflare.task.datasource.template.utils.DbUtils.getDbType;

/**
 * <p>
 *
 * </p>
 *
 * @author opensnail
 * @date 2025-03-30
 */
public class JobLogMessageAccess implements JobLogAccess<JobLogMessageDO> {
    private final JobLogMessageMapper jobLogMessageMapper;

    public JobLogMessageAccess(JobLogMessageMapper jobLogMessageMapper) {
        this.jobLogMessageMapper = jobLogMessageMapper;
    }

    @Override
    public boolean supports(String operationType) {
        return DbTypeEnum.all().contains(getDbType()) && OperationTypeEnum.JOB_LOG.name().equals(operationType);
    }

    @Override
    public int insert(JobLogMessageDO jobLogMessageDO) {
        JobLogMessage jobLogMessage = LogConverter.INSTANCE.toJobLogMessage(jobLogMessageDO);
        return jobLogMessageMapper.insert(jobLogMessage);
    }

    @Override
    public int insertBatch(List<JobLogMessageDO> list) {
        List<JobLogMessage> jobLogMessages = LogConverter.INSTANCE.toJobLogMessages(list);
        return jobLogMessageMapper.insertBatch(jobLogMessages);
    }

    @Override
    public PageResponseDO<JobLogMessageDO> listPage(PageQueryDO queryDO) {
        LogPageQueryDO logPageQueryDO = (LogPageQueryDO) queryDO;

        PageDTO<JobLogMessage> selectPage = jobLogMessageMapper.selectPage(
                new PageDTO<>(queryDO.getPage(), logPageQueryDO.getSize(), logPageQueryDO.isSearchCount()),
                new LambdaQueryWrapper<JobLogMessage>()
                        .gt(JobLogMessage::getRealTime, logPageQueryDO.getStartRealTime())
                        .eq(JobLogMessage::getTaskBatchId, logPageQueryDO.getTaskBatchId())
                        .eq(JobLogMessage::getTaskId, logPageQueryDO.getTaskId())
                        .orderByAsc(JobLogMessage::getId)
                        .orderByAsc(JobLogMessage::getRealTime));
        List<JobLogMessage> records = selectPage.getRecords();

        PageResponseDO<JobLogMessageDO> responseDO = new PageResponseDO<>();
        responseDO.setPage(selectPage.getCurrent());
        responseDO.setSize(selectPage.getSize());
        responseDO.setTotal(selectPage.getTotal());
        responseDO.setRows(LogConverter.INSTANCE.toJobLogMessageDOList(records));
        return responseDO;
    }

    @Override
    public List<JobLogMessageDO> list(ListQueryDO queryDO) {
        return List.of();
    }

    @Override
    public JobLogMessageDO one(OneQueryDO query) {
        return null;
    }

    @Override
    public int update(JobLogMessageDO jobLogMessageDO, UpdateQueryDO query) {
        return 0;
    }

    @Override
    public int updateById(JobLogMessageDO jobLogMessageDO) {
        JobLogMessage jobLogMessage = LogConverter.INSTANCE.toJobLogMessage(jobLogMessageDO);
        return jobLogMessageMapper.updateById(jobLogMessage);
    }

    @Override
    public int deleteById(Serializable id) {
        return jobLogMessageMapper.deleteById(id);
    }

    @Override
    public int delete(DeleteQueryDO query) {
        return 0;
    }

    @Override
    public long count(LambdaQueryWrapper<JobLogMessageDO> query) {
        return 0;
    }

}
