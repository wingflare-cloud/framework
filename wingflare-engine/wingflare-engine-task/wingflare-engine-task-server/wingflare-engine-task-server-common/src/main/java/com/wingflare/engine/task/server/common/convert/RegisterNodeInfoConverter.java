package com.wingflare.engine.task.server.common.convert;

import com.wingflare.engine.task.server.common.dto.RegisterNodeInfo;
import com.wingflare.engine.task.datasource.template.persistence.po.ServerNode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: opensnail
 * @date : 2023-06-09 11:07
 * @since 1.6.0
 */
@Mapper
public interface RegisterNodeInfoConverter {
    RegisterNodeInfoConverter INSTANCE = Mappers.getMapper(RegisterNodeInfoConverter.class);

    RegisterNodeInfo toRegisterNodeInfo(ServerNode serverNode);
}
