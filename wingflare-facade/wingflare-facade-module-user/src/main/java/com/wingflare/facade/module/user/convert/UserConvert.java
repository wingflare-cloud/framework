package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.UserBo;
import com.wingflare.facade.module.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * UserConvert
 * 
 * @author naizui_ycx
 * @date Tue Mar 07 17:34:13 CST 2023
 */
@Mapper
public interface UserConvert
{
	UserConvert convert = Mappers.getMapper(UserConvert.class);

	UserBo dtoToBo(UserDto userDto);

	UserDto boToDto(UserBo userBo);

	List<UserBo> dtoToBoList(List<UserDto> userDtoList);

	List<UserDto> boToDtoList(List<UserBo> userBoList);
}
