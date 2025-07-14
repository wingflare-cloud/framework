package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.OrgDepartmentDO;
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
public interface OrgDepartmentConvert extends com.wingflare.facade.module.user.convert.OrgDepartmentConvert
{
	OrgDepartmentConvert convert = Mappers.getMapper(OrgDepartmentConvert.class);

	OrgDepartmentBO doToBo(OrgDepartmentDO orgDepartmentDo);

	OrgDepartmentDTO doToDto(OrgDepartmentDO orgDepartmentDo);

	OrgDepartmentDO boToDo(OrgDepartmentBO orgDepartmentBo);

	OrgDepartmentDO dtoToDo(OrgDepartmentDTO orgDepartmentDto);

	List<OrgDepartmentBO> doToBoList(List<OrgDepartmentDO> orgDepartmentDOList);

	List<OrgDepartmentDTO> doToDtoList(List<OrgDepartmentDO> orgDepartmentDOList);

	List<OrgDepartmentDO> boToDoList(List<OrgDepartmentBO> orgDepartmentBOList);

	List<OrgDepartmentDO> dtoToDoList(List<OrgDepartmentDTO> orgDepartmentDTOList);
}
