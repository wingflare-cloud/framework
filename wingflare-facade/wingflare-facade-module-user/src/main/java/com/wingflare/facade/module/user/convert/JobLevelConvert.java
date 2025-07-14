package com.wingflare.facade.module.user.convert;


import com.wingflare.facade.module.user.bo.JobLevelBO;
import com.wingflare.facade.module.user.dto.JobLevelDTO;
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

	JobLevelBO dtoToBo(JobLevelDTO jobLevelDto);

	JobLevelDTO boToDto(JobLevelBO jobLevelBo);

	List<JobLevelBO> dtoToBoList(List<JobLevelDTO> jobLevelDTOList);

	List<JobLevelDTO> boToDtoList(List<JobLevelBO> jobLevelBOList);
}
