package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.RolePermissionDO;
import com.wingflare.facade.module.user.bo.RolePermissionBO;
import com.wingflare.facade.module.user.dto.RolePermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * RolePermissionConvert
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@Mapper
public interface RolePermissionConvert extends com.wingflare.facade.module.user.convert.RolePermissionConvert
{
	RolePermissionConvert convert = Mappers.getMapper(RolePermissionConvert.class);

	RolePermissionBO doToBo(RolePermissionDO rolePermissionDo);

	RolePermissionDTO doToDto(RolePermissionDO rolePermissionDo);

	RolePermissionDO boToDo(RolePermissionBO rolePermissionBo);

	RolePermissionDO dtoToDo(RolePermissionDTO rolePermissionDto);

	List<RolePermissionBO> doToBoList(List<RolePermissionDO> rolePermissionDOList);

	List<RolePermissionDTO> doToDtoList(List<RolePermissionDO> rolePermissionDOList);

	List<RolePermissionDO> boToDoList(List<RolePermissionBO> rolePermissionBOList);

	List<RolePermissionDO> dtoToDoList(List<RolePermissionDTO> rolePermissionDTOList);
}
