package com.wingflare.facade.module.user.biz;


import com.wingflare.api.core.PageDto;
import com.wingflare.facade.module.user.bo.OrgBO;
import com.wingflare.facade.module.user.bo.OrgSearchBO;
import com.wingflare.facade.module.user.dto.OrgDTO;
import com.wingflare.lib.standard.bo.IdBo;


/**
 * 组织机构Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public interface OrgBiz {

    /**
     * 查询组织机构列表
     */
    PageDto<OrgDTO> list(OrgSearchBO bo);

    /**
     * 查询组织机构详情
     */
    OrgDTO get(IdBo bo);

    /**
     * 通过条件查询单个组织机构详情
     */
    OrgDTO getOnlyOne(OrgSearchBO searchBo);

    /**
     * 删除组织机构
     */
    void delete(IdBo bo);

    /**
     * 新增组织机构
     */
    OrgDTO create(OrgBO bo);

    /**
     * 更新组织机构
     */
    OrgDTO update(OrgBO bo);

}