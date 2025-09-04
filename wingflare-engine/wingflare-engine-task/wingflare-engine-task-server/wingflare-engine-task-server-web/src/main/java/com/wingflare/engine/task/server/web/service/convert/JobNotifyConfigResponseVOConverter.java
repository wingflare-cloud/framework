package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.response.JobNotifyConfigResponseVO;
import com.wingflare.engine.task.datasource.template.persistence.dataobject.JobNotifyConfigResponseDO;
import com.wingflare.engine.task.datasource.template.persistence.po.JobNotifyConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author: opensnail
 * @date : 2022-03-03 11:20
 */
@Mapper
public interface JobNotifyConfigResponseVOConverter {

    JobNotifyConfigResponseVOConverter INSTANCE = Mappers.getMapper(JobNotifyConfigResponseVOConverter.class);

    JobNotifyConfigResponseVO convert(JobNotifyConfig jobNotifyConfig);

    List<JobNotifyConfigResponseVO> convertList(List<JobNotifyConfigResponseDO> jobNotifyConfigs);
}
