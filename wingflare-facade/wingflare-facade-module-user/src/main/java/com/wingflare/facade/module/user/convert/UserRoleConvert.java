package com.wingflare.facade.module.user.convert;

import com.wingflare.facade.module.user.bo.UserRoleBO;
import com.wingflare.facade.module.user.dto.UserRoleDTO;
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

    UserRoleBO dtoToBo(UserRoleDTO userRoleDto);

    UserRoleDTO boToDto(UserRoleBO userRoleBo);

    List<UserRoleBO> dtoToBoList(List<UserRoleDTO> userRoleDTOList);

    List<UserRoleDTO> boToDtoList(List<UserRoleBO> userRoleBOList);

}