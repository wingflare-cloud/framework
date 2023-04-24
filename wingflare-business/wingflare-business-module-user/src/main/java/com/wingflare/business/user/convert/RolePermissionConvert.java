package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.RolePermissionDo;
import com.wingflare.facade.module.user.bo.RolePermissionBo;
import com.wingflare.facade.module.user.dto.RolePermissionDto;
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

	RolePermissionBo doToBo(RolePermissionDo rolePermissionDo);

	RolePermissionDto doToDto(RolePermissionDo rolePermissionDo);

	RolePermissionDo boToDo(RolePermissionBo rolePermissionBo);

	RolePermissionDo dtoToDo(RolePermissionDto rolePermissionDto);

	List<RolePermissionBo> doToBoList(List<RolePermissionDo> rolePermissionDoList);

	List<RolePermissionDto> doToDtoList(List<RolePermissionDo> rolePermissionDoList);

	List<RolePermissionDo> boToDoList(List<RolePermissionBo> rolePermissionBoList);

	List<RolePermissionDo> dtoToDoList(List<RolePermissionDto> rolePermissionDtoList);
}
