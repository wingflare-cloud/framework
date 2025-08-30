package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.request.JobNotifyConfigRequestVO;
import com.wingflare.task.datasource.template.persistence.po.JobNotifyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author: zuoJunLin
 * @date : 2023-12-02 13:43
 */
@Mapper
public interface JobNotifyConfigConverter {

    JobNotifyConfigConverter INSTANCE = Mappers.getMapper(JobNotifyConfigConverter.class);

    JobNotifyConfig convert(JobNotifyConfigRequestVO jobNotifyConfigVO);
}
