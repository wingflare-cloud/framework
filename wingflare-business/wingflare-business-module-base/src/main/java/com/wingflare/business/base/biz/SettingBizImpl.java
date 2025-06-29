package com.wingflare.business.base.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.base.ErrorCode;
import com.wingflare.business.base.constants.BaseEventName;
import com.wingflare.business.base.convert.SettingConvert;
import com.wingflare.business.base.db.SettingDo;
import com.wingflare.business.base.service.SettingServer;
import com.wingflare.business.base.wrapper.SettingWrapper;
import com.wingflare.facade.module.base.biz.SettingBiz;
import com.wingflare.facade.module.base.bo.SettingBo;
import com.wingflare.facade.module.base.bo.SettingSearchBo;
import com.wingflare.facade.module.base.dto.SettingDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.EventUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private EventUtil eventUtil;

    private static final Logger logger = LoggerFactory.getLogger(SettingBizImpl.class);

    /**
     * 查询系统设置列表
     */
    @Override
    public PageDto<SettingDto> list(@Valid SettingSearchBo bo) {
        LambdaQueryWrapper<SettingDo> queryWrapper = SettingWrapper.getLambdaQueryWrapper(bo);
        queryWrapper.orderByDesc(SettingDo::getSettingId);

        IPage<SettingDo> iPage = settingServer.page(
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
    public SettingDto get(@Valid @NotNull IdBo bo) {
        return SettingConvert.convert.doToDto(
                settingServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统设置详情
     */
    @Override
    public SettingDto getOnlyOne(@Valid @NotNull SettingSearchBo searchBo) {
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
        SettingDto ret = transactionTemplate.execute(status -> {
            SettingDto settingDto = null;
            SettingDo settingDo = settingServer.getById(bo.getId());

            if (settingDo != null) {
                Assert.isTrue(settingServer.removeById(bo.getId()), ErrorCode.SYS_SETTING_DELETE_ERROR);
                settingDto = SettingConvert.convert.doToDto(settingDo);
                eventUtil.publishEvent(BaseEventName.SETTING_DELETE, settingDto);
            }

            return settingDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(BaseEventName.SETTING_DELETED, val);
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
    public SettingDto create(@Valid @NotNull SettingBo bo) {
        SettingDto ret = transactionTemplate.execute(status -> {
            checkSettingCanSave(bo, null);
            SettingDo settingDo = SettingConvert.convert.boToDo(bo);
            Assert.isTrue(settingServer.save(settingDo), ErrorCode.SYS_SETTING_CREATE_ERROR);
            SettingDto settingDto = SettingConvert.convert.doToDto(settingDo);
            eventUtil.publishEvent(BaseEventName.SETTING_CREATE, bo, settingDto);
            return settingDto;
        });

        try {
            eventUtil.publishEvent(BaseEventName.SETTING_CREATED, bo, ret);
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
    public SettingDto update(@Valid @NotNull SettingBo bo) {
        AtomicReference<SettingDto> oldDto = new AtomicReference<>(null);
        SettingDto ret = transactionTemplate.execute(status -> {
            SettingDo oldSettingDo = settingServer.getById(bo.getSettingId());
            SettingDto settingDto = null;

            if (oldSettingDo == null) {
                throw new DataNotFoundException("setting.data.notfound");
            }

            checkSettingCanSave(bo, oldSettingDo);
            Builder.of(() -> bo)
                    .with(SettingBo::setSettingCode, null)
                    .with(SettingBo::setSystemCode, null)
                    .build();
            SettingDo settingDo = SettingConvert.convert.boToDo(bo);
            SettingDo oldField = oldSettingDo.setOnNew(settingDo);

            if (oldField != null) {
                oldDto.set(SettingConvert.convert.doToDto(oldField));
                Assert.isTrue(settingServer.updateById(oldSettingDo), ErrorCode.SYS_SETTING_UPDATE_ERROR);
                settingDto = SettingConvert.convert.doToDto(oldSettingDo);
                eventUtil.publishEvent(BaseEventName.SETTING_UPDATE, oldDto.get(), settingDto);
            } else {
                settingDto = SettingConvert.convert.doToDto(oldSettingDo);
            }

            return settingDto;
        });

        if (oldDto.get() != null) {
            try {
                eventUtil.publishEvent(BaseEventName.SETTING_UPDATED, oldDto.get(), ret);
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
    public void save(@Valid @NotNull SettingBo bo) {
        String systemCode = SecurityUtil.getBusinessSystem();

        if (StringUtil.isNotEmpty(bo.getSystemCode())) {
            systemCode = bo.getSystemCode();
        }

        if (has(new SettingSearchBo()
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
    private void checkSettingCanSave(SettingBo bo, SettingDo oldDo) {
        if (oldDo == null) {
            Assert.isFalse(has(new SettingSearchBo()
                    .setEq_settingCode(bo.getSettingCode())
                    .setEq_systemCode(bo.getSystemCode())
            ), ErrorCode.SYS_SETTING_REPEAT);
        } else {
            Assert.isFalse(has(new SettingSearchBo()
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
    public boolean has(SettingSearchBo bo) {
        return settingServer.has(
                SettingWrapper.getLambdaQueryWrapper(bo)
        );
    }

}