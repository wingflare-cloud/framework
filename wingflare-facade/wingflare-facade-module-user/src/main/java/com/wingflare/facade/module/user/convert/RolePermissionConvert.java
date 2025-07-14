package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.RolePermissionBO;
import com.wingflare.facade.module.user.dto.RolePermissionDTO;
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

	RolePermissionBO dtoToBo(RolePermissionDTO rolePermissionDto);

	RolePermissionDTO boToDto(RolePermissionBO rolePermissionBo);

	List<RolePermissionBO> dtoToBoList(List<RolePermissionDTO> rolePermissionDTOList);

	List<RolePermissionDTO> boToDtoList(List<RolePermissionBO> rolePermissionBOList);
}
