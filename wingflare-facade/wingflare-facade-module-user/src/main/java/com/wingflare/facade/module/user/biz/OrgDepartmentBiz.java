package com.wingflare.facade.module.user.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.api.core.validate.Create;
import com.wingflare.api.core.validate.Update;
import com.wingflare.api.validation.annotation.Validated;
import com.wingflare.facade.module.user.bo.OrgDepartmentBO;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBO;
import com.wingflare.facade.module.user.dto.OrgDepartmentDTO;
import com.wingflare.lib.standard.bo.IdBo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;


/**
 * 机构部门Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
@Validated
public interface OrgDepartmentBiz {

    /**
     * 查询机构部门列表
     */
    PageDto<OrgDepartmentDTO> list(@Valid OrgDepartmentSearchBO bo);

    /**
     * 查询机构部门详情
     */
    OrgDepartmentDTO get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个机构部门详情
     */
    OrgDepartmentDTO getOnlyOne(@Valid @NotNull OrgDepartmentSearchBO searchBo);

    /**
     * 删除机构部门
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增机构部门
     */
    @Validated({Default.class, Create.class})
    OrgDepartmentDTO create(@Valid @NotNull OrgDepartmentBO bo);

    /**
     * 更新机构部门
     */
    @Validated({Default.class, Update.class})
    OrgDepartmentDTO update(@Valid @NotNull OrgDepartmentBO bo);

}