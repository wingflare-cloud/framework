package com.wingflare.facade.module.user.convert;


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
public interface RoleConvert
{
	RoleConvert convert = Mappers.getMapper(RoleConvert.class);

	RoleBo dtoToBo(RoleDto roleDto);

	RoleDto boToDto(RoleBo roleBo);

	List<RoleBo> dtoToBoList(List<RoleDto> roleDtoList);

	List<RoleDto> boToDtoList(List<RoleBo> roleBoList);
}
