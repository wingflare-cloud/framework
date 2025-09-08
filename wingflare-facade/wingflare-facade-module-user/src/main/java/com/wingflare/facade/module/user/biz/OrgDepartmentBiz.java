package com.wingflare.facade.module.user.biz;


import com.wingflare.facade.module.user.bo.OrgDepartmentBO;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBO;
import com.wingflare.facade.module.user.dto.OrgDepartmentDTO;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;


/**
 * 机构部门Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
public interface OrgDepartmentBiz {

    /**
     * 查询机构部门列表
     */
    PageDto<OrgDepartmentDTO> list(OrgDepartmentSearchBO bo);

    /**
     * 查询机构部门详情
     */
    OrgDepartmentDTO get(IdBo bo);

    /**
     * 通过条件查询单个机构部门详情
     */
    OrgDepartmentDTO getOnlyOne(OrgDepartmentSearchBO searchBo);

    /**
     * 删除机构部门
     */
    void delete(IdBo bo);

    /**
     * 新增机构部门
     */
    OrgDepartmentDTO create(OrgDepartmentBO bo);

    /**
     * 更新机构部门
     */
    OrgDepartmentDTO update(OrgDepartmentBO bo);

}