package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.JobLevelBo;
import com.wingflare.facade.module.user.dto.JobLevelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * JobLevelConvert
 * 
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
@Mapper
public interface JobLevelConvert
{
	JobLevelConvert convert = Mappers.getMapper(JobLevelConvert.class);

	JobLevelBo dtoToBo(JobLevelDto jobLevelDto);

	JobLevelDto boToDto(JobLevelBo jobLevelBo);

	List<JobLevelBo> dtoToBoList(List<JobLevelDto> jobLevelDtoList);

	List<JobLevelDto> boToDtoList(List<JobLevelBo> jobLevelBoList);
}
