package com.wingflare.facade.module.user.biz;

import com.wingflare.facade.module.user.bo.JobLevelClassifyBO;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBO;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

/**
 * <p>
 * 职级分类表 业务接口
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
@Validated
public interface JobLevelClassifyBiz {

    PageDto<JobLevelClassifyDTO> list(@Valid JobLevelClassifySearchBO bo);

    JobLevelClassifyDTO get(@Valid @NotNull IdBo bo);

    JobLevelClassifyDTO getOnlyOne(@Valid @NotNull JobLevelClassifySearchBO bo);

    @Validated({Default.class, Create.class})
    JobLevelClassifyDTO create(@Valid @NotNull JobLevelClassifyBO bo);

    @Validated({Default.class, Update.class})
    JobLevelClassifyDTO update(@Valid @NotNull JobLevelClassifyBO bo);

    JobLevelClassifyDTO delete(@Valid @NotNull IdBo bo);

}
