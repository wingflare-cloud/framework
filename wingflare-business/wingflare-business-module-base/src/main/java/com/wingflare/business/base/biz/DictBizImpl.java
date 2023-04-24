package com.wingflare.business.base.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.abstraction.module.base.DictStorage;
import com.wingflare.business.base.ErrorCode;
import com.wingflare.business.base.constants.BaseEventName;
import com.wingflare.business.base.convert.DictConvert;
import com.wingflare.business.base.db.DictDo;
import com.wingflare.business.base.service.DictServer;
import com.wingflare.business.base.wrapper.DictWrapper;
import com.wingflare.facade.module.base.biz.DictBiz;
import com.wingflare.facade.module.base.bo.DictBo;
import com.wingflare.facade.module.base.bo.DictSearchBo;
import com.wingflare.facade.module.base.constants.Base;
import com.wingflare.facade.module.base.dict.DictTypes;
import com.wingflare.facade.module.base.dto.DictDto;
import com.wingflare.facade.module.base.dto.SimpleDictDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.CacheService;
import com.wingflare.lib.standard.EventUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
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
@Component
@Validated
public class DictBizImpl implements DictBiz {

    @Resource
    private DictServer dictServer;

    @Resource
    private DictStorage dictStorage;

    @Resource
    private CacheService cacheService;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private EventUtil eventUtil;

    private static final Logger logger = LoggerFactory.getLogger(DictBizImpl.class);

    /**
     * 查询系统字典列表
     */
    @Override
    public PageDto<DictDto> list(@Valid DictSearchBo bo) {
        IPage<DictDo> iPage = dictServer.page(
                dictServer.createPage(bo),
                DictWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                DictConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统字典详情
     */
    @Override
    public DictDto get(@Valid @NotNull IdBo bo) {
        return DictConvert.convert.doToDto(
                dictServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统字典详情
     */
    @Override
    public DictDto getOnlyOne(@Valid @NotNull DictSearchBo searchBo) {
        return DictConvert.convert.doToDto(
                dictServer.getOne(
                        DictWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统字典
     */
    @Override
    public void delete(@Valid @NotNull IdBo bo) {
        DictDto ret = transactionTemplate.execute(status -> {
            DictDto dictDto = null;
            DictDo dictDo = dictServer.getById(bo.getId());
            if (dictDo != null) {
                if (DictTypes.DIRECTORY.getValue().equals(dictDo.getDictType())) {
                    Assert.isFalse(
                            has(new DictSearchBo()
                                    .setEq_systemCode(dictDo.getSystemCode())
                                    .setEq_dictCode(dictDo.getDictCode())
                                    .setEq_dictType(DictTypes.ELEMENT.getValue())
                            ), ErrorCode.SYS_DICT_NOT_DELETE);
                }
                Assert.isTrue(dictServer.removeById(bo.getId()), ErrorCode.SYS_DICT_DELETE_ERROR);
                dictDto = DictConvert.convert.doToDto(dictDo);
                eventUtil.publishEvent(BaseEventName.DICT_DELETE, dictDto);
            }
            return dictDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(BaseEventName.DICT_DELETED, val);
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
    public DictDto create(@Valid @NotNull DictBo bo) {
        DictDto ret = transactionTemplate.execute(status -> {
            checkDictCanSave(bo, null);
            DictDo dictDo = DictConvert.convert.boToDo(bo);
            Assert.isTrue(dictServer.save(dictDo), ErrorCode.SYS_DICT_CREATE_ERROR);
            DictDto dictDto = DictConvert.convert.doToDto(dictDo);
            eventUtil.publishEvent(BaseEventName.DICT_CREATE, bo, dictDto);
            return dictDto;
        });

        try {
            eventUtil.publishEvent(BaseEventName.DICT_CREATED, bo, ret);
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
    public DictDto update(@Valid @NotNull DictBo bo) {
        AtomicReference<DictDto> oldDictDto = new AtomicReference<>(null);
        DictDto ret = transactionTemplate.execute(status -> {
            DictDo oldDictDo = dictServer.getById(bo.getDictId());
            DictDto dictDto = null;

            if (oldDictDo == null) {
                throw new DataNotFoundException("dict.data.notfound" );
            }

            Builder.of(() -> bo)
                    .with(DictBo::setDictCode, null)
                    .with(DictBo::setDictType, null)
                    .with(DictBo::setSystemCode, null)
                    .build();
            checkDictCanSave(bo, oldDictDo);
            DictDo dictDo = DictConvert.convert.boToDo(bo);
            DictDo oldField = oldDictDo.setOnNew(dictDo);

            if (oldField != null) {
                oldDictDto.set(DictConvert.convert.doToDto(oldField));
                Assert.isTrue(dictServer.updateById(oldDictDo), ErrorCode.SYS_DICT_UPDATE_ERROR);
                dictDto = DictConvert.convert.doToDto(oldDictDo);
                eventUtil.publishEvent(BaseEventName.DICT_UPDATE, oldDictDto.get(), dictDto);
            } else {
                dictDto = DictConvert.convert.doToDto(oldDictDo);
            }

            return dictDto;
        });

        if (oldDictDto.get() != null) {
            try {
                eventUtil.publishEvent(BaseEventName.DICT_UPDATED, oldDictDto.get(), ret);
            } catch (Throwable e) {
                logger.warn(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * 刷新系统字典
     *
     * @param systemCode
     */
    @Override
    public void refresh(@NotBlank String systemCode) {
        List<DictDo> dictDoList = dictServer
                .list(DictWrapper.getQueryWrapper(new DictSearchBo()
                        .setEq_systemCode(systemCode)
                        .setEq_state(OnOffEnum.ON.getValue())
                ));

        List<SimpleDictDto> simpleDictDtoList = new ArrayList<>();
        List<String> enableDictCode = new ArrayList<>();

        if (CollectionUtil.isNotEmpty(dictDoList)) {
            dictDoList.forEach((item) -> {
                if (DictTypes.DIRECTORY.getValue().equals(item.getDictType())
                        && OnOffEnum.ON.getValue().equals(item.getState())) {
                    enableDictCode.add(item.getDictCode());
                }
            });

            dictDoList.forEach(item -> {
                if (OnOffEnum.ON.getValue().equals(item.getState())) {
                    if (DictTypes.ELEMENT.getValue().equals(item.getDictType())) {
                        if (enableDictCode.contains(item.getDictCode())) {
                            simpleDictDtoList.add(DictConvert.convert.doToSimpleDto(item));
                        }
                    } else {
                        simpleDictDtoList.add(DictConvert.convert.doToSimpleDto(item));
                    }
                }
            });

            simpleDictDtoList.sort((d1, d2) -> (d2.getSort() - d1.getSort()));
            Long flag = dictStorage.save(systemCode, simpleDictDtoList.toArray(new SimpleDictDto[0]));
            Assert.isTrue(flag != null && flag.compareTo(0L) > 0, ErrorCode.SYS_DICT_REFRESH_ERROR);
        }
    }

    /**
     * 从缓存中取出全部字典数据
     *
     * @return
     */
    @Override
    public List<SimpleDictDto> getAllDictByCache(@NotBlank String systemCode) {
        return cacheService.getCacheList(String.format("%s:%s" , Base.DICT_CACHE_KEY, systemCode));
    }

    /**
     * 验证字典是否允许保存
     *
     * @param bo
     */
    private void checkDictCanSave(DictBo bo, DictDo oldDo) {
        if (oldDo == null) {
            Assert.isFalse(has(
                    new DictSearchBo()
                            .setEq_systemCode(bo.getSystemCode())
                            .setEq_dictType(bo.getDictType())
                            .setEq_dictCode(bo.getDictCode())
                            .setEq_dictName(bo.getDictName())
            ), ErrorCode.SYS_DICT_NAME_REPEAT);

            if (DictTypes.DIRECTORY.getValue().equals(bo.getDictType())) {
                Assert.isFalse(has(
                        new DictSearchBo()
                                .setEq_systemCode(bo.getSystemCode())
                                .setEq_dictCode(bo.getDictCode())
                                .setEq_dictType(bo.getDictType())
                ), ErrorCode.SYS_DICT_REPEAT);
            } else {
                Assert.isTrue(has(
                        new DictSearchBo()
                                .setEq_systemCode(bo.getSystemCode())
                                .setEq_dictCode(bo.getDictCode())
                                .setEq_dictType(DictTypes.DIRECTORY.getValue())
                ), () -> new BusinessLogicException(ErrorCode.SYS_DICT_NOTFOUND, bo.getDictCode()));
            }
        } else {
            Optional.ofNullable(bo.getDictName())
                    .ifPresent(name -> {
                        if (!name.equals(oldDo.getDictName())) {
                            Assert.isFalse(has(
                                    new DictSearchBo()
                                            .setEq_systemCode(oldDo.getSystemCode())
                                            .setEq_dictType(oldDo.getDictType())
                                            .setEq_dictCode(oldDo.getDictCode())
                                            .setEq_dictName(bo.getDictName())
                                            .setNeq_dictId(oldDo.getDictId())
                            ), ErrorCode.SYS_DICT_NAME_REPEAT);
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
    public boolean has(DictSearchBo bo) {
        return dictServer.has(
                DictWrapper.getQueryWrapper(bo)
        );
    }

}