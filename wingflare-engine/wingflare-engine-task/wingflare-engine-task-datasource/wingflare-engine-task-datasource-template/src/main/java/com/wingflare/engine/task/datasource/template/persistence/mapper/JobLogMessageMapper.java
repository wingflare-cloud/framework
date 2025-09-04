package com.wingflare.engine.task.datasource.template.persistence.mapper;

import com.wingflare.engine.task.datasource.template.persistence.po.JobLogMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 调度日志 Mapper 接口
 * </p>
 *
 * @author opensnail
 * @since 2023-09-24
 */
@Mapper
public interface JobLogMessageMapper extends BaseMapper<JobLogMessage> {

    int insertBatch(@Param("list") List<JobLogMessage> list);

}
