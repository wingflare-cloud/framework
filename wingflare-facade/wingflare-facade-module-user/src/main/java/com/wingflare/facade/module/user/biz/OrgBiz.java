package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.OrgBO;
import com.wingflare.facade.module.user.bo.OrgSearchBO;
import com.wingflare.facade.module.user.dto.OrgDTO;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;


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
    PageDto<OrgDTO> list(@Valid OrgSearchBO bo);

    /**
     * 查询组织机构详情
     */
    OrgDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个组织机构详情
     */
    OrgDTO getOnlyOne(@Valid @NotNull OrgSearchBO searchBo);

    /**
     * 删除组织机构
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增组织机构
     */
    @Validated({Default.class, Create.class})
    OrgDTO create(@Valid @NotNull OrgBO bo);

    /**
     * 更新组织机构
     */
    @Validated({Default.class, Update.class})
    OrgDTO update(@Valid @NotNull OrgBO bo);

}