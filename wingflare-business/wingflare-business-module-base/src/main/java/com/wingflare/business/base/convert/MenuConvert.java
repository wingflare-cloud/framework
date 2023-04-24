package com.wingflare.business.base.convert;


import com.wingflare.business.base.db.MenuDo;
import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.dto.MenuDto;
import com.wingflare.facade.module.base.dto.SimpleMenuDto;
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
public interface MenuConvert extends com.wingflare.facade.module.base.convert.MenuConvert
{
	MenuConvert convert = Mappers.getMapper(MenuConvert.class);

	MenuBo doToBo(MenuDo menuDo);

	MenuDto doToDto(MenuDo menuDo);

	MenuDo boToDo(MenuBo menuBo);

	MenuDo dtoToDo(MenuDto menuDto);

	SimpleMenuDto doToSimpleDto(MenuDo menuDo);

	List<MenuBo> doToBoList(List<MenuDo> menuDoList);

	List<MenuDto> doToDtoList(List<MenuDo> menuDoList);

	List<MenuDo> boToDoList(List<MenuBo> menuBoList);

	List<MenuDo> dtoToDoList(List<MenuDto> menuDtoList);
}
