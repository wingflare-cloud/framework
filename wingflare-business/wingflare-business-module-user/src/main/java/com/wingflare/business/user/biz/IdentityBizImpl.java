package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.constants.UserEventName;
import com.wingflare.business.user.service.JobLevelServer;
import com.wingflare.business.user.service.OrgDepartmentServer;
import com.wingflare.business.user.service.OrgServer;
import com.wingflare.business.user.wrapper.IdentityWrapper;
import com.wingflare.facade.module.user.biz.IdentityBiz;
import com.wingflare.business.user.convert.IdentityConvert;
import com.wingflare.business.user.service.IdentityServer;
import com.wingflare.business.user.db.IdentityDo;
import com.wingflare.facade.module.user.bo.IdentityBo;
import com.wingflare.facade.module.user.bo.IdentitySearchBo;
import com.wingflare.facade.module.user.dto.IdentityDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.EventUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.standard.bo.IdBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.Optional;


/**
 * 岗位身份BizImpl
 *
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
@Component
@Validated
public class IdentityBizImpl implements IdentityBiz {

    @Resource
    private IdentityServer identityServer;

    @Resource
    private OrgServer orgServer;

    @Resource
    private JobLevelServer jobLevelServer;

    @Resource
    private OrgDepartmentServer orgDepartmentServer;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private EventUtil eventUtil;

    private static final Logger logger = LoggerFactory.getLogger(IdentityBizImpl.class);

    /**
     * 查询岗位身份列表
     */
    @Override
    public PageDto<IdentityDto> list(@Valid IdentitySearchBo bo) {
        IPage<IdentityDo> iPage = identityServer.page(
                identityServer.createPage(bo),
                IdentityWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                IdentityConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询岗位身份详情
     */
    @Override
    public IdentityDto get(@Valid @NotNull IdBo bo) {
        return IdentityConvert.convert.doToDto(
                identityServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个岗位身份详情
     */
    @Override
    public IdentityDto getOnlyOne(@Valid @NotNull IdentitySearchBo searchBo) {
        return IdentityConvert.convert.doToDto(
                identityServer.getOne(IdentityWrapper.getQueryWrapper(searchBo)));
    }

    /**
     * 删除岗位身份
     */
    @Override
    public IdentityDto delete(@Valid @NotNull IdBo bo) {
        IdentityDto dto = deleteHandle(bo);
        afterDelete(dto);
        return dto;
    }

    public IdentityDto deleteHandle(@Valid @NotNull IdBo bo) {
        return transactionTemplate.execute(status -> {
            IdentityDto dto = null;
            IdentityDo identityDo = identityServer.getById(bo.getId());

            if (identityDo != null) {
                identityServer.removeById(bo.getId());
                dto = IdentityConvert.convert.doToDto(identityDo);
                eventUtil.publishEvent(UserEventName.IDENTITY_DELETE, false, dto);
            }

            return dto;
        });
    }

    public void afterDelete(IdentityDto dto) {
        Optional.ofNullable(dto)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(UserEventName.IDENTITY_DELETED, false, val);
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });
    }

    /**
     * 新增岗位身份
     */
    @Override
    @Validated({Default.class, Create.class})
    public IdentityDto create(@Valid @NotNull IdentityBo bo) {
        checkIdentityCanSave(bo, null);
        IdentityDo identityDo = IdentityConvert.convert.boToDo(bo);
        identityServer.save(identityDo);
        return IdentityConvert.convert.doToDto(identityDo);
    }

    /**
     * 更新岗位身份
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public IdentityDto update(@Valid @NotNull IdentityBo bo) {
        IdentityDo oldIdentityDo = identityServer.getById(bo.getIdentityId());

        if (oldIdentityDo == null) {
            throw new DataNotFoundException("identity.data.notfound");
        }

        checkIdentityCanSave(bo, oldIdentityDo);
        IdentityDo identityDo = IdentityConvert.convert.boToDo(bo);
        IdentityDo oldField = oldIdentityDo.setOnNew(identityDo);

        if (oldField != null) {
            identityServer.updateById(oldIdentityDo);
        }

        return IdentityConvert.convert.doToDto(identityDo);
    }


    private void checkIdentityCanSave(IdentityBo bo, IdentityDo oldDo) {
        IdentitySearchBo searchBo = new IdentitySearchBo().setEq_identityName(bo.getIdentityName());

        if (oldDo != null) {
            searchBo = searchBo.setNeq_identityId(bo.getIdentityId());
        }

        if (StringUtil.isNotEmpty(bo.getDepartmentId())) {
            Assert.isTrue(orgDepartmentServer.hasById(bo.getDepartmentId()), ErrorCode.SYS_PARENT_DEPARTMENT_NON_EXISTENT);
            searchBo.setEq_departmentId(bo.getDepartmentId());
        } else if (StringUtil.isNotEmpty(bo.getOrgId())) {
            searchBo.setEq_orgId(bo.getOrgId());
        }

        if (StringUtil.isNotEmpty(bo.getJobLevelId())) {
            Assert.isTrue(jobLevelServer.hasById(bo.getJobLevelId()), ErrorCode.SYS_JOB_LEVEL_NON_EXISTENT);
        }

        Assert.isTrue(orgServer.hasById(bo.getOrgId()), ErrorCode.SYS_ORG_NON_EXISTENT);
        Assert.isFalse(has(searchBo), ErrorCode.SYS_IDENTITY_NAME_REPEAT);
    }

    /**
     * 判断是否存在符合条件的岗位身份
     *
     * @param bo 查询参数
     * @return 岗位身份
     */
    public boolean has(IdentitySearchBo bo) {
        return identityServer.has(
                IdentityWrapper.getQueryWrapper(bo)
        );
    }

}