package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.response.DashboardLineResponseVO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.DashboardLineResponseDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author opensnail
 * @date 2023-08-04 22:51:56
 * @since 2.2.0
 */
@Mapper
public interface DispatchQuantityResponseVOConverter {

    DispatchQuantityResponseVOConverter INSTANCE = Mappers.getMapper(DispatchQuantityResponseVOConverter.class);

    List<DashboardLineResponseVO> convertList(List<DashboardLineResponseDO> dashboardLineResponseDOList);
}
