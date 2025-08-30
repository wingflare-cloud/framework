package com.wingflare.task.datasource.template.persistence.mapper;

import com.wingflare.task.datasource.template.persistence.dataobject.JobBatchResponseDO;
import com.wingflare.task.datasource.template.persistence.dataobject.JobBatchSummaryResponseDO;
import com.wingflare.task.datasource.template.persistence.po.JobTaskBatch;
import com.wingflare.task.datasource.template.persistence.po.WorkflowTaskBatch;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 调度任务 Mapper 接口
 * </p>
 *
 * @author opensnail
 * @since 2023-09-24
 */
@Mapper
public interface JobTaskBatchMapper extends BaseMapper<JobTaskBatch> {

    List<JobBatchResponseDO> selectJobBatchPageList(IPage<JobTaskBatch> iPage, @Param("ew") Wrapper<JobTaskBatch> wrapper);

    List<JobBatchResponseDO> selectJobBatchListByIds(@Param("ew") Wrapper<JobTaskBatch> wrapper);

    List<JobBatchSummaryResponseDO> selectJobBatchSummaryList(@Param("ew") Wrapper<JobTaskBatch> wrapper);

    List<JobBatchSummaryResponseDO> selectWorkflowTaskBatchSummaryList(@Param("ew") Wrapper<WorkflowTaskBatch> wrapper);
}
