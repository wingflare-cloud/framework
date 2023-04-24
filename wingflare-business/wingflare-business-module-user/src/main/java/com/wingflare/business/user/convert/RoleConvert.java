package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.RoleDo;
import com.wingflare.facade.module.user.bo.RoleBo;
import com.wingflare.facade.module.user.dto.RoleDto;
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

	RoleBo doToBo(RoleDo roleDo);

	RoleDto doToDto(RoleDo roleDo);

	RoleDo boToDo(RoleBo roleBo);

	RoleDo dtoToDo(RoleDto roleDto);

	List<RoleBo> doToBoList(List<RoleDo> roleDoList);

	List<RoleDto> doToDtoList(List<RoleDo> roleDoList);

	List<RoleDo> boToDoList(List<RoleBo> roleBoList);

	List<RoleDo> dtoToDoList(List<RoleDto> roleDtoList);
}
