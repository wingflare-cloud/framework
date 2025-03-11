package com.wingflare.business.user.convert;

import com.wingflare.business.user.db.UserRoleDo;
import com.wingflare.facade.module.user.bo.UserRoleBo;
import com.wingflare.facade.module.user.dto.UserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * 系统用户角色表 类型转换器
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-10
 */
@Mapper
public interface UserRoleConvert extends com.wingflare.facade.module.user.convert.UserRoleConvert {

    UserRoleConvert convert = Mappers.getMapper(UserRoleConvert.class);

    UserRoleBo doToBo(UserRoleDo userRoleDo);

    UserRoleDto doToDto(UserRoleDo userRoleDo);

    UserRoleDo boToDo(UserRoleBo userRoleBo);

    UserRoleDo dtoToDo(UserRoleDto userRoleDto);

    List<UserRoleBo> doToBoList(List<UserRoleDo> userRoleDoList);

    List<UserRoleDto> doToDtoList(List<UserRoleDo> userRoleDoList);

    List<UserRoleDo> boToDoList(List<UserRoleBo> userRoleBoList);

    List<UserRoleDo> dtoToDoList(List<UserRoleDto> userRoleDtoList);

}
