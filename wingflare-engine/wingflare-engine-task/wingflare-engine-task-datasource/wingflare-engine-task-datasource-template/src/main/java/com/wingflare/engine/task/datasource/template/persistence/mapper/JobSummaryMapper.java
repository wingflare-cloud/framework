package com.wingflare.engine.task.datasource.template.persistence.mapper;

import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardCardResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardLineResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardRetryLineResponseDO.Rank;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardRetryLineResponseDO.Task;
import com.wingflare.engine.task.datasource.template.persistence.po.Job;
import com.wingflare.engine.task.datasource.template.persistence.po.JobSummary;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhengweilin
 * @version 2.6.0
 * @date 2023/11/22
 */
@Mapper
public interface JobSummaryMapper extends BaseMapper<JobSummary> {

    int insertBatch(@Param("list") List<JobSummary> list);

    int updateBatch(@Param("list") List<JobSummary> list);

    IPage<Task> selectJobTaskList(@Param("ew") Wrapper<Job> wrapper, Page<Object> page);

    // jobTaskList 自定义 countStatement
    long selectJobTaskListCount(@Param("ew") Wrapper<Job> wrapper);

    IPage<Task> selectWorkflowTaskList(@Param("ew") Wrapper<Job> wrapper, Page<Object> page);

    // workflowTaskList 自定义 countStatement
    long selectWorkflowTaskListCount(@Param("ew") Wrapper<Job> wrapper);

    List<DashboardLineResponseDO> selectJobLineList(@Param("dateFormat") String dateFormat, @Param("ew") Wrapper<JobSummary> wrapper);

    List<Rank> selectDashboardRankList(@Param("systemTaskType") Integer systemTaskType, @Param("ew") Wrapper<JobSummary> wrapper);

    DashboardCardResponseDO.JobTask selectJobTask(@Param("ew") Wrapper<JobSummary> wrapper);

}
