package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.UserDO;
import com.wingflare.facade.module.user.bo.UserBO;
import com.wingflare.facade.module.user.dto.UserDTO;
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

	UserBO doToBo(UserDO userDo);

	UserDTO doToDto(UserDO userDo);

	UserDO boToDo(UserBO userBo);

	UserDO dtoToDo(UserDTO userDto);

	List<UserBO> doToBoList(List<UserDO> userDOList);

	List<UserDTO> doToDtoList(List<UserDO> userDOList);

	List<UserDO> boToDoList(List<UserBO> userBOList);

	List<UserDO> dtoToDoList(List<UserDTO> userDTOList);
}
