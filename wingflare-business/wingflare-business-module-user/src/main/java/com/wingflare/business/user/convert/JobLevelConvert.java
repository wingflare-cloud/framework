package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.JobLevelDo;
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
public interface JobLevelConvert extends com.wingflare.facade.module.user.convert.JobLevelConvert
{
	JobLevelConvert convert = Mappers.getMapper(JobLevelConvert.class);

	JobLevelBo doToBo(JobLevelDo jobLevelDo);

	JobLevelDto doToDto(JobLevelDo jobLevelDo);

	JobLevelDo boToDo(JobLevelBo jobLevelBo);

	JobLevelDo dtoToDo(JobLevelDto jobLevelDto);

	List<JobLevelBo> doToBoList(List<JobLevelDo> jobLevelDoList);

	List<JobLevelDto> doToDtoList(List<JobLevelDo> jobLevelDoList);

	List<JobLevelDo> boToDoList(List<JobLevelBo> jobLevelBoList);

	List<JobLevelDo> dtoToDoList(List<JobLevelDto> jobLevelDtoList);
}
