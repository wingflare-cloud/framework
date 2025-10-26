package com.wingflare.engine.task.server.web.service.convert;


import com.wingflare.engine.task.server.web.model.request.UserSessionVO;
import com.wingflare.engine.task.server.web.model.response.SystemUserResponseVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author opensnail
 * @date 2022-03-05
 * @since 1.0.0
 */
@Mapper
public interface SystemUserResponseVOConverter {

    SystemUserResponseVOConverter INSTANCE = Mappers.getMapper(SystemUserResponseVOConverter.class);

    SystemUserResponseVO convert(UserSessionVO systemUser);

}
