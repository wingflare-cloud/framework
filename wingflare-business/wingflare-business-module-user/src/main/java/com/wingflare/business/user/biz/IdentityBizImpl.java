package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.constants.UserEventName;
import com.wingflare.business.user.db.IdentityDO;
import com.wingflare.business.user.service.JobLevelServer;
import com.wingflare.business.user.service.OrgDepartmentServer;
import com.wingflare.business.user.service.OrgServer;
import com.wingflare.business.user.wrapper.IdentityWrapper;
import com.wingflare.facade.module.user.biz.IdentityBiz;
import com.wingflare.business.user.convert.IdentityConvert;
import com.wingflare.business.user.service.IdentityServer;
import com.wingflare.facade.module.user.bo.IdentityBO;
import com.wingflare.facade.module.user.bo.IdentitySearchBO;
import com.wingflare.facade.module.user.dto.IdentityDTO;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
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

import java.math.BigInteger;
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
    public PageDto<IdentityDTO> list(@Valid IdentitySearchBO bo) {
        IPage<IdentityDO> iPage = identityServer.page(
                identityServer.createPage(bo),
                IdentityWrapper.getLambdaQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                IdentityConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询岗位身份详情
     */
    @Override
    public IdentityDTO get(@Valid @NotNull IdBo bo) {
        return IdentityConvert.convert.doToDto(
                identityServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个岗位身份详情
     */
    @Override
    public IdentityDTO getOnlyOne(@Valid @NotNull IdentitySearchBO searchBo) {
        return IdentityConvert.convert.doToDto(
                identityServer.getOne(IdentityWrapper.getLambdaQueryWrapper(searchBo)));
    }

    /**
     * 删除岗位身份
     */
    @Override
    public IdentityDTO delete(@Valid @NotNull IdBo bo) {
        IdentityDTO dto = deleteHandle(bo);
        afterDelete(dto);
        return dto;
    }

    public IdentityDTO deleteHandle(@Valid @NotNull IdBo bo) {
        return transactionTemplate.execute(status -> {
            IdentityDTO dto = null;
            IdentityDO identityDo = identityServer.getById(bo.getId());

            if (identityDo != null) {
                identityServer.removeById(bo.getId());
                dto = IdentityConvert.convert.doToDto(identityDo);
                eventUtil.publishEvent(UserEventName.IDENTITY_DELETE, false, dto);
            }

            return dto;
        });
    }

    public void afterDelete(IdentityDTO dto) {
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
    public IdentityDTO create(@Valid @NotNull IdentityBO bo) {
        checkIdentityCanSave(bo, null);
        IdentityDO identityDo = IdentityConvert.convert.boToDo(bo);
        identityServer.save(identityDo);
        return IdentityConvert.convert.doToDto(identityDo);
    }

    /**
     * 更新岗位身份
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public IdentityDTO update(@Valid @NotNull IdentityBO bo) {
        IdentityDO oldIdentityDO = identityServer.getById(bo.getIdentityId());

        if (oldIdentityDO == null) {
            throw new DataNotFoundException("identity.data.notfound");
        }

        checkIdentityCanSave(bo, oldIdentityDO);
        IdentityDO identityDo = IdentityConvert.convert.boToDo(bo);
        IdentityDO oldField = oldIdentityDO.setOnNew(identityDo);

        if (oldField != null) {
            identityServer.updateById(oldIdentityDO);
        }

        return IdentityConvert.convert.doToDto(identityDo);
    }


    private void checkIdentityCanSave(IdentityBO bo, IdentityDO oldDo) {
        IdentitySearchBO searchBo = new IdentitySearchBO().setEq_identityName(bo.getIdentityName());

        if (oldDo != null) {
            searchBo = searchBo.setNeq_identityId(bo.getIdentityId());
        }

        if (bo.getDepartmentId() != null && bo.getDepartmentId().compareTo(BigInteger.ZERO) > 0) {
            Assert.isTrue(orgDepartmentServer.hasById(bo.getDepartmentId()), ErrorCode.SYS_PARENT_DEPARTMENT_NON_EXISTENT);
            searchBo.setEq_departmentId(bo.getDepartmentId());
        } else if (bo.getOrgId() != null && bo.getOrgId().compareTo(BigInteger.ZERO) > 0) {
            searchBo.setEq_orgId(bo.getOrgId());
        }

        if (bo.getJobLevelId() != null && bo.getJobLevelId().compareTo(BigInteger.ZERO) > 0) {
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
    public boolean has(IdentitySearchBO bo) {
        return identityServer.has(
                IdentityWrapper.getLambdaQueryWrapper(bo)
        );
    }

}