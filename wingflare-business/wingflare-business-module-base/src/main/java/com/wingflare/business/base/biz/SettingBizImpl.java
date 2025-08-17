package com.wingflare.business.base.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.base.ErrorCode;
import com.wingflare.business.base.convert.SettingConvert;
import com.wingflare.business.base.db.SettingDO;
import com.wingflare.business.base.service.SettingServer;
import com.wingflare.business.base.wrapper.SettingWrapper;
import com.wingflare.facade.module.base.biz.SettingBiz;
import com.wingflare.facade.module.base.bo.SettingBO;
import com.wingflare.facade.module.base.bo.SettingSearchBO;
import com.wingflare.facade.module.base.dto.SettingDTO;
import com.wingflare.facade.module.base.event.SettingCreateEvent;
import com.wingflare.facade.module.base.event.SettingDeleteEvent;
import com.wingflare.facade.module.base.event.SettingDeletedEvent;
import com.wingflare.facade.module.base.event.SettingUpdateEvent;
import com.wingflare.facade.module.base.event.SettingUpdatedEvent;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 系统设置Biz
 *
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
@Component
@Validated
public class SettingBizImpl implements SettingBiz {

    @Resource
    private SettingServer settingServer;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private ApplicationEventPublisher appEventPublisher;

    private static final Logger logger = LoggerFactory.getLogger(SettingBizImpl.class);

    /**
     * 查询系统设置列表
     */
    @Override
    public PageDto<SettingDTO> list(@Valid SettingSearchBO bo) {
        LambdaQueryWrapper<SettingDO> queryWrapper = SettingWrapper.getLambdaQueryWrapper(bo);
        queryWrapper.orderByDesc(SettingDO::getSettingId);

        IPage<SettingDO> iPage = settingServer.page(
                settingServer.createPage(bo),
                queryWrapper
        );

        return PageUtil.convertIPage(iPage,
                SettingConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统设置详情
     */
    @Override
    public SettingDTO get(@Valid @NotNull IdBo bo) {
        return SettingConvert.convert.doToDto(
                settingServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统设置详情
     */
    @Override
    public SettingDTO getOnlyOne(@Valid @NotNull SettingSearchBO searchBo) {
        return SettingConvert.convert.doToDto(
                settingServer.getOne(
                        SettingWrapper.getLambdaQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统设置
     */
    @Override
    public void delete(@Valid @NotNull IdBo bo) {
        SettingDTO ret = transactionTemplate.execute(status -> {
            SettingDTO settingDto = null;
            SettingDO settingDo = settingServer.getById(bo.getId());

            if (settingDo != null) {
                Assert.isTrue(settingServer.removeById(bo.getId()), ErrorCode.SYS_SETTING_DELETE_ERROR);
                settingDto = SettingConvert.convert.doToDto(settingDo);
                appEventPublisher.publishEvent(new SettingDeleteEvent(settingDto));
            }

            return settingDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        appEventPublisher.publishEvent(new SettingDeletedEvent(val));
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });
    }

    /**
     * 新增系统设置
     */
    @Override
    @Validated({Default.class, Create.class})
    public SettingDTO create(@Valid @NotNull SettingBO bo) {
        SettingDTO ret = transactionTemplate.execute(status -> {
            checkSettingCanSave(bo, null);
            SettingDO settingDo = SettingConvert.convert.boToDo(bo);
            Assert.isTrue(settingServer.save(settingDo), ErrorCode.SYS_SETTING_CREATE_ERROR);
            SettingDTO settingDto = SettingConvert.convert.doToDto(settingDo);
            appEventPublisher.publishEvent(new SettingCreateEvent(bo, settingDto));
            return settingDto;
        });

        try {
            appEventPublisher.publishEvent(new SettingCreateEvent(bo, ret));
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }

        return ret;
    }

    /**
     * 更新系统设置
     */
    @Override
    @Validated({Default.class, Update.class})
    public SettingDTO update(@Valid @NotNull SettingBO bo) {
        AtomicReference<SettingDTO> oldDto = new AtomicReference<>(null);
        SettingDTO ret = transactionTemplate.execute(status -> {
            SettingDO oldSettingDO = settingServer.getById(bo.getSettingId());
            SettingDTO settingDto = null;

            if (oldSettingDO == null) {
                throw new DataNotFoundException("setting.data.notfound");
            }

            checkSettingCanSave(bo, oldSettingDO);
            Builder.of(() -> bo)
                    .with(SettingBO::setSettingCode, null)
                    .with(SettingBO::setSystemCode, null)
                    .build();
            SettingDO settingDo = SettingConvert.convert.boToDo(bo);
            SettingDO oldField = oldSettingDO.setOnNew(settingDo);

            if (oldField != null) {
                oldDto.set(SettingConvert.convert.doToDto(oldField));
                settingDto = SettingConvert.convert.doToDto(oldSettingDO);
                appEventPublisher.publishEvent(new SettingUpdateEvent(oldDto.get(), settingDto));
                Assert.isTrue(settingServer.updateById(oldSettingDO), ErrorCode.SYS_SETTING_UPDATE_ERROR);
            } else {
                settingDto = SettingConvert.convert.doToDto(oldSettingDO);
            }

            return settingDto;
        });

        if (oldDto.get() != null) {
            try {
                appEventPublisher.publishEvent(new SettingUpdatedEvent(oldDto.get(), ret));
            } catch (Throwable e) {
                logger.warn(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * 保存并刷新设置
     *
     * @param bo
     */
    @Override
    @Validated({Default.class, Create.class})
    public void save(@Valid @NotNull SettingBO bo) {
        String systemCode = SecurityUtil.getBusinessSystem();

        if (StringUtil.isNotEmpty(bo.getSystemCode())) {
            systemCode = bo.getSystemCode();
        }

        if (has(new SettingSearchBO()
                .setEq_systemCode(systemCode)
                .setEq_settingCode(bo.getSettingCode()))) {
            update(bo);
        } else {
            create(bo);
        }
    }

    /**
     * 验证设置是否允许保存
     *
     * @param bo
     */
    private void checkSettingCanSave(SettingBO bo, SettingDO oldDo) {
        if (oldDo == null) {
            Assert.isFalse(has(new SettingSearchBO()
                    .setEq_settingCode(bo.getSettingCode())
                    .setEq_systemCode(bo.getSystemCode())
            ), ErrorCode.SYS_SETTING_REPEAT);
        } else {
            Assert.isFalse(has(new SettingSearchBO()
                    .setEq_settingCode(bo.getSettingCode())
                    .setEq_systemCode(bo.getSystemCode())
                    .setNeq_settingId(oldDo.getSettingId())
            ), ErrorCode.SYS_SETTING_REPEAT);
        }
    }

    /**
     * 判断是否存在符合条件的系统设置
     *
     * @param bo 查询参数
     * @return 系统设置
     */
    public boolean has(SettingSearchBO bo) {
        return settingServer.has(
                SettingWrapper.getLambdaQueryWrapper(bo)
        );
    }

}