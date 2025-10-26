package com.wingflare.engine.task.server.web.service.convert;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author: xiaowoniu
 * @date : 2023-11-23 14:04
 * @since : 2.5.0
 */
@Mapper
public interface PermissionsResponseVOConverter {

    PermissionsResponseVOConverter INSTANCE = Mappers.getMapper(PermissionsResponseVOConverter.class);

}
