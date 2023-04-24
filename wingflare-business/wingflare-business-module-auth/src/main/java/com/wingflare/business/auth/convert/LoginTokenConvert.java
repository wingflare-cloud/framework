package com.wingflare.business.auth.convert;


import com.wingflare.business.auth.db.LoginTokenDo;
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
public interface LoginTokenConvert extends com.wingflare.facade.module.auth.convert.LoginTokenConvert
{
	LoginTokenConvert convert = Mappers.getMapper(LoginTokenConvert.class);

	LoginTokenBo doToBo(LoginTokenDo loginTokenDo);

	LoginTokenDto doToDto(LoginTokenDo loginTokenDo);

	LoginTokenDo boToDo(LoginTokenBo loginTokenBo);

	LoginTokenDo dtoToDo(LoginTokenDto loginTokenDto);

	List<LoginTokenBo> doToBoList(List<LoginTokenDo> loginTokenDoList);

	List<LoginTokenDto> doToDtoList(List<LoginTokenDo> loginTokenDoList);

	List<LoginTokenDo> boToDoList(List<LoginTokenBo> loginTokenBoList);

	List<LoginTokenDo> dtoToDoList(List<LoginTokenDto> loginTokenDtoList);
}
