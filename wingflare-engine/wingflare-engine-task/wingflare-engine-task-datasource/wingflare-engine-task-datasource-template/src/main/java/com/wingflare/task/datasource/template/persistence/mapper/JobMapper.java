package com.wingflare.task.datasource.template.persistence.mapper;

import com.wingflare.task.datasource.template.persistence.po.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 任务信息 Mapper 接口
 * </p>
 *
 * @author opensnail
 * @since 2023-09-24
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {

    int updateBatchNextTriggerAtById(@Param("list") List<Job> list);

}
