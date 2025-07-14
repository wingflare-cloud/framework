package com.wingflare.facade.module.base.convert;


import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.dto.MenuDTO;
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

	MenuBO dtoToBo(MenuDTO menuDto);

	MenuDTO boToDto(MenuBO menuBo);

	List<MenuBO> dtoToBoList(List<MenuDTO> menuDTOList);

	List<MenuDTO> boToDtoList(List<MenuBO> menuBOList);
}
