package com.wingflare.facade.module.user.convert;

import com.wingflare.facade.module.user.bo.UserRoleBo;
import com.wingflare.facade.module.user.dto.UserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 系统用户角色表 bo dto类型转换器
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
@Mapper
public interface UserRoleConvert {

    UserRoleConvert convert = Mappers.getMapper(UserRoleConvert.class);

    UserRoleBo dtoToBo(UserRoleDto userRoleDto);

    UserRoleDto boToDto(UserRoleBo userRoleBo);

    List<UserRoleBo> dtoToBoList(List<UserRoleDto> userRoleDtoList);

    List<UserRoleDto> boToDtoList(List<UserRoleBo> userRoleBoList);

}