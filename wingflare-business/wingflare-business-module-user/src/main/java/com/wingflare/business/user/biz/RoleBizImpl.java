package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.api.core.PageDto;
import com.wingflare.api.core.annotation.Validated;
import com.wingflare.api.core.validate.Create;
import com.wingflare.api.core.validate.Update;
import com.wingflare.api.event.EventPublisher;
import com.wingflare.api.transaction.TransactionTemplate;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.convert.RoleConvert;
import com.wingflare.business.user.db.RoleDO;
import com.wingflare.business.user.service.RoleServer;
import com.wingflare.business.user.wrapper.RoleWrapper;
import com.wingflare.facade.module.user.biz.RoleBiz;
import com.wingflare.facade.module.user.bo.*;
import com.wingflare.facade.module.user.dto.RoleDTO;
import com.wingflare.facade.module.user.event.RoleCreateEvent;
import com.wingflare.facade.module.user.event.RoleCreatedEvent;
import com.wingflare.facade.module.user.event.RoleDeleteEvent;
import com.wingflare.facade.module.user.event.RoleDeletedEvent;
import com.wingflare.facade.module.user.event.RoleUpdateEvent;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.bo.IdBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Validated
public class RoleBizImpl implements RoleBiz {

    private final RoleServer roleServer;

    private final TransactionTemplate transactionTemplate;

    private final EventPublisher eventPublisher;

    private static final Logger logger = LoggerFactory.getLogger(RoleBizImpl.class);

    public RoleBizImpl(RoleServer roleServer, TransactionTemplate transactionTemplate, EventPublisher eventPublisher) {
        this.roleServer = roleServer;
        this.transactionTemplate = transactionTemplate;
        this.eventPublisher = eventPublisher;
    }

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
        RoleDTO ret = transactionTemplate.execute(() -> {
            RoleDTO roleDto = null;
            RoleDO roleDo = roleServer.getById(bo.getId());
            if (roleDo != null) {
                Assert.isTrue(roleServer.removeById(bo.getId()), ErrorCode.SYS_ROLE_DELETE_ERROR);
                roleDto = RoleConvert.convert.doToDto(roleDo);
                eventPublisher.publishEvent(new RoleDeleteEvent(roleDto));
            }
            return roleDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        eventPublisher.publishEvent(new RoleDeletedEvent(val));
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
        RoleDTO ret = transactionTemplate.execute(() -> {
            checkRoleCanSave(bo, null);
            RoleDO roleDo = RoleConvert.convert.boToDo(bo);
            Assert.isTrue(roleServer.save(roleDo), ErrorCode.SYS_ROLE_CREATE_ERROR);
            RoleDTO roleDto = RoleConvert.convert.doToDto(roleDo);
            eventPublisher.publishEvent(new RoleCreateEvent(bo, roleDto));
            return roleDto;
        });

        try {
            eventPublisher.publishEvent(new RoleCreatedEvent(bo, ret));
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
        RoleDTO ret = transactionTemplate.execute(() -> {
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
                eventPublisher.publishEvent(new RoleUpdateEvent(oldDto.get(), roleDto));
            } else {
                roleDto = RoleConvert.convert.doToDto(oldRoleDO);
            }

            return roleDto;
        });

        if (oldDto.get() != null) {
            try {
                eventPublisher.publishEvent(new RoleUpdateEvent(oldDto.get(), ret));
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