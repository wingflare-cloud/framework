package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.JobLevelBo;
import com.wingflare.facade.module.user.bo.JobLevelSearchBo;
import com.wingflare.facade.module.user.dto.JobLevelDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 职级Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
@Validated
public interface JobLevelBiz {

    /**
     * 查询职级列表
     */
    PageDto<JobLevelDto> list(@Valid JobLevelSearchBo bo);

    /**
     * 查询职级详情
     */
    JobLevelDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个职级详情
     */
    JobLevelDto getOnlyOne(@Valid @NotNull JobLevelSearchBo searchBo);

    /**
     * 删除职级
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增职级
     */
    @Validated({Default.class, Create.class})
    JobLevelDto create(@Valid @NotNull JobLevelBo bo);

    /**
     * 更新职级
     */
    @Validated({Default.class, Update.class})
    JobLevelDto update(@Valid @NotNull JobLevelBo bo);

}