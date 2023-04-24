package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.OrgDo;
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
public interface OrgConvert extends com.wingflare.facade.module.user.convert.OrgConvert
{
	OrgConvert convert = Mappers.getMapper(OrgConvert.class);

	OrgBo doToBo(OrgDo orgDo);

	OrgDto doToDto(OrgDo orgDo);

	OrgDo boToDo(OrgBo orgBo);

	OrgDo dtoToDo(OrgDto orgDto);

	List<OrgBo> doToBoList(List<OrgDo> orgDoList);

	List<OrgDto> doToDtoList(List<OrgDo> orgDoList);

	List<OrgDo> boToDoList(List<OrgBo> orgBoList);

	List<OrgDo> dtoToDoList(List<OrgDto> orgDtoList);
}
