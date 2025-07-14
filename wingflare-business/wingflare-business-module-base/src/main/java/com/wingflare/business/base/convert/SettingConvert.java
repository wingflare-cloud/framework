package com.wingflare.business.base.convert;


import com.wingflare.business.base.db.SettingDO;
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
public interface SettingConvert extends com.wingflare.facade.module.base.convert.SettingConvert
{
	SettingConvert convert = Mappers.getMapper(SettingConvert.class);

	SettingBO doToBo(SettingDO settingDo);

	SettingDTO doToDto(SettingDO settingDo);

	SettingDO boToDo(SettingBO settingBo);

	SettingDO dtoToDo(SettingDTO settingDto);

	List<SettingBO> doToBoList(List<SettingDO> settingDOList);

	List<SettingDTO> doToDtoList(List<SettingDO> settingDOList);

	List<SettingDO> boToDoList(List<SettingBO> settingBOList);

	List<SettingDO> dtoToDoList(List<SettingDTO> settingDTOList);
}
