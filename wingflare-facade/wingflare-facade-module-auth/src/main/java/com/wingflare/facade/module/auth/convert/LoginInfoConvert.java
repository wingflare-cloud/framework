package com.wingflare.facade.module.auth.convert;


import com.wingflare.facade.module.auth.bo.LoginInfoBo;
import com.wingflare.facade.module.auth.dto.LoginInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * LoginInfoConvert
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
@Mapper
public interface LoginInfoConvert
{
	LoginInfoConvert convert = Mappers.getMapper(LoginInfoConvert.class);

	LoginInfoBo dtoToBo(LoginInfoDto loginInfoDto);

	LoginInfoDto boToDto(LoginInfoBo loginInfoBo);

	List<LoginInfoBo> dtoToBoList(List<LoginInfoDto> loginInfoDtoList);

	List<LoginInfoDto> boToDtoList(List<LoginInfoBo> loginInfoBoList);
}
