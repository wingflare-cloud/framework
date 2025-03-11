package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.OrgDepartmentBo;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBo;
import com.wingflare.facade.module.user.dto.OrgDepartmentDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.validation.annotation.Validated;

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
    PageDto<OrgDepartmentDto> list(@Valid OrgDepartmentSearchBo bo);

    /**
     * 查询机构部门详情
     */
    OrgDepartmentDto get(@Valid @NotNull IdBo bo);

    /**
     * 通过条件查询单个机构部门详情
     */
    OrgDepartmentDto getOnlyOne(@Valid @NotNull OrgDepartmentSearchBo searchBo);

    /**
     * 删除机构部门
     */
    void delete(@Valid @NotNull IdBo bo);

    /**
     * 新增机构部门
     */
    @Validated({Default.class, Create.class})
    OrgDepartmentDto create(@Valid @NotNull OrgDepartmentBo bo);

    /**
     * 更新机构部门
     */
    @Validated({Default.class, Update.class})
    OrgDepartmentDto update(@Valid @NotNull OrgDepartmentBo bo);

}