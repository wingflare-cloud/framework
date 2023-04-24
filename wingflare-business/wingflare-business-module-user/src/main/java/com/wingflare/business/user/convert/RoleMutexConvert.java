package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.RoleMutexDo;
import com.wingflare.facade.module.user.bo.RoleMutexBo;
import com.wingflare.facade.module.user.dto.RoleMutexDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * RoleMutexConvert
 * 
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
@Mapper
public interface RoleMutexConvert extends com.wingflare.facade.module.user.convert.RoleMutexConvert
{
	RoleMutexConvert convert = Mappers.getMapper(RoleMutexConvert.class);

	RoleMutexBo doToBo(RoleMutexDo roleMutexDo);

	RoleMutexDto doToDto(RoleMutexDo roleMutexDo);

	RoleMutexDo boToDo(RoleMutexBo roleMutexBo);

	RoleMutexDo dtoToDo(RoleMutexDto roleMutexDto);

	List<RoleMutexBo> doToBoList(List<RoleMutexDo> roleMutexDoList);

	List<RoleMutexDto> doToDtoList(List<RoleMutexDo> roleMutexDoList);

	List<RoleMutexDo> boToDoList(List<RoleMutexBo> roleMutexBoList);

	List<RoleMutexDo> dtoToDoList(List<RoleMutexDto> roleMutexDtoList);
}
