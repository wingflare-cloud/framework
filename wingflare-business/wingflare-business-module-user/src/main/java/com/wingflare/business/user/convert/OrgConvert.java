package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.OrgDO;
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
public interface OrgConvert extends com.wingflare.facade.module.user.convert.OrgConvert
{
	OrgConvert convert = Mappers.getMapper(OrgConvert.class);

	OrgBO doToBo(OrgDO orgDo);

	OrgDTO doToDto(OrgDO orgDo);

	OrgDO boToDo(OrgBO orgBo);

	OrgDO dtoToDo(OrgDTO orgDto);

	List<OrgBO> doToBoList(List<OrgDO> orgDOList);

	List<OrgDTO> doToDtoList(List<OrgDO> orgDOList);

	List<OrgDO> boToDoList(List<OrgBO> orgBOList);

	List<OrgDO> dtoToDoList(List<OrgDTO> orgDTOList);
}
