package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.api.core.PageDto;
import com.wingflare.business.user.convert.OrgConvert;
import com.wingflare.business.user.db.OrgDO;
import com.wingflare.business.user.service.OrgServer;
import com.wingflare.business.user.wrapper.OrgWrapper;
import com.wingflare.facade.module.user.biz.OrgBiz;
import com.wingflare.facade.module.user.bo.OrgBO;
import com.wingflare.facade.module.user.bo.OrgSearchBO;
import com.wingflare.facade.module.user.dto.OrgDTO;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.transaction.annotation.Transactional;
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
public class OrgBizImpl implements OrgBiz {

    private final OrgServer orgServer;

    public OrgBizImpl(OrgServer orgServer) {
        this.orgServer = orgServer;
    }

    /**
     * 查询组织机构列表
     */
    @Override
    public PageDto<OrgDTO> list(@Valid OrgSearchBO bo) {
        IPage<OrgDO> iPage = orgServer.page(
                orgServer.createPage(bo),
                OrgWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                OrgConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询组织机构详情
     */
    @Override
    public OrgDTO get(@Valid @NotNull IdBo bo) {
        return OrgConvert.convert.doToDto(
                orgServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个组织机构详情
     */
    @Override
    public OrgDTO getOnlyOne(@Valid @NotNull OrgSearchBO searchBo) {
        return OrgConvert.convert.doToDto(
                orgServer.getOne(
                        OrgWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除组织机构
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        OrgDO orgDo = orgServer.getById(bo.getId());

        if (orgDo != null) {
            orgServer.removeById(bo.getId());
        }
    }

    /**
     * 新增组织机构
     */
    @Override
    @Validated({Default.class, Create.class})
    public OrgDTO create(@Valid @NotNull OrgBO bo) {
        OrgDO orgDo = OrgConvert.convert.boToDo(bo);
        orgServer.save(orgDo);
        return OrgConvert.convert.doToDto(orgDo);
    }

    /**
     * 更新组织机构
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public OrgDTO update(@Valid @NotNull OrgBO bo) {
        OrgDO oldOrgDO = orgServer.getById(bo.getOrgId());

        if (oldOrgDO == null) {
            throw new DataNotFoundException("org.data.notfound");
        }

        OrgDO orgDo = OrgConvert.convert.boToDo(bo);
        oldOrgDO.setOnNew(orgDo);
        orgServer.updateById(oldOrgDO);
        return OrgConvert.convert.doToDto(orgDo);
    }

    /**
     * 判断是否存在符合条件的组织机构
     *
     * @param bo 查询参数
     * @return 组织机构
     */
    public boolean has(OrgSearchBO bo) {
        return orgServer.has(
                OrgWrapper.getQueryWrapper(bo)
        );
    }

}