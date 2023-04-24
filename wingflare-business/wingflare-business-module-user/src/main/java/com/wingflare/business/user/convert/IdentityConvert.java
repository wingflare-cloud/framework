package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.IdentityDo;
import com.wingflare.facade.module.user.bo.IdentityBo;
import com.wingflare.facade.module.user.dto.IdentityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * IdentityConvert
 * 
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
@Mapper
public interface IdentityConvert extends com.wingflare.facade.module.user.convert.IdentityConvert
{
	IdentityConvert convert = Mappers.getMapper(IdentityConvert.class);

	IdentityBo doToBo(IdentityDo identityDo);

	IdentityDto doToDto(IdentityDo identityDo);

	IdentityDo boToDo(IdentityBo identityBo);

	IdentityDo dtoToDo(IdentityDto identityDto);

	List<IdentityBo> doToBoList(List<IdentityDo> identityDoList);

	List<IdentityDto> doToDtoList(List<IdentityDo> identityDoList);

	List<IdentityDo> boToDoList(List<IdentityBo> identityBoList);

	List<IdentityDo> dtoToDoList(List<IdentityDto> identityDtoList);
}
