package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.constants.UserEventName;
import com.wingflare.business.user.convert.RoleConvert;
import com.wingflare.business.user.db.RoleDo;
import com.wingflare.business.user.service.RoleGroupServer;
import com.wingflare.business.user.service.RoleServer;
import com.wingflare.business.user.wrapper.RoleWrapper;
import com.wingflare.facade.module.user.biz.RoleBiz;
import com.wingflare.facade.module.user.bo.RoleBo;
import com.wingflare.facade.module.user.bo.RoleSearchBo;
import com.wingflare.facade.module.user.dto.RoleDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.StringUtil;
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

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
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
    private RoleGroupServer roleGroupServer;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private EventUtil eventUtil;

    private static final Logger logger = LoggerFactory.getLogger(RoleBizImpl.class);

    /**
     * 查询系统角色列表
     */
    @Override
    public PageDto<RoleDto> list(@Valid RoleSearchBo bo) {
        IPage<RoleDo> iPage = roleServer.page(
                roleServer.createPage(bo),
                RoleWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                RoleConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统角色详情
     */
    @Override
    public RoleDto get(@Valid @NotNull IdBo bo) {
        return RoleConvert.convert.doToDto(
                roleServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统角色详情
     */
    @Override
    public RoleDto getOnlyOne(@Valid @NotNull RoleSearchBo searchBo) {
        return RoleConvert.convert.doToDto(
                roleServer.getOne(
                        RoleWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统角色
     */
    @Override
    public RoleDto delete(@Valid @NotNull IdBo bo) {
        RoleDto ret = transactionTemplate.execute(status -> {
            RoleDto roleDto = null;
            RoleDo roleDo = roleServer.getById(bo.getId());
            if (roleDo != null) {
                Assert.isFalse(has(new RoleSearchBo()
                        .setEq_parentRoleId(bo.getId())), ErrorCode.SYS_ROLE_HAS_SUB);
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
    public RoleDto create(@Valid @NotNull RoleBo bo) {
        RoleDto ret = transactionTemplate.execute(status -> {
            checkRoleCanSave(bo, null);
            RoleDo roleDo = RoleConvert.convert.boToDo(bo);
            Assert.isTrue(roleServer.save(roleDo), ErrorCode.SYS_ROLE_CREATE_ERROR);
            RoleDto roleDto = RoleConvert.convert.doToDto(roleDo);
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
    public RoleDto update(@Valid @NotNull RoleBo bo) {
        AtomicReference<RoleDto> oldDto = new AtomicReference<>(null);
        RoleDto ret = transactionTemplate.execute(status -> {
            RoleDo oldRoleDo = roleServer.getById(bo.getRoleId());
            RoleDto roleDto = null;

            if (oldRoleDo == null) {
                throw new DataNotFoundException("role.data.notfound" );
            }

            checkRoleCanSave(bo, oldRoleDo);
            RoleDo roleDo = RoleConvert.convert.boToDo(bo);
            RoleDo oldField = oldRoleDo.setOnNew(roleDo);

            if (oldField != null) {
                oldDto.set(RoleConvert.convert.doToDto(oldField));
                Assert.isTrue(roleServer.updateById(oldRoleDo), ErrorCode.SYS_ROLE_UPDATE_ERROR);
                roleDto = RoleConvert.convert.doToDto(oldRoleDo);
                eventUtil.publishEvent(UserEventName.ROLE_UPDATE, oldDto.get(), roleDto);
            } else {
                roleDto = RoleConvert.convert.doToDto(oldRoleDo);
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
    private void checkRoleCanSave(RoleBo bo, RoleDo oldDo) {
        if (oldDo == null) {
            if (StringUtil.isNotEmpty(bo.getParentRoleId())) {
                Assert.isFalse(has(
                        new RoleSearchBo()
                                .setEq_roleId(bo.getParentRoleId())
                ), ErrorCode.SYS_ROLE_PARENT_NOTFOUND);
            }

            if (StringUtil.isNotEmpty(bo.getRoleGroupId())) {
                Assert.isTrue(roleGroupServer.hasById(bo.getRoleGroupId()), ErrorCode.SYS_ROLE_GROUP_NOT_FOUND);
            }

            Assert.isFalse(has(
                    new RoleSearchBo()
                            .setEq_roleName(bo.getRoleName())
            ), ErrorCode.SYS_ROLE_NAME_REPEAT);
        } else {
            if (!oldDo.getParentRoleId().equals(bo.getParentRoleId())) {
                throw new BusinessLogicException(ErrorCode.SYS_ROLE_UPDATE_FORBID_PARENT);
            }

            if (StringUtil.isNotEmpty(bo.getRoleGroupId()) && !bo.getRoleGroupId().equals(oldDo.getRoleGroupId())) {
                Assert.isTrue(roleGroupServer.hasById(bo.getRoleGroupId()), ErrorCode.SYS_ROLE_GROUP_NOT_FOUND);
            }

            if (bo.getRoleName() != null && !bo.getRoleName().equals(oldDo.getRoleName())) {
                Assert.isFalse(has(
                        new RoleSearchBo()
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
    public boolean has(RoleSearchBo bo) {
        return roleServer.has(
                RoleWrapper.getQueryWrapper(bo)
        );
    }

}