package com.wingflare.task.datasource.template.persistence.mapper;

import com.wingflare.task.datasource.template.persistence.dataobject.ActivePodQuantityResponseDO;
import com.wingflare.task.datasource.template.persistence.po.ServerNode;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ServerNodeMapper extends BaseMapper<ServerNode> {

    int insertBatch(@Param("list") List<ServerNode> list);

    int updateBatchExpireAt(@Param("list") List<ServerNode> list);

    List<ActivePodQuantityResponseDO> selectActivePodCount(@Param("ew") Wrapper<ServerNode> wrapper);

}
