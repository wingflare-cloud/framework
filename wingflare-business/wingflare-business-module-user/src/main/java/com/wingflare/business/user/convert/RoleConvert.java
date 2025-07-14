package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.RoleDO;
import com.wingflare.facade.module.user.bo.RoleBO;
import com.wingflare.facade.module.user.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * RoleConvert
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
@Mapper
public interface RoleConvert extends com.wingflare.facade.module.user.convert.RoleConvert
{
	RoleConvert convert = Mappers.getMapper(RoleConvert.class);

	RoleBO doToBo(RoleDO roleDo);

	RoleDTO doToDto(RoleDO roleDo);

	RoleDO boToDo(RoleBO roleBo);

	RoleDO dtoToDo(RoleDTO roleDto);

	List<RoleBO> doToBoList(List<RoleDO> roleDOList);

	List<RoleDTO> doToDtoList(List<RoleDO> roleDOList);

	List<RoleDO> boToDoList(List<RoleBO> roleBOList);

	List<RoleDO> dtoToDoList(List<RoleDTO> roleDTOList);
}
