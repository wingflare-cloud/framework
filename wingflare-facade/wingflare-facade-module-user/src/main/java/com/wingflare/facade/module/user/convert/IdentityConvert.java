package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.IdentityBo;
import com.wingflare.facade.module.user.dto.IdentityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * IdentityConvert
 * 
 * @author naizui_ycx
 * @date Sun Apr 02 10:29:44 CST 2023
 */
@Mapper
public interface IdentityConvert
{
	IdentityConvert convert = Mappers.getMapper(IdentityConvert.class);

	IdentityBo dtoToBo(IdentityDto identityDto);

	IdentityDto boToDto(IdentityBo identityBo);

	List<IdentityBo> dtoToBoList(List<IdentityDto> identityDtoList);

	List<IdentityDto> boToDtoList(List<IdentityBo> identityBoList);
}
