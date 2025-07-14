package com.wingflare.business.user.convert;

import com.wingflare.business.user.db.UserRoleDO;
import com.wingflare.facade.module.user.bo.UserRoleBO;
import com.wingflare.facade.module.user.dto.UserRoleDTO;
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

    UserRoleBO doToBo(UserRoleDO userRoleDo);

    UserRoleDTO doToDto(UserRoleDO userRoleDo);

    UserRoleDO boToDo(UserRoleBO userRoleBo);

    UserRoleDO dtoToDo(UserRoleDTO userRoleDto);

    List<UserRoleBO> doToBoList(List<UserRoleDO> userRoleDOList);

    List<UserRoleDTO> doToDtoList(List<UserRoleDO> userRoleDOList);

    List<UserRoleDO> boToDoList(List<UserRoleBO> userRoleBOList);

    List<UserRoleDO> dtoToDoList(List<UserRoleDTO> userRoleDTOList);

}
