package com.wingflare.facade.module.base.convert;


import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.dto.SettingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * SettingConvert
 * 
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
@Mapper
public interface SettingConvert
{
	SettingConvert convert = Mappers.getMapper(SettingConvert.class);

	SettingBO dtoToBo(SettingDTO settingDto);

	SettingDTO boToDto(SettingBO settingBo);

	List<SettingBO> dtoToBoList(List<SettingDTO> settingDTOList);

	List<SettingDTO> boToDtoList(List<SettingBO> settingBOList);
}
