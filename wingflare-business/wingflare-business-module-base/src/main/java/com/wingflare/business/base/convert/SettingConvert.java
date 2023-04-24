package com.wingflare.business.base.convert;


import com.wingflare.business.base.db.SettingDo;
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
public interface SettingConvert extends com.wingflare.facade.module.base.convert.SettingConvert
{
	SettingConvert convert = Mappers.getMapper(SettingConvert.class);

	SettingBo doToBo(SettingDo settingDo);

	SettingDto doToDto(SettingDo settingDo);

	SettingDo boToDo(SettingBo settingBo);

	SettingDo dtoToDo(SettingDto settingDto);

	List<SettingBo> doToBoList(List<SettingDo> settingDoList);

	List<SettingDto> doToDtoList(List<SettingDo> settingDoList);

	List<SettingDo> boToDoList(List<SettingBo> settingBoList);

	List<SettingDo> dtoToDoList(List<SettingDto> settingDtoList);
}
