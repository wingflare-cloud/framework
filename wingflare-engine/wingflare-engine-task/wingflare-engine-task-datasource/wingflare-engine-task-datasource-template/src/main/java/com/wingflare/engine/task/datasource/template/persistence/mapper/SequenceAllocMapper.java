package com.wingflare.engine.task.datasource.template.persistence.mapper;

import com.wingflare.engine.task.datasource.template.persistence.po.SequenceAlloc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 号段模式序号ID分配表 Mapper 接口
 * </p>
 *
 * @author opensnail
 * @date 2023-05-05
 * @since 1.2.0
 */
@Mapper
public interface SequenceAllocMapper extends BaseMapper<SequenceAlloc> {

}
