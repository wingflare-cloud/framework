package com.wingflare.facade.module.base.convert;


import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.dto.MenuDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * MenuConvert
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
@Mapper
public interface MenuConvert
{
	MenuConvert convert = Mappers.getMapper(MenuConvert.class);

	MenuBo dtoToBo(MenuDto menuDto);

	MenuDto boToDto(MenuBo menuBo);

	List<MenuBo> dtoToBoList(List<MenuDto> menuDtoList);

	List<MenuDto> boToDtoList(List<MenuBo> menuBoList);
}
