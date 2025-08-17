package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.convert.RoleConvert;
import com.wingflare.business.user.db.RoleDO;
import com.wingflare.business.user.service.RoleServer;
import com.wingflare.business.user.wrapper.RoleWrapper;
import com.wingflare.facade.module.user.biz.RoleBiz;
import com.wingflare.facade.module.user.bo.*;
import com.wingflare.facade.module.user.constants.UserEventName;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.EventUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;

import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 系统角色Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
@Component
@Validated
public class RoleBizImpl implements RoleBiz {

    @Resource
    private RoleServer roleServer;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private EventUtil eventUtil;

    private static final Logger logger = LoggerFactory.getLogger(RoleBizImpl.class);

    /**
     * 查询系统角色列表
     */
    @Override
    public PageDto<RoleDTO> list(@Valid RoleSearchBO bo) {
        LambdaQueryWrapper<RoleDO> queryWrapper = RoleWrapper.getLambdaQueryWrapper(bo);
        queryWrapper.orderByDesc(RoleDO::getRoleId);

        IPage<RoleDO> iPage = roleServer.page(
                roleServer.createPage(bo),
                queryWrapper
        );

        return PageUtil.convertIPage(iPage,
                RoleConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统角色详情
     */
    @Override
    public RoleDTO get(@Valid @NotNull IdBo bo) {
        return RoleConvert.convert.doToDto(
                roleServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统角色详情
     */
    @Override
    public RoleDTO getOnlyOne(@Valid @NotNull RoleSearchBO searchBo) {
        return RoleConvert.convert.doToDto(
                roleServer.getOne(
                        RoleWrapper.getLambdaQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统角色
     */
    @Override
    public RoleDTO delete(@Valid @NotNull IdBo bo) {
        RoleDTO ret = transactionTemplate.execute(status -> {
            RoleDTO roleDto = null;
            RoleDO roleDo = roleServer.getById(bo.getId());
            if (roleDo != null) {
                Assert.isTrue(roleServer.removeById(bo.getId()), ErrorCode.SYS_ROLE_DELETE_ERROR);
                roleDto = RoleConvert.convert.doToDto(roleDo);
                eventUtil.publishEvent(UserEventName.ROLE_DELETE, roleDto);
            }
            return roleDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(UserEventName.ROLE_DELETED, val);
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });

        return ret;
    }

    /**
     * 新增系统角色
     */
    @Override
    @Validated({Default.class, Create.class})
    public RoleDTO create(@Valid @NotNull RoleBO bo) {
        RoleDTO ret = transactionTemplate.execute(status -> {
            checkRoleCanSave(bo, null);
            RoleDO roleDo = RoleConvert.convert.boToDo(bo);
            Assert.isTrue(roleServer.save(roleDo), ErrorCode.SYS_ROLE_CREATE_ERROR);
            RoleDTO roleDto = RoleConvert.convert.doToDto(roleDo);
            eventUtil.publishEvent(UserEventName.ROLE_CREATE, bo, roleDto);
            return roleDto;
        });

        try {
            eventUtil.publishEvent(UserEventName.ROLE_CREATED, bo, ret);
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }

        return ret;
    }

    /**
     * 更新系统角色
     */
    @Override
    @Validated({Default.class, Update.class})
    public RoleDTO update(@Valid @NotNull RoleBO bo) {
        AtomicReference<RoleDTO> oldDto = new AtomicReference<>(null);
        RoleDTO ret = transactionTemplate.execute(status -> {
            RoleDO oldRoleDO = roleServer.getById(bo.getRoleId());
            RoleDTO roleDto = null;

            if (oldRoleDO == null) {
                throw new DataNotFoundException("role.data.notfound");
            }

            checkRoleCanSave(bo, oldRoleDO);
            RoleDO roleDo = RoleConvert.convert.boToDo(bo);
            RoleDO oldField = oldRoleDO.setOnNew(roleDo);

            if (oldField != null) {
                oldDto.set(RoleConvert.convert.doToDto(oldField));
                Assert.isTrue(roleServer.updateById(oldRoleDO), ErrorCode.SYS_ROLE_UPDATE_ERROR);
                roleDto = RoleConvert.convert.doToDto(oldRoleDO);
                eventUtil.publishEvent(UserEventName.ROLE_UPDATE, oldDto.get(), roleDto);
            } else {
                roleDto = RoleConvert.convert.doToDto(oldRoleDO);
            }

            return roleDto;
        });

        if (oldDto.get() != null) {
            try {
                eventUtil.publishEvent(UserEventName.ROLE_UPDATED, oldDto.get(), ret);
            } catch (Throwable e) {
                logger.warn(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * 判断角色是否允许保存
     *
     * @param bo
     * @param oldDo
     */
    private void checkRoleCanSave(RoleBO bo, RoleDO oldDo) {
        if (oldDo == null) {
            if (bo.getParentRoleId() != null && bo.getParentRoleId().compareTo(BigInteger.ZERO) > 0) {
                Assert.isFalse(has(
                        new RoleSearchBO()
                                .setEq_roleId(bo.getParentRoleId())
                ), ErrorCode.SYS_ROLE_PARENT_NOTFOUND);
            }

            Assert.isFalse(has(
                    new RoleSearchBO()
                            .setEq_roleName(bo.getRoleName())
            ), ErrorCode.SYS_ROLE_NAME_REPEAT);
        } else {
            if (bo.getRoleName() != null && !bo.getRoleName().equals(oldDo.getRoleName())) {
                Assert.isFalse(has(
                        new RoleSearchBO()
                                .setEq_roleName(bo.getRoleName())
                                .setNeq_roleId(bo.getRoleId())
                ), ErrorCode.SYS_ROLE_NAME_REPEAT);
            }
        }
    }

    /**
     * 判断是否存在符合条件的系统角色
     *
     * @param bo 查询参数
     * @return 系统角色
     */
    public boolean has(RoleSearchBO bo) {
        return roleServer.has(
                RoleWrapper.getLambdaQueryWrapper(bo)
        );
    }

}