package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.OrgBo;
import com.wingflare.facade.module.user.bo.OrgSearchBo;
import com.wingflare.facade.module.user.dto.OrgDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 组织机构Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
@Validated
public interface OrgBiz {

    /**
     * 查询组织机构列表
     */
    PageDto<OrgDto> list(@Valid OrgSearchBo bo);

    /**
     * 查询组织机构详情
     */
    OrgDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个组织机构详情
     */
    OrgDto getOnlyOne(@Valid @NotNull OrgSearchBo searchBo);

    /**
     * 删除组织机构
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增组织机构
     */
    @Validated({Default.class, Create.class})
    OrgDto create(@Valid @NotNull OrgBo bo);

    /**
     * 更新组织机构
     */
    @Validated({Default.class, Update.class})
    OrgDto update(@Valid @NotNull OrgBo bo);

}