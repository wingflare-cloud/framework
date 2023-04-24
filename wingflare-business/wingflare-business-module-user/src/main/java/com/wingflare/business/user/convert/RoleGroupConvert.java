package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.RoleGroupDo;
import com.wingflare.facade.module.user.bo.RoleGroupBo;
import com.wingflare.facade.module.user.dto.RoleGroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * RoleGroupConvert
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
@Mapper
public interface RoleGroupConvert extends com.wingflare.facade.module.user.convert.RoleGroupConvert
{
	RoleGroupConvert convert = Mappers.getMapper(RoleGroupConvert.class);

	RoleGroupBo doToBo(RoleGroupDo roleGroupDo);

	RoleGroupDto doToDto(RoleGroupDo roleGroupDo);

	RoleGroupDo boToDo(RoleGroupBo roleGroupBo);

	RoleGroupDo dtoToDo(RoleGroupDto roleGroupDto);

	List<RoleGroupBo> doToBoList(List<RoleGroupDo> roleGroupDoList);

	List<RoleGroupDto> doToDtoList(List<RoleGroupDo> roleGroupDoList);

	List<RoleGroupDo> boToDoList(List<RoleGroupBo> roleGroupBoList);

	List<RoleGroupDo> dtoToDoList(List<RoleGroupDto> roleGroupDtoList);
}
