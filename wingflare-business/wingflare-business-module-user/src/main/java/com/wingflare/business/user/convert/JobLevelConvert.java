package com.wingflare.business.user.convert;


import com.wingflare.business.user.db.JobLevelDO;
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
public interface JobLevelConvert extends com.wingflare.facade.module.user.convert.JobLevelConvert
{
	JobLevelConvert convert = Mappers.getMapper(JobLevelConvert.class);

	JobLevelBO doToBo(JobLevelDO jobLevelDo);

	JobLevelDTO doToDto(JobLevelDO jobLevelDo);

	JobLevelDO boToDo(JobLevelBO jobLevelBo);

	JobLevelDO dtoToDo(JobLevelDTO jobLevelDto);

	List<JobLevelBO> doToBoList(List<JobLevelDO> jobLevelDOList);

	List<JobLevelDTO> doToDtoList(List<JobLevelDO> jobLevelDOList);

	List<JobLevelDO> boToDoList(List<JobLevelBO> jobLevelBOList);

	List<JobLevelDO> dtoToDoList(List<JobLevelDTO> jobLevelDTOList);
}
