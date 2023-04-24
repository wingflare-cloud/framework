package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.RoleGroupBo;
import com.wingflare.facade.module.user.dto.RoleGroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * RoleGroupConvert
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
@Mapper
public interface RoleGroupConvert
{
	RoleGroupConvert convert = Mappers.getMapper(RoleGroupConvert.class);

	RoleGroupBo dtoToBo(RoleGroupDto roleGroupDto);

	RoleGroupDto boToDto(RoleGroupBo roleGroupBo);

	List<RoleGroupBo> dtoToBoList(List<RoleGroupDto> roleGroupDtoList);

	List<RoleGroupDto> boToDtoList(List<RoleGroupBo> roleGroupBoList);
}
