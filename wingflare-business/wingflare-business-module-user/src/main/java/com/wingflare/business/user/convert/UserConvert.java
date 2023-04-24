package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.UserDo;
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
public interface UserConvert extends com.wingflare.facade.module.user.convert.UserConvert
{
	UserConvert convert = Mappers.getMapper(UserConvert.class);

	UserBo doToBo(UserDo userDo);

	UserDto doToDto(UserDo userDo);

	UserDo boToDo(UserBo userBo);

	UserDo dtoToDo(UserDto userDto);

	List<UserBo> doToBoList(List<UserDo> userDoList);

	List<UserDto> doToDtoList(List<UserDo> userDoList);

	List<UserDo> boToDoList(List<UserBo> userBoList);

	List<UserDo> dtoToDoList(List<UserDto> userDtoList);
}
