package com.wingflare.facade.module.base.convert;


import com.wingflare.facade.module.base.bo.SettingBo;
import com.wingflare.facade.module.base.dto.SettingDto;
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

	SettingBo dtoToBo(SettingDto settingDto);

	SettingDto boToDto(SettingBo settingBo);

	List<SettingBo> dtoToBoList(List<SettingDto> settingDtoList);

	List<SettingDto> boToDtoList(List<SettingBo> settingBoList);
}
