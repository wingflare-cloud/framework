package com.wingflare.facade.module.auth.convert;


import com.wingflare.facade.module.auth.bo.LoginTokenBo;
import com.wingflare.facade.module.auth.dto.LoginTokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * LoginTokenConvert
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:36:13 CST 2023
 */
@Mapper
public interface LoginTokenConvert
{
	LoginTokenConvert convert = Mappers.getMapper(LoginTokenConvert.class);

	LoginTokenBo dtoToBo(LoginTokenDto loginTokenDto);

	LoginTokenDto boToDto(LoginTokenBo loginTokenBo);

	List<LoginTokenBo> dtoToBoList(List<LoginTokenDto> loginTokenDtoList);

	List<LoginTokenDto> boToDtoList(List<LoginTokenBo> loginTokenBoList);
}
