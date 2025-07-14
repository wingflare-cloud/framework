package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.OrgBO;
import com.wingflare.facade.module.user.dto.OrgDTO;
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

	OrgBO dtoToBo(OrgDTO orgDto);

	OrgDTO boToDto(OrgBO orgBo);

	List<OrgBO> dtoToBoList(List<OrgDTO> orgDTOList);

	List<OrgDTO> boToDtoList(List<OrgBO> orgBOList);
}
