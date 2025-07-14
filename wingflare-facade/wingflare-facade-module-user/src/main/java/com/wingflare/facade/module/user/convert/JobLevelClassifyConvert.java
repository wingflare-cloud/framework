package com.wingflare.facade.module.user.convert;

import com.wingflare.facade.module.user.bo.JobLevelClassifyBO;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDTO;
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

    JobLevelClassifyBO dtoToBo(JobLevelClassifyDTO jobLevelClassifyDto);

    JobLevelClassifyDTO boToDto(JobLevelClassifyBO jobLevelClassifyBo);

    List<JobLevelClassifyBO> dtoToBoList(List<JobLevelClassifyDTO> jobLevelClassifyDTOList);

    List<JobLevelClassifyDTO> boToDtoList(List<JobLevelClassifyBO> jobLevelClassifyBOList);

}