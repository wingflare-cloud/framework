package com.wingflare.engine.task.datasource.template.persistence.mapper;

import com.wingflare.engine.task.datasource.template.persistence.po.Workflow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工作流 Mapper 接口
 * </p>
 *
 * @author xiaowoniu
 * @since 2023-12-12
 * @since : 2.6.0
 */
@Mapper
public interface WorkflowMapper extends BaseMapper<Workflow> {

    int updateBatchNextTriggerAtById(@Param("list") List<Workflow> list);
}
