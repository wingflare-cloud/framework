package com.wingflare.facade.module.user.convert;


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
public interface UserConvert
{
	UserConvert convert = Mappers.getMapper(UserConvert.class);

	UserBO dtoToBo(UserDTO userDto);

	UserDTO boToDto(UserBO userBo);

	List<UserBO> dtoToBoList(List<UserDTO> userDTOList);

	List<UserDTO> boToDtoList(List<UserBO> userBOList);
}
