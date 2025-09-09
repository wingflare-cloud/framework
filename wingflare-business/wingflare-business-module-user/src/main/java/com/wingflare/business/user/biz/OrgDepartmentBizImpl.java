package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.api.core.PageDto;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.convert.OrgDepartmentConvert;
import com.wingflare.business.user.db.OrgDepartmentDO;
import com.wingflare.business.user.service.OrgDepartmentServer;
import com.wingflare.business.user.service.OrgServer;
import com.wingflare.business.user.wrapper.OrgDepartmentWrapper;
import com.wingflare.facade.module.user.biz.OrgDepartmentBiz;
import com.wingflare.facade.module.user.bo.OrgDepartmentBO;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBO;
import com.wingflare.facade.module.user.dto.OrgDepartmentDTO;
import com.wingflare.lib.core.Assert;
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

import java.math.BigInteger;


/**
 * 机构部门Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
@Validated
public class OrgDepartmentBizImpl implements OrgDepartmentBiz {

    private final OrgDepartmentServer orgDepartmentServer;

    private final OrgServer orgServer;

    public OrgDepartmentBizImpl(OrgDepartmentServer orgDepartmentServer, OrgServer orgServer) {
        this.orgDepartmentServer = orgDepartmentServer;
        this.orgServer = orgServer;
    }

    /**
     * 查询机构部门列表
     */
    @Override
    public PageDto<OrgDepartmentDTO> list(@Valid OrgDepartmentSearchBO bo) {
        IPage<OrgDepartmentDO> iPage = orgDepartmentServer.page(
                orgDepartmentServer.createPage(bo),
                OrgDepartmentWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                OrgDepartmentConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询机构部门详情
     */
    @Override
    public OrgDepartmentDTO get(@Valid @NotNull IdBo bo) {
        return OrgDepartmentConvert.convert.doToDto(
                orgDepartmentServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个机构部门详情
     */
    @Override
    public OrgDepartmentDTO getOnlyOne(@Valid @NotNull OrgDepartmentSearchBO searchBo) {
        return OrgDepartmentConvert.convert.doToDto(
                orgDepartmentServer.getOne(
                        OrgDepartmentWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除机构部门
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        OrgDepartmentDO orgDepartmentDo = orgDepartmentServer.getById(bo.getId());

        if (orgDepartmentDo != null) {
            orgDepartmentServer.removeById(bo.getId());
        }
    }

    /**
     * 新增机构部门
     */
    @Override
    @Validated({Default.class, Create.class})
    public OrgDepartmentDTO create(@Valid @NotNull OrgDepartmentBO bo) {
        checkOrgDepartmentCanSave(bo, null);
        OrgDepartmentDO orgDepartmentDo = OrgDepartmentConvert.convert.boToDo(bo);
        orgDepartmentServer.save(orgDepartmentDo);
        return OrgDepartmentConvert.convert.doToDto(orgDepartmentDo);
    }

    /**
     * 更新机构部门
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public OrgDepartmentDTO update(@Valid @NotNull OrgDepartmentBO bo) {
        OrgDepartmentDO oldOrgDepartmentDO = orgDepartmentServer.getById(bo.getDepartmentId());

        if (oldOrgDepartmentDO == null) {
            throw new DataNotFoundException("orgDepartment.data.notfound");
        }

        checkOrgDepartmentCanSave(bo, oldOrgDepartmentDO);
        OrgDepartmentDO orgDepartmentDo = OrgDepartmentConvert.convert.boToDo(bo);
        oldOrgDepartmentDO.setOnNew(orgDepartmentDo);
        orgDepartmentServer.updateById(oldOrgDepartmentDO);
        return OrgDepartmentConvert.convert.doToDto(orgDepartmentDo);
    }


    private void checkOrgDepartmentCanSave(OrgDepartmentBO bo, OrgDepartmentDO oldDo) {
        OrgDepartmentSearchBO searchBo = new OrgDepartmentSearchBO()
                .setEq_departmentName(bo.getDepartmentName());

        if (oldDo != null) {
            searchBo.setNeq_departmentId(oldDo.getDepartmentId());
        }

        if (bo.getParentDepartmentId() != null && bo.getParentDepartmentId().compareTo(BigInteger.ZERO) > 0) {
            Assert.isTrue(orgDepartmentServer.hasById(bo.getParentDepartmentId()), ErrorCode.SYS_DEPARTMENT_NON_EXISTENT);
            searchBo.setEq_parentDepartmentId(bo.getParentDepartmentId());
        }

        if (bo.getDepartmentId() != null && bo.getDepartmentId().compareTo(BigInteger.ZERO) > 0) {
            searchBo.setEq_departmentId(bo.getDepartmentId());
        } else {
            searchBo.setEq_orgId(bo.getOrgId());
        }

        Assert.isTrue(orgServer.hasById(bo.getOrgId()), ErrorCode.SYS_ORG_NON_EXISTENT);
        Assert.isFalse(has(searchBo), ErrorCode.SYS_ORG_DEPARTMENT_NAME_REPEAT);
    }

    /**
     * 判断是否存在符合条件的机构部门
     *
     * @param bo 查询参数
     * @return 机构部门
     */
    public boolean has(OrgDepartmentSearchBO bo) {
        return orgDepartmentServer.has(
                OrgDepartmentWrapper.getQueryWrapper(bo)
        );
    }

}