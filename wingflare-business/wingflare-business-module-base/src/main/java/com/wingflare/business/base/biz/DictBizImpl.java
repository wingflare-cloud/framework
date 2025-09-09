package com.wingflare.business.base.biz;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.abstraction.module.base.DictStorage;
import com.wingflare.api.core.PageDto;
import com.wingflare.api.event.EventPublisher;
import com.wingflare.business.base.ErrorCode;
import com.wingflare.business.base.convert.DictConvert;
import com.wingflare.business.base.db.DictDO;
import com.wingflare.business.base.service.DictServer;
import com.wingflare.business.base.wrapper.DictWrapper;
import com.wingflare.facade.module.base.biz.DictBiz;
import com.wingflare.facade.module.base.bo.DictBO;
import com.wingflare.facade.module.base.bo.DictSearchBO;
import com.wingflare.facade.module.base.constants.Base;
import com.wingflare.facade.module.base.dict.DictTypes;
import com.wingflare.facade.module.base.dto.DictDTO;
import com.wingflare.facade.module.base.dto.SimpleDictDTO;
import com.wingflare.facade.module.base.event.DictCreateEvent;
import com.wingflare.facade.module.base.event.DictCreatedEvent;
import com.wingflare.facade.module.base.event.DictDeleteEvent;
import com.wingflare.facade.module.base.event.DictDeletedEvent;
import com.wingflare.facade.module.base.event.DictUpdateEvent;
import com.wingflare.facade.module.base.event.DictUpdatedEvent;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.CacheService;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 系统字典Biz
 *
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
@Validated
public class DictBizImpl implements DictBiz {

    private final DictServer dictServer;

    private final DictStorage dictStorage;

    private final CacheService cacheService;

    private final TransactionTemplate transactionTemplate;

    private final EventPublisher eventPublisher;

    private static final Logger logger = LoggerFactory.getLogger(DictBizImpl.class);

    public DictBizImpl(DictServer dictServer, DictStorage dictStorage, CacheService cacheService,
                       TransactionTemplate transactionTemplate, EventPublisher eventPublisher) {
        this.dictServer = dictServer;
        this.dictStorage = dictStorage;
        this.cacheService = cacheService;
        this.transactionTemplate = transactionTemplate;
        this.eventPublisher = eventPublisher;
    }

    /**
     * 查询系统字典列表
     */
    @Override
    public PageDto<DictDTO> list(@Valid DictSearchBO bo) {
        LambdaQueryWrapper<DictDO> queryWrapper = DictWrapper.getLambdaQueryWrapper(bo);
        queryWrapper.orderByDesc(DictDO::getDictId);

        IPage<DictDO> iPage = dictServer.page(
                dictServer.createPage(bo),
                queryWrapper
        );

        return PageUtil.convertIPage(iPage,
                DictConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统字典详情
     */
    @Override
    public DictDTO get(@Valid @NotNull IdBo bo) {
        return DictConvert.convert.doToDto(
                dictServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统字典详情
     */
    @Override
    public DictDTO getOnlyOne(@Valid @NotNull DictSearchBO searchBo) {
        return DictConvert.convert.doToDto(
                dictServer.getOne(
                        DictWrapper.getLambdaQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统字典
     */
    @Override
    public void delete(@Valid @NotNull IdBo bo) {
        DictDTO ret = transactionTemplate.execute(status -> {
            DictDTO dictDto = null;
            DictDO dictDo = dictServer.getById(bo.getId());
            if (dictDo != null) {
                if (DictTypes.DIRECTORY.getValue().equals(dictDo.getDictType())) {
                    Assert.isFalse(
                            has(new DictSearchBO()
                                    .setEq_dictCode(dictDo.getDictCode())
                                    .setEq_dictType(DictTypes.ELEMENT.getValue())
                            ), ErrorCode.SYS_DICT_NOT_DELETE);
                }
                Assert.isTrue(dictServer.removeById(bo.getId()), ErrorCode.SYS_DICT_DELETE_ERROR);
                dictDto = DictConvert.convert.doToDto(dictDo);
                eventPublisher.publishEvent(new DictDeleteEvent(dictDto));
            }
            return dictDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        eventPublisher.publishEvent(new DictDeletedEvent(val));
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });
    }

    /**
     * 新增系统字典
     */
    @Override
    @Validated({Default.class, Create.class})
    public DictDTO create(@Valid @NotNull DictBO bo) {
        DictDTO ret = transactionTemplate.execute(status -> {
            checkDictCanSave(bo, null);
            DictDO dictDo = DictConvert.convert.boToDo(bo);
            Assert.isTrue(dictServer.save(dictDo), ErrorCode.SYS_DICT_CREATE_ERROR);
            DictDTO dictDto = DictConvert.convert.doToDto(dictDo);
            eventPublisher.publishEvent(new DictCreateEvent(bo, dictDto));
            return dictDto;
        });

        try {
            eventPublisher.publishEvent(new DictCreatedEvent(bo, ret));
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }

        return ret;
    }

    /**
     * 更新系统字典
     */
    @Override
    @Validated({Default.class, Update.class})
    public DictDTO update(@Valid @NotNull DictBO bo) {
        AtomicReference<DictDTO> oldDictDto = new AtomicReference<>(null);
        DictDTO ret = transactionTemplate.execute(status -> {
            DictDO oldDictDO = dictServer.getById(bo.getDictId());
            DictDTO dictDto = null;

            if (oldDictDO == null) {
                throw new DataNotFoundException("dict.data.notfound");
            }

            Builder.of(() -> bo)
                    .with(DictBO::setDictCode, null)
                    .with(DictBO::setDictType, null)
                    .build();
            checkDictCanSave(bo, oldDictDO);
            DictDO dictDo = DictConvert.convert.boToDo(bo);
            DictDO oldField = oldDictDO.setOnNew(dictDo);

            if (oldField != null) {
                oldDictDto.set(DictConvert.convert.doToDto(oldField));
                Assert.isTrue(dictServer.updateById(oldDictDO), ErrorCode.SYS_DICT_UPDATE_ERROR);
                dictDto = DictConvert.convert.doToDto(oldDictDO);
                eventPublisher.publishEvent(new DictUpdateEvent(oldDictDto.get(), dictDto));
            } else {
                dictDto = DictConvert.convert.doToDto(oldDictDO);
            }

            return dictDto;
        });

        if (oldDictDto.get() != null) {
            try {
                eventPublisher.publishEvent(new DictUpdatedEvent(oldDictDto.get(), ret));
            } catch (Throwable e) {
                logger.warn(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * 刷新系统字典
     */
    @Override
    public void refresh() {
        List<DictDO> dictDOList = dictServer
                .list(DictWrapper.getLambdaQueryWrapper(new DictSearchBO()
                        .setEq_state(OnOffEnum.ON.getValue())
                ));

        List<SimpleDictDTO> simpleDictDTOList = new ArrayList<>();
        List<String> enableDictCode = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(dictDOList)) {
            dictDOList.forEach((item) -> {
                if (DictTypes.DIRECTORY.getValue().equals(item.getDictType())
                        && OnOffEnum.ON.getValue().equals(item.getState())) {
                    enableDictCode.add(item.getDictCode());
                }
            });

            dictDOList.forEach(item -> {
                if (OnOffEnum.ON.getValue().equals(item.getState())) {
                    if (DictTypes.ELEMENT.getValue().equals(item.getDictType())) {
                        if (enableDictCode.contains(item.getDictCode())) {
                            simpleDictDTOList.add(DictConvert.convert.doToSimpleDto(item));
                        }
                    } else {
                        simpleDictDTOList.add(DictConvert.convert.doToSimpleDto(item));
                    }
                }
            });

            simpleDictDTOList.sort((d1, d2) -> (d2.getSort() - d1.getSort()));
            Long flag = dictStorage.save(simpleDictDTOList.toArray(new SimpleDictDTO[0]));
            Assert.isTrue(flag != null && flag.compareTo(0L) > 0, ErrorCode.SYS_DICT_REFRESH_ERROR);
        }
    }

    /**
     * 从缓存中取出全部字典数据
     *
     * @return
     */
    @Override
    public List<SimpleDictDTO> getAllDictByCache() {
        return cacheService.getCacheList(Base.DICT_CACHE_KEY);
    }

    /**
     * 验证字典是否允许保存
     *
     * @param bo
     */
    private void checkDictCanSave(DictBO bo, DictDO oldDo) {
        if (oldDo == null) {
            Assert.isFalse(has(
                    new DictSearchBO()
                            .setEq_dictType(bo.getDictType())
                            .setEq_dictCode(bo.getDictCode())
                            .setEq_dictName(bo.getDictName())
            ), ErrorCode.SYS_DICT_NAME_REPEAT);

            Assert.isFalse(has(
                    new DictSearchBO()
                            .setEq_dictType(bo.getDictType())
                            .setEq_dictCode(bo.getDictCode())
                            .setEq_dictValue(bo.getDictValue())
            ), ErrorCode.SYS_DICT_REPEAT);

            if (DictTypes.DIRECTORY.getValue().equals(bo.getDictType())) {
                Assert.isFalse(has(
                        new DictSearchBO()
                                .setEq_dictCode(bo.getDictCode())
                                .setEq_dictType(bo.getDictType())
                ), ErrorCode.SYS_DICT_REPEAT);
            } else {
                Assert.isTrue(has(
                        new DictSearchBO()
                                .setEq_dictCode(bo.getDictCode())
                                .setEq_dictType(DictTypes.DIRECTORY.getValue())
                ), () -> new BusinessLogicException(ErrorCode.SYS_DICT_NOTFOUND, bo.getDictCode()));
            }
        } else {
            Optional.ofNullable(bo.getDictName())
                    .ifPresent(name -> {
                        if (!name.equals(oldDo.getDictName())) {
                            Assert.isFalse(has(
                                    new DictSearchBO()
                                            .setEq_dictType(oldDo.getDictType())
                                            .setEq_dictCode(oldDo.getDictCode())
                                            .setEq_dictName(bo.getDictName())
                                            .setNeq_dictId(oldDo.getDictId())
                            ), ErrorCode.SYS_DICT_NAME_REPEAT);
                        }
                    });

            Optional.ofNullable(bo.getDictValue())
                    .ifPresent(value -> {
                        if (!value.equals(oldDo.getDictValue())) {
                            Assert.isFalse(has(
                                    new DictSearchBO()
                                            .setEq_dictType(oldDo.getDictType())
                                            .setEq_dictCode(oldDo.getDictCode())
                                            .setEq_dictValue(bo.getDictValue())
                                            .setNeq_dictId(oldDo.getDictId())
                            ), ErrorCode.SYS_DICT_REPEAT);
                        }
                    });
        }
    }

    /**
     * 判断是否存在符合条件的系统字典
     *
     * @param bo 查询参数
     * @return 系统字典
     */
    public boolean has(DictSearchBO bo) {
        return dictServer.has(
                DictWrapper.getLambdaQueryWrapper(bo)
        );
    }

}