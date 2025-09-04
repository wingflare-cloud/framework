package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.response.DashboardRetryLineResponseVO.Rank;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardRetryLineResponseDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-08-04 22:55:04
 * @since 2.2.0
 */
@Mapper
public interface SceneQuantityRankResponseVOConverter {

    SceneQuantityRankResponseVOConverter INSTANCE = Mappers.getMapper(SceneQuantityRankResponseVOConverter.class);

    List<Rank> convertList(List<DashboardRetryLineResponseDO.Rank> rankList);
}
