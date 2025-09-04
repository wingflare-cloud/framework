package com.wingflare.engine.task.datasource.template.persistence.mapper;

import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardCardResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardLineResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardRetryLineResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySceneConfig;
import com.wingflare.engine.task.datasource.template.persistence.po.RetrySummary;
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
public interface RetrySummaryMapper extends BaseMapper<RetrySummary> {

    int insertBatch(@Param("list") List<RetrySummary> list);

    int updateBatch(@Param("list") List<RetrySummary> list);

    DashboardCardResponseDO.RetryTask selectRetryTask(@Param("ew") Wrapper<RetrySummary> wrapper);

    List<DashboardCardResponseDO.RetryTask> selectRetryTaskBarList(@Param("ew") Wrapper<RetrySummary> wrapper);

    IPage<DashboardRetryLineResponseDO.Task> selectRetryTaskList(@Param("ew") Wrapper<RetrySceneConfig> wrapper, Page<Object> page);

    long selectRetryTaskListCount(@Param("ew") Wrapper<RetrySceneConfig> wrapper);

    List<DashboardLineResponseDO> selectRetryLineList(@Param("dateFormat") String dateFormat, @Param("ew") Wrapper<RetrySummary> wrapper);

    List<DashboardRetryLineResponseDO.Rank> selectDashboardRankList(@Param("ew") Wrapper<RetrySummary> wrapper);

}
