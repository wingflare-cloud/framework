package com.wingflare.task.datasource.template.persistence.mapper;

import com.wingflare.task.datasource.template.persistence.po.RetryDeadLetter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RetryDeadLetterMapper extends BaseMapper<RetryDeadLetter> {

    int insertBatch(@Param("list") List<RetryDeadLetter> list);

}
