package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.OrgBo;
import com.wingflare.facade.module.user.dto.OrgDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * OrgConvert
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
@Mapper
public interface OrgConvert
{
	OrgConvert convert = Mappers.getMapper(OrgConvert.class);

	OrgBo dtoToBo(OrgDto orgDto);

	OrgDto boToDto(OrgBo orgBo);

	List<OrgBo> dtoToBoList(List<OrgDto> orgDtoList);

	List<OrgDto> boToDtoList(List<OrgBo> orgBoList);
}
