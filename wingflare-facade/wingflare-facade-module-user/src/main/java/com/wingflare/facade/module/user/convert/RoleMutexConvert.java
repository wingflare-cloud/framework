package com.wingflare.facade.module.user.convert;


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
public interface RoleMutexConvert
{
	RoleMutexConvert convert = Mappers.getMapper(RoleMutexConvert.class);

	RoleMutexBo dtoToBo(RoleMutexDto roleMutexDto);

	RoleMutexDto boToDto(RoleMutexBo roleMutexBo);

	List<RoleMutexBo> dtoToBoList(List<RoleMutexDto> roleMutexDtoList);

	List<RoleMutexDto> boToDtoList(List<RoleMutexBo> roleMutexBoList);
}
