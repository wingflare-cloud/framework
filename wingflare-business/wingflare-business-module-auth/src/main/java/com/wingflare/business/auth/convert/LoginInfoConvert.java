package com.wingflare.business.auth.convert;


import com.wingflare.business.auth.db.LoginInfoDo;
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
public interface LoginInfoConvert extends com.wingflare.facade.module.auth.convert.LoginInfoConvert
{
	LoginInfoConvert convert = Mappers.getMapper(LoginInfoConvert.class);

	LoginInfoBo doToBo(LoginInfoDo loginInfoDo);

	LoginInfoDto doToDto(LoginInfoDo loginInfoDo);

	LoginInfoDo boToDo(LoginInfoBo loginInfoBo);

	LoginInfoDo dtoToDo(LoginInfoDto loginInfoDto);

	List<LoginInfoBo> doToBoList(List<LoginInfoDo> loginInfoDoList);

	List<LoginInfoDto> doToDtoList(List<LoginInfoDo> loginInfoDoList);

	List<LoginInfoDo> boToDoList(List<LoginInfoBo> loginInfoBoList);

	List<LoginInfoDo> dtoToDoList(List<LoginInfoDto> loginInfoDtoList);
}
