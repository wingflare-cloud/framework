package com.wingflare.business.user.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.user.ErrorCode;
import com.wingflare.business.user.constants.UserEventName;
import com.wingflare.business.user.convert.RoleGroupConvert;
import com.wingflare.business.user.db.RoleGroupDo;
import com.wingflare.business.user.service.RoleGroupServer;
import com.wingflare.business.user.wrapper.RoleGroupWrapper;
import com.wingflare.facade.module.user.biz.RoleGroupBiz;
import com.wingflare.facade.module.user.bo.RoleGroupBo;
import com.wingflare.facade.module.user.bo.RoleGroupSearchBo;
import com.wingflare.facade.module.user.bo.RoleSearchBo;
import com.wingflare.facade.module.user.dto.RoleGroupDto;
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

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 系统角色分组表Biz
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
@Component
@Validated
public class RoleGroupBizImpl implements RoleGroupBiz {

    @Resource
    private RoleGroupServer roleGroupServer;

    @Resource
    private RoleBizImpl roleBiz;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private EventUtil eventUtil;

    private static final Logger logger = LoggerFactory.getLogger(RoleGroupBizImpl.class);

    /**
     * 查询系统角色分组表列表
     */
    @Override
    public PageDto<RoleGroupDto> list(@Valid RoleGroupSearchBo bo) {
        IPage<RoleGroupDo> iPage = roleGroupServer.page(
                roleGroupServer.createPage(bo),
                RoleGroupWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                RoleGroupConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统角色分组表详情
     */
    @Override
    public RoleGroupDto get(@Valid @NotNull IdBo bo) {
        return RoleGroupConvert.convert.doToDto(
                roleGroupServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统角色分组表详情
     */
    @Override
    public RoleGroupDto getOnlyOne(@Valid @NotNull RoleGroupSearchBo searchBo) {
        return RoleGroupConvert.convert.doToDto(
                roleGroupServer.getOne(
                        RoleGroupWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统角色分组表
     */
    @Override
    public void delete(@Valid @NotNull IdBo bo) {
        RoleGroupDto ret = transactionTemplate.execute(status -> {
            RoleGroupDto roleGroupDto = null;
            RoleGroupDo roleGroupDo = roleGroupServer.getById(bo.getId());

            if (roleGroupDo != null) {
                Assert.isFalse(roleBiz.has(
                        new RoleSearchBo()
                                .setEq_roleGroupId(roleGroupDo.getRoleGroupId())
                ), ErrorCode.SYS_ROLE_GROUP_USED);
                Assert.isTrue(roleGroupServer.removeById(bo.getId()), ErrorCode.SYS_ROLE_GROUP_DELETE_ERROR);
                roleGroupDto = RoleGroupConvert.convert.doToDto(roleGroupDo);
                eventUtil.publishEvent(UserEventName.ROLE_GROUP_DELETE, roleGroupDto);
            }

            return roleGroupDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(UserEventName.ROLE_GROUP_DELETED, ret);
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });
    }

    /**
     * 新增系统角色分组表
     */
    @Override
    @Validated({Default.class, Create.class})
    public RoleGroupDto create(@Valid @NotNull RoleGroupBo bo) {
        RoleGroupDto ret = transactionTemplate.execute(status -> {
            checkRoleGroupCanSave(bo, null);
            RoleGroupDo roleGroupDo = RoleGroupConvert.convert.boToDo(bo);
            Assert.isTrue(roleGroupServer.save(roleGroupDo), ErrorCode.SYS_ROLE_GROUP_CREATE_ERROR);
            RoleGroupDto roleGroupDto = RoleGroupConvert.convert.doToDto(roleGroupDo);
            eventUtil.publishEvent(UserEventName.ROLE_GROUP_CREATE, bo, roleGroupDto);
            return roleGroupDto;
        });

        try {
            eventUtil.publishEvent(UserEventName.ROLE_GROUP_CREATED, bo, ret);
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }

        return ret;
    }

    /**
     * 更新系统角色分组表
     */
    @Override
    @Validated({Default.class, Update.class})
    public RoleGroupDto update(@Valid @NotNull RoleGroupBo bo) {
        AtomicReference<RoleGroupDto> oldDto = new AtomicReference<>(null);
        RoleGroupDto ret = transactionTemplate.execute(status -> {
            RoleGroupDo oldRoleGroupDo = roleGroupServer.getById(bo.getRoleGroupId());
            RoleGroupDto roleGroupDto = null;

            if (oldRoleGroupDo == null) {
                throw new DataNotFoundException("roleGroup.data.notfound");
            }

            checkRoleGroupCanSave(bo, oldRoleGroupDo);
            RoleGroupDo roleGroupDo = RoleGroupConvert.convert.boToDo(bo);
            RoleGroupDo oldField = oldRoleGroupDo.setOnNew(roleGroupDo);

            if (oldField != null) {
                oldDto.set(RoleGroupConvert.convert.doToDto(oldField));
                Assert.isTrue(roleGroupServer.updateById(oldRoleGroupDo), ErrorCode.SYS_ROLE_GROUP_UPDATE_ERROR);
                roleGroupDto = RoleGroupConvert.convert.doToDto(oldRoleGroupDo);
                eventUtil.publishEvent(UserEventName.ROLE_GROUP_UPDATE, oldDto.get(), roleGroupDto);
            } else {
                roleGroupDto = RoleGroupConvert.convert.doToDto(oldRoleGroupDo);
            }

            return roleGroupDto;
        });

        if (oldDto.get() != null) {
            try {
                eventUtil.publishEvent(UserEventName.ROLE_GROUP_UPDATED, oldDto.get(), ret);
            } catch (Throwable e) {
                logger.warn(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * 判断角色分组是否允许保存
     *
     * @param bo
     * @param oldDo
     */
    private void checkRoleGroupCanSave(RoleGroupBo bo, RoleGroupDo oldDo) {
        if (oldDo == null) {
            Assert.isFalse(has(
                    new RoleGroupSearchBo()
                            .setEq_groupName(bo.getGroupName())
            ), ErrorCode.SYS_ROLE_GROUP_NAME_REPEAT);
        } else {
            if (bo.getGroupName() != null && !bo.getGroupName().equals(oldDo.getGroupName())) {
                Assert.isFalse(has(
                        new RoleGroupSearchBo()
                                .setEq_groupName(bo.getGroupName())
                                .setNeq_roleGroupId(oldDo.getRoleGroupId())
                ), ErrorCode.SYS_ROLE_GROUP_NAME_REPEAT);
            }
        }
    }

    /**
     * 判断是否存在符合条件的系统角色分组表
     *
     * @param bo 查询参数
     * @return 系统角色分组表
     */
    public boolean has(RoleGroupSearchBo bo) {
        return roleGroupServer.has(
                RoleGroupWrapper.getQueryWrapper(bo)
        );
    }

}