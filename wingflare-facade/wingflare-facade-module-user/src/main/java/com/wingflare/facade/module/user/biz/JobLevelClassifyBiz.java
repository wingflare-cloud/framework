package com.wingflare.facade.module.user.biz;

import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.facade.module.user.dto.JobLevelClassifyDto;
import com.wingflare.facade.module.user.bo.JobLevelClassifyBo;
import com.wingflare.facade.module.user.bo.JobLevelClassifySearchBo;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

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

    PageDto<JobLevelClassifyDto> list(@Valid JobLevelClassifySearchBo bo);

    JobLevelClassifyDto get(@Valid @NotNull IdBo bo);

    JobLevelClassifyDto getOnlyOne(@Valid @NotNull JobLevelClassifySearchBo bo);

    @Validated({Default.class, Create.class})
    JobLevelClassifyDto create(@Valid @NotNull JobLevelClassifyBo bo);

    @Validated({Default.class, Update.class})
    JobLevelClassifyDto update(@Valid @NotNull JobLevelClassifyBo bo);

    JobLevelClassifyDto delete(@Valid @NotNull IdBo bo);

}
