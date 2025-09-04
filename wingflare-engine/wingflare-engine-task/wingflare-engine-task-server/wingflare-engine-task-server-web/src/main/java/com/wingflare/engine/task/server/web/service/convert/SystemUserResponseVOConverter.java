package com.wingflare.engine.task.server.web.service.convert;

import com.wingflare.engine.task.server.web.model.request.UserSessionVO;
import com.wingflare.engine.task.server.web.model.response.SystemUserResponseVO;
import com.wingflare.engine.task.datasource.template.persistence.po.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author opensnail
 * @date 2022-03-05
 * @since 1.0.0
 */
@Mapper
public interface SystemUserResponseVOConverter {

    SystemUserResponseVOConverter INSTANCE = Mappers.getMapper(SystemUserResponseVOConverter.class);

    SystemUserResponseVO convert(UserSessionVO systemUser);

    SystemUserResponseVO convert(SystemUser systemUser);

    List<SystemUserResponseVO> convertList(List<SystemUser> systemUsers);
}
