package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.JobLevelBO;
import com.wingflare.facade.module.user.bo.JobLevelSearchBO;
import com.wingflare.facade.module.user.dto.JobLevelDTO;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;


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
    PageDto<JobLevelDTO> list(@Valid JobLevelSearchBO bo);

    /**
     * 查询职级详情
     */
    JobLevelDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个职级详情
     */
    JobLevelDTO getOnlyOne(@Valid @NotNull JobLevelSearchBO searchBo);

    /**
     * 删除职级
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增职级
     */
    @Validated({Default.class, Create.class})
    JobLevelDTO create(@Valid @NotNull JobLevelBO bo);

    /**
     * 更新职级
     */
    @Validated({Default.class, Update.class})
    JobLevelDTO update(@Valid @NotNull JobLevelBO bo);

}