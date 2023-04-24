package com.wingflare.facade.module.user.convert;


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
public interface OrgDepartmentConvert
{
	OrgDepartmentConvert convert = Mappers.getMapper(OrgDepartmentConvert.class);

	OrgDepartmentBo dtoToBo(OrgDepartmentDto orgDepartmentDto);

	OrgDepartmentDto boToDto(OrgDepartmentBo orgDepartmentBo);

	List<OrgDepartmentBo> dtoToBoList(List<OrgDepartmentDto> orgDepartmentDtoList);

	List<OrgDepartmentDto> boToDtoList(List<OrgDepartmentBo> orgDepartmentBoList);
}
