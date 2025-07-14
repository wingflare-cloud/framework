package com.wingflare.business.base.convert;


import com.wingflare.business.base.db.DictDO;
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
public interface DictConvert extends com.wingflare.facade.module.base.convert.DictConvert
{
	DictConvert convert = Mappers.getMapper(DictConvert.class);

	DictBO doToBo(DictDO dictDo);

	DictDTO doToDto(DictDO dictDo);

	DictDO boToDo(DictBO dictBo);

	DictDO dtoToDo(DictDTO dictDto);

	SimpleDictDTO doToSimpleDto(DictDO dictDo);

	List<DictBO> doToBoList(List<DictDO> dictDOList);

	List<DictDTO> doToDtoList(List<DictDO> dictDOList);

	List<DictDO> boToDoList(List<DictBO> dictBOList);

	List<DictDO> dtoToDoList(List<DictDTO> dictDTOList);
}
