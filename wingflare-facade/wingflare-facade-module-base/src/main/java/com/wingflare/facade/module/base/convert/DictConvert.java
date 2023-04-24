package com.wingflare.facade.module.base.convert;


import com.wingflare.facade.module.base.bo.DictBo;
import com.wingflare.facade.module.base.dto.DictDto;
import com.wingflare.facade.module.base.dto.SimpleDictDto;
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

	DictBo dtoToBo(DictDto dictDto);

	SimpleDictDto dtoToSimple(DictDto dictDto);

	DictDto boToDto(DictBo dictBo);

	List<DictBo> dtoToBoList(List<DictDto> dictDtoList);

	List<DictDto> boToDtoList(List<DictBo> dictBoList);
}
