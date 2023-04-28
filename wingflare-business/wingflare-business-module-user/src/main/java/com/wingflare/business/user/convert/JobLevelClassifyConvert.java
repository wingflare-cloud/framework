package com.wingflare.business.user.convert

import com.wingflare.business.user.db.JobLevelClassifyDo;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBo;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * 职级分类表 类型转换器
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@Mapper
public interface JobLevelClassifyConvert extends com.wingflare.facade.module.user.convert.JobLevelClassifyConvert {

    JobLevelClassifyConvert convert = Mappers.getMapper(JobLevelClassifyConvert.class);

    JobLevelClassifyBo doToBo(JobLevelClassifyDo jobLevelClassifyDo);

    JobLevelClassifyDto doToDto(JobLevelClassifyDo jobLevelClassifyDo);

    JobLevelClassifyDo boToDo(JobLevelClassifyBo jobLevelClassifyBo);

    JobLevelClassifyDo dtoToDo(JobLevelClassifyDto jobLevelClassifyDto);

    List<JobLevelClassifyBo> doToBoList(List<JobLevelClassifyDo> jobLevelClassifyDoList);

    List<JobLevelClassifyDto> doToDtoList(List<JobLevelClassifyDo> jobLevelClassifyDoList);

    List<JobLevelClassifyDo> boToDoList(List<JobLevelClassifyBo> jobLevelClassifyBoList);

    List<JobLevelClassifyDo> dtoToDoList(List<JobLevelClassifyDto> jobLevelClassifyDtoList);

}
