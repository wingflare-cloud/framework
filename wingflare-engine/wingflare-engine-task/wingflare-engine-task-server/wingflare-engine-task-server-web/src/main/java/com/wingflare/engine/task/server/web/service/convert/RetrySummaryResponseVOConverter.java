package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.response.DashboardCardResponseVO.RetryTask;
import com.wingflare.task.datasource.template.persistence.dataobject.DashboardCardResponseDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author zhengweilin
 * @version 2.6.0
 * @date 2023/11/24
 */
@Mapper
public interface RetrySummaryResponseVOConverter {

    RetrySummaryResponseVOConverter INSTANCE = Mappers.getMapper(RetrySummaryResponseVOConverter.class);

    RetryTask convert(DashboardCardResponseDO.RetryTask retryTask);
}
