package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.convert.OrgConvert;
import com.wingflare.business.user.db.OrgDo;
import com.wingflare.business.user.service.OrgServer;
import com.wingflare.business.user.wrapper.OrgWrapper;
import com.wingflare.facade.module.user.biz.OrgBiz;
import com.wingflare.facade.module.user.bo.OrgBo;
import com.wingflare.facade.module.user.bo.OrgSearchBo;
import com.wingflare.facade.module.user.dto.OrgDto;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;


/**
 * 组织机构Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
@Component
@Validated
public class OrgBizImpl implements OrgBiz {

    @Resource
    private OrgServer orgServer;

    /**
     * 查询组织机构列表
     */
    @Override
    public PageDto<OrgDto> list(@Valid OrgSearchBo bo) {
        IPage<OrgDo> iPage = orgServer.page(
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
    public OrgDto get(@Valid @NotNull IdBo bo) {
        return OrgConvert.convert.doToDto(
                orgServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个组织机构详情
     */
    @Override
    public OrgDto getOnlyOne(@Valid @NotNull OrgSearchBo searchBo) {
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
        OrgDo orgDo = orgServer.getById(bo.getId());

        if (orgDo != null) {
            orgServer.removeById(bo.getId());
        }
    }

    /**
     * 新增组织机构
     */
    @Override
    @Validated({Default.class, Create.class})
    public OrgDto create(@Valid @NotNull OrgBo bo) {
        OrgDo orgDo = OrgConvert.convert.boToDo(bo);
        orgServer.save(orgDo);
        return OrgConvert.convert.doToDto(orgDo);
    }

    /**
     * 更新组织机构
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public OrgDto update(@Valid @NotNull OrgBo bo) {
        OrgDo oldOrgDo = orgServer.getById(bo.getOrgId());

        if (oldOrgDo == null) {
            throw new DataNotFoundException("org.data.notfound");
        }

        OrgDo orgDo = OrgConvert.convert.boToDo(bo);
        oldOrgDo.setOnNew(orgDo);
        orgServer.updateById(oldOrgDo);
        return OrgConvert.convert.doToDto(orgDo);
    }

    /**
     * 判断是否存在符合条件的组织机构
     *
     * @param bo 查询参数
     * @return 组织机构
     */
    public boolean has(OrgSearchBo bo) {
        return orgServer.has(
                OrgWrapper.getQueryWrapper(bo)
        );
    }

}