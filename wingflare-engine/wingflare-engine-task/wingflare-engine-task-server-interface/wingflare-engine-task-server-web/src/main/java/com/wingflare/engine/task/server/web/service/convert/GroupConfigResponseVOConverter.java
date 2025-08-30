package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.response.GroupConfigResponseVO;
import com.wingflare.task.datasource.template.persistence.po.GroupConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author opensnail
 * @date 2022-02-27
 * @since 1.2.0
 */
@Mapper
public interface GroupConfigResponseVOConverter {

    GroupConfigResponseVOConverter INSTANCE = Mappers.getMapper(GroupConfigResponseVOConverter.class);

    GroupConfigResponseVO convert(GroupConfig groupConfig);

    List<GroupConfigResponseVO> convertList(List<GroupConfig> groupConfigs);
}
