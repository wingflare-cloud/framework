package com.wingflare.business.user.convert;

import com.wingflare.business.user.db.JobLevelClassifyDO;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBO;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDTO;
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

    JobLevelClassifyBO doToBo(JobLevelClassifyDO jobLevelClassifyDo);

    JobLevelClassifyDTO doToDto(JobLevelClassifyDO jobLevelClassifyDo);

    JobLevelClassifyDO boToDo(JobLevelClassifyBO jobLevelClassifyBo);

    JobLevelClassifyDO dtoToDo(JobLevelClassifyDTO jobLevelClassifyDto);

    List<JobLevelClassifyBO> doToBoList(List<JobLevelClassifyDO> jobLevelClassifyDOList);

    List<JobLevelClassifyDTO> doToDtoList(List<JobLevelClassifyDO> jobLevelClassifyDOList);

    List<JobLevelClassifyDO> boToDoList(List<JobLevelClassifyBO> jobLevelClassifyBOList);

    List<JobLevelClassifyDO> dtoToDoList(List<JobLevelClassifyDTO> jobLevelClassifyDTOList);

}
