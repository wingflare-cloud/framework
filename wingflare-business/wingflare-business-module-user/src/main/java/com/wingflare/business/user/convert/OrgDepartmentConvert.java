package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.OrgDepartmentDo;
import com.wingflare.facade.module.user.bo.OrgDepartmentBo;
import com.wingflare.facade.module.user.dto.OrgDepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * OrgDepartmentConvert
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
@Mapper
public interface OrgDepartmentConvert extends com.wingflare.facade.module.user.convert.OrgDepartmentConvert
{
	OrgDepartmentConvert convert = Mappers.getMapper(OrgDepartmentConvert.class);

	OrgDepartmentBo doToBo(OrgDepartmentDo orgDepartmentDo);

	OrgDepartmentDto doToDto(OrgDepartmentDo orgDepartmentDo);

	OrgDepartmentDo boToDo(OrgDepartmentBo orgDepartmentBo);

	OrgDepartmentDo dtoToDo(OrgDepartmentDto orgDepartmentDto);

	List<OrgDepartmentBo> doToBoList(List<OrgDepartmentDo> orgDepartmentDoList);

	List<OrgDepartmentDto> doToDtoList(List<OrgDepartmentDo> orgDepartmentDoList);

	List<OrgDepartmentDo> boToDoList(List<OrgDepartmentBo> orgDepartmentBoList);

	List<OrgDepartmentDo> dtoToDoList(List<OrgDepartmentDto> orgDepartmentDtoList);
}
