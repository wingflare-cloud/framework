package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.IdentityBO;
import com.wingflare.facade.module.user.dto.IdentityDTO;
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

	IdentityBO dtoToBo(IdentityDTO identityDto);

	IdentityDTO boToDto(IdentityBO identityBo);

	List<IdentityBO> dtoToBoList(List<IdentityDTO> identityDTOList);

	List<IdentityDTO> boToDtoList(List<IdentityBO> identityBOList);
}
