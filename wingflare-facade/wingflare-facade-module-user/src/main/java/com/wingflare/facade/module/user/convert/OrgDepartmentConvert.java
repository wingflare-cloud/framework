package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.OrgDepartmentBO;
import com.wingflare.facade.module.user.dto.OrgDepartmentDTO;
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

	OrgDepartmentBO dtoToBo(OrgDepartmentDTO orgDepartmentDto);

	OrgDepartmentDTO boToDto(OrgDepartmentBO orgDepartmentBo);

	List<OrgDepartmentBO> dtoToBoList(List<OrgDepartmentDTO> orgDepartmentDTOList);

	List<OrgDepartmentDTO> boToDtoList(List<OrgDepartmentBO> orgDepartmentBOList);
}
