package com.wingflare.facade.module.user.convert

import com.wingflare.facade.module.user.bo.JobLevelClassifyBo;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 职级分类表 bo dto类型转换器
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@Mapper
public interface JobLevelClassifyConvert {

    JobLevelClassifyConvert convert = Mappers.getMapper(JobLevelClassifyConvert.class);

    JobLevelClassifyBo dtoToBo(JobLevelClassifyDto jobLevelClassifyDto);

    JobLevelClassifyDto boToDto(JobLevelClassifyBo jobLevelClassifyBo);

    List<JobLevelClassifyBo> dtoToBoList(List<JobLevelClassifyDto> jobLevelClassifyDtoList);

    List<JobLevelClassifyDto> boToDtoList(List<JobLevelClassifyBo> jobLevelClassifyBoList);

}