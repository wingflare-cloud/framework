package com.wingflare.facade.module.user.convert;


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
public interface RoleConvert
{
	RoleConvert convert = Mappers.getMapper(RoleConvert.class);

	RoleBO dtoToBo(RoleDTO roleDto);

	RoleDTO boToDto(RoleBO roleBo);

	List<RoleBO> dtoToBoList(List<RoleDTO> roleDTOList);

	List<RoleDTO> boToDtoList(List<RoleBO> roleBOList);
}
