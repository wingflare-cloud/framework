package com.wingflare.business.base.convert;


import com.wingflare.business.base.db.MenuDO;
import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.dto.MenuDTO;
import com.wingflare.facade.module.base.dto.SimpleMenuDTO;
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

	MenuBO doToBo(MenuDO menuDo);

	MenuDTO doToDto(MenuDO menuDo);

	MenuDO boToDo(MenuBO menuBo);

	MenuDO dtoToDo(MenuDTO menuDto);

	SimpleMenuDTO doToSimpleDto(MenuDO menuDo);

	List<MenuBO> doToBoList(List<MenuDO> menuDOList);

	List<MenuDTO> doToDtoList(List<MenuDO> menuDOList);

	List<MenuDO> boToDoList(List<MenuBO> menuBOList);

	List<MenuDO> dtoToDoList(List<MenuDTO> menuDTOList);
}
