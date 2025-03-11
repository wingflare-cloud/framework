package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.convert.OrgDepartmentConvert;
import com.wingflare.business.user.db.OrgDepartmentDo;
import com.wingflare.business.user.service.OrgDepartmentServer;
import com.wingflare.business.user.service.OrgServer;
import com.wingflare.business.user.service.UserRoleServer;
import com.wingflare.business.user.wrapper.OrgDepartmentWrapper;
import com.wingflare.facade.module.user.biz.OrgDepartmentBiz;
import com.wingflare.facade.module.user.bo.OrgDepartmentBo;
import com.wingflare.facade.module.user.bo.OrgDepartmentSearchBo;
import com.wingflare.facade.module.user.dto.OrgDepartmentDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;


/**
 * 机构部门Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
@Component
@Validated
public class OrgDepartmentBizImpl implements OrgDepartmentBiz {

    @Resource
    private OrgDepartmentServer orgDepartmentServer;

    @Resource
    private OrgServer orgServer;

    @Resource
    private UserRoleServer userRoleServer;

    /**
     * 查询机构部门列表
     */
    @Override
    public PageDto<OrgDepartmentDto> list(@Valid OrgDepartmentSearchBo bo) {
        IPage<OrgDepartmentDo> iPage = orgDepartmentServer.page(
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
    public OrgDepartmentDto get(@Valid @NotNull IdBo bo) {
        return OrgDepartmentConvert.convert.doToDto(
                orgDepartmentServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个机构部门详情
     */
    @Override
    public OrgDepartmentDto getOnlyOne(@Valid @NotNull OrgDepartmentSearchBo searchBo) {
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
        OrgDepartmentDo orgDepartmentDo = orgDepartmentServer.getById(bo.getId());

        if (orgDepartmentDo != null) {
            orgDepartmentServer.removeById(bo.getId());
        }
    }

    /**
     * 新增机构部门
     */
    @Override
    @Validated({Default.class, Create.class})
    public OrgDepartmentDto create(@Valid @NotNull OrgDepartmentBo bo) {
        checkOrgDepartmentCanSave(bo, null);
        OrgDepartmentDo orgDepartmentDo = OrgDepartmentConvert.convert.boToDo(bo);
        orgDepartmentServer.save(orgDepartmentDo);
        return OrgDepartmentConvert.convert.doToDto(orgDepartmentDo);
    }

    /**
     * 更新机构部门
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public OrgDepartmentDto update(@Valid @NotNull OrgDepartmentBo bo) {
        OrgDepartmentDo oldOrgDepartmentDo = orgDepartmentServer.getById(bo.getDepartmentId());

        if (oldOrgDepartmentDo == null) {
            throw new DataNotFoundException("orgDepartment.data.notfound");
        }

        checkOrgDepartmentCanSave(bo, oldOrgDepartmentDo);
        OrgDepartmentDo orgDepartmentDo = OrgDepartmentConvert.convert.boToDo(bo);
        oldOrgDepartmentDo.setOnNew(orgDepartmentDo);
        orgDepartmentServer.updateById(oldOrgDepartmentDo);
        return OrgDepartmentConvert.convert.doToDto(orgDepartmentDo);
    }


    private void checkOrgDepartmentCanSave(OrgDepartmentBo bo, OrgDepartmentDo oldDo) {
        OrgDepartmentSearchBo searchBo = new OrgDepartmentSearchBo()
                .setEq_departmentName(bo.getDepartmentName());

        if (oldDo != null) {
            searchBo.setNeq_departmentId(oldDo.getDepartmentId());
        }

        if (StringUtil.isNotEmpty(bo.getParentDepartmentId())) {
            Assert.isTrue(orgDepartmentServer.hasById(bo.getParentDepartmentId()), ErrorCode.SYS_DEPARTMENT_NON_EXISTENT);
            searchBo.setEq_parentDepartmentId(bo.getParentDepartmentId());
        }

        if (StringUtil.isNotEmpty(bo.getDepartmentId())) {
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
    public boolean has(OrgDepartmentSearchBo bo) {
        return orgDepartmentServer.has(
                OrgDepartmentWrapper.getQueryWrapper(bo)
        );
    }

}