package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.RolePermissionBo;
import com.wingflare.facade.module.user.dto.RolePermissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * RolePermissionConvert
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@Mapper
public interface RolePermissionConvert
{
	RolePermissionConvert convert = Mappers.getMapper(RolePermissionConvert.class);

	RolePermissionBo dtoToBo(RolePermissionDto rolePermissionDto);

	RolePermissionDto boToDto(RolePermissionBo rolePermissionBo);

	List<RolePermissionBo> dtoToBoList(List<RolePermissionDto> rolePermissionDtoList);

	List<RolePermissionDto> boToDtoList(List<RolePermissionBo> rolePermissionBoList);
}
