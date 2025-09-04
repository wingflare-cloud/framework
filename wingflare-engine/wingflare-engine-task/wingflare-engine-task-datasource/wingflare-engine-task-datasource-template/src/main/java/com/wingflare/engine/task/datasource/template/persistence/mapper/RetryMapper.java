package com.wingflare.engine.task.datasource.template.persistence.mapper;

import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardRetryResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.po.Retry;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetryMapper extends BaseMapper<Retry> {

    int insertBatch(@Param("list") List<Retry> list);

    int updateBatchNextTriggerAtById(@Param("list") List<Retry> list);

    List<DashboardRetryResponseDO> selectRetrySummaryList(@Param("ew") Wrapper<Retry> wrapper);
}
