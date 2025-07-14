package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.IdentityDO;
import com.wingflare.facade.module.user.bo.IdentityBO;
import com.wingflare.facade.module.user.dto.IdentityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * IdentityConvert
 * 
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
@Mapper
public interface IdentityConvert extends com.wingflare.facade.module.user.convert.IdentityConvert
{
	IdentityConvert convert = Mappers.getMapper(IdentityConvert.class);

	IdentityBO doToBo(IdentityDO identityDo);

	IdentityDTO doToDto(IdentityDO identityDo);

	IdentityDO boToDo(IdentityBO identityBo);

	IdentityDO dtoToDo(IdentityDTO identityDto);

	List<IdentityBO> doToBoList(List<IdentityDO> identityDOList);

	List<IdentityDTO> doToDtoList(List<IdentityDO> identityDOList);

	List<IdentityDO> boToDoList(List<IdentityBO> identityBOList);

	List<IdentityDO> dtoToDoList(List<IdentityDTO> identityDTOList);
}
