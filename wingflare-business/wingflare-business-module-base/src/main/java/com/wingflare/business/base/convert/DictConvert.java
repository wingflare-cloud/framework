package com.wingflare.business.base.convert;


import com.wingflare.business.base.db.DictDo;
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
public interface DictConvert extends com.wingflare.facade.module.base.convert.DictConvert
{
	DictConvert convert = Mappers.getMapper(DictConvert.class);

	DictBo doToBo(DictDo dictDo);

	DictDto doToDto(DictDo dictDo);

	DictDo boToDo(DictBo dictBo);

	DictDo dtoToDo(DictDto dictDto);

	SimpleDictDto doToSimpleDto(DictDo dictDo);

	List<DictBo> doToBoList(List<DictDo> dictDoList);

	List<DictDto> doToDtoList(List<DictDo> dictDoList);

	List<DictDo> boToDoList(List<DictBo> dictBoList);

	List<DictDo> dtoToDoList(List<DictDto> dictDtoList);
}
