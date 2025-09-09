package com.wingflare.facade.module.user.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBO;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDTO;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBO;


/**
 * <p>
 * 职级分类表 业务接口
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public interface JobLevelClassifyBiz {

    PageDto<JobLevelClassifyDTO> list(JobLevelClassifySearchBO bo);

    JobLevelClassifyDTO get(IdBo bo);

    JobLevelClassifyDTO getOnlyOne(JobLevelClassifySearchBO bo);

    JobLevelClassifyDTO create(JobLevelClassifyBO bo);

    JobLevelClassifyDTO update(JobLevelClassifyBO bo);

    JobLevelClassifyDTO delete(IdBo bo);

}
