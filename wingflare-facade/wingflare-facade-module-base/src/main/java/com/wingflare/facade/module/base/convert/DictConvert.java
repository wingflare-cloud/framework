package com.wingflare.facade.module.base.convert;


import com.wingflare.facade.module.base.bo.DictBO;
import com.wingflare.facade.module.base.dto.DictDTO;
import com.wingflare.facade.module.base.dto.SimpleDictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * DictConvert
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
@Mapper
public interface DictConvert
{
	DictConvert convert = Mappers.getMapper(DictConvert.class);

	DictBO dtoToBo(DictDTO dictDto);

	SimpleDictDTO dtoToSimple(DictDTO dictDto);

	DictDTO boToDto(DictBO dictBo);

	List<DictBO> dtoToBoList(List<DictDTO> dictDTOList);

	List<DictDTO> boToDtoList(List<DictBO> dictBOList);
}
