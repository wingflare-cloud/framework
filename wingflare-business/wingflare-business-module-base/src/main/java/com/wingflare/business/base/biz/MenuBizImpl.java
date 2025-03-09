package com.wingflare.business.base.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.base.ErrorCode;
import com.wingflare.business.base.constants.BaseEventName;
import com.wingflare.business.base.convert.MenuConvert;
import com.wingflare.business.base.db.MenuDo;
import com.wingflare.business.base.service.MenuServer;
import com.wingflare.business.base.wrapper.MenuWrapper;
import com.wingflare.facade.module.base.biz.MenuBiz;
import com.wingflare.facade.module.base.bo.DictSearchBo;
import com.wingflare.facade.module.base.bo.MenuBo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;
import com.wingflare.facade.module.base.dict.DictTypes;
import com.wingflare.facade.module.base.dto.MenuDto;
import com.wingflare.facade.module.base.dto.SimpleMenuDto;
import com.wingflare.lib.core.Assert;
import com.wingflare.lib.core.Builder;
import com.wingflare.lib.core.exceptions.DataNotFoundException;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.core.validation.Create;
import com.wingflare.lib.core.validation.Update;
import com.wingflare.lib.mybatis.plus.utils.PageUtil;
import com.wingflare.lib.standard.EventUtil;
import com.wingflare.lib.standard.PageDto;
import com.wingflare.lib.standard.bo.IdBo;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 系统菜单Biz
 *
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
@Component
@Validated
public class MenuBizImpl implements MenuBiz {

    @Resource
    private MenuServer menuServer;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Resource
    private EventUtil eventUtil;

    private static final Logger logger = LoggerFactory.getLogger(MenuBizImpl.class);

    /**
     * 查询系统菜单列表
     */
    @Override
    public PageDto<MenuDto> list(@Valid MenuSearchBo bo) {
        IPage<MenuDo> iPage = menuServer.page(
                menuServer.createPage(bo),
                MenuWrapper.getQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                MenuConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统菜单详情
     */
    @Override
    public MenuDto get(@Valid @NotNull IdBo bo) {
        return MenuConvert.convert.doToDto(
                menuServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统菜单详情
     */
    @Override
    public MenuDto getOnlyOne(@Valid @NotNull MenuSearchBo searchBo) {
        return MenuConvert.convert.doToDto(
                menuServer.getOne(
                        MenuWrapper.getQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统菜单
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        MenuDto ret = transactionTemplate.execute(status -> {
            MenuDo menuDo = menuServer.getById(bo.getId());
            MenuDto menuDto = null;

            if (menuDo != null) {
                Assert.isFalse(
                        has(new MenuSearchBo()
                                .setEq_parentMenuId(menuDo.getMenuId())
                        ), ErrorCode.SYS_MENU_NOT_DELETE);
                Assert.isTrue(menuServer.removeById(bo.getId()), ErrorCode.SYS_MENU_DELETE_ERROR);
                menuDto = MenuConvert.convert.doToDto(menuDo);
                eventUtil.publishEvent(BaseEventName.MENU_DELETE, menuDto);
            }

            return menuDto;
        });

        Optional.ofNullable(ret)
                .ifPresent(val -> {
                    try {
                        eventUtil.publishEvent(BaseEventName.MENU_DELETED, ret);
                    } catch (Throwable e) {
                        logger.warn(e.getMessage());
                    }
                });
    }

    /**
     * 新增系统菜单
     */
    @Override
    @Validated({Default.class, Create.class})
    public MenuDto create(@Valid @NotNull MenuBo bo) {
        MenuDto ret = transactionTemplate.execute(status -> {
            checkMenuCanSave(bo, null);
            MenuDo menuDo = MenuConvert.convert.boToDo(bo);
            Assert.isTrue(menuServer.save(menuDo), ErrorCode.SYS_MENU_CREATE_ERROR);
            MenuDto menuDto = MenuConvert.convert.doToDto(menuDo);
            eventUtil.publishEvent(BaseEventName.MENU_CREATE, bo, menuDto);
            return menuDto;
        });

        try {
            eventUtil.publishEvent(BaseEventName.MENU_CREATED, bo, ret);
        } catch (Throwable e) {
            logger.warn(e.getMessage());
        }

        return ret;
    }

    /**
     * 更新系统菜单
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Validated({Default.class, Update.class})
    public MenuDto update(@Valid @NotNull MenuBo bo) {
        AtomicReference<MenuDto> oldField = new AtomicReference<>(null);
        MenuDto ret = transactionTemplate.execute(status -> {
            MenuDo oldMenuDo = menuServer.getById(bo.getMenuId());
            MenuDto menuDto = null;

            if (oldMenuDo == null) {
                throw new DataNotFoundException("menu.data.notfound" );
            }

            checkMenuCanSave(bo, oldMenuDo);
            Builder.of(() -> bo)
                    .with(MenuBo::setSystemCode, null)
                    .with(MenuBo::setParentMenuId, null)
                    .with(MenuBo::setMenuType, null)
                    .build();
            MenuDo menuDo = MenuConvert.convert.boToDo(bo);
            MenuDo oldFieldDo = oldMenuDo.setOnNew(menuDo);

            if (oldFieldDo != null) {
                oldField.set(MenuConvert.convert.doToDto(oldFieldDo));
                Assert.isTrue(menuServer.updateById(oldMenuDo), ErrorCode.SYS_MENU_UPDATE_ERROR);
                menuDto = MenuConvert.convert.doToDto(oldMenuDo);
                eventUtil.publishEvent(BaseEventName.MENU_UPDATE, oldField.get(), menuDto);
            } else {
                menuDto = MenuConvert.convert.doToDto(oldMenuDo);
            }
            return menuDto;
        });

        if (oldField.get() != null) {
            try {
                eventUtil.publishEvent(BaseEventName.MENU_UPDATED, oldField.get(), ret);
            } catch (Throwable e) {
                logger.warn(e.getMessage());
            }
        }

        return ret;
    }

    /**
     * 获取树形结构菜单
     *
     * @param systemCode
     * @return
     */
    @Override
    public List<SimpleMenuDto> tree(@NotBlank String systemCode) {
        List<SimpleMenuDto> menuTree = new ArrayList<>();
        List<MenuDo> menuDoList = menuServer
                .list(MenuWrapper.getQueryWrapper(new MenuSearchBo()
                        .setEq_state(OnOffEnum.ON.getValue())
                        .setEq_systemCode(systemCode)));

        if (CollectionUtil.isNotEmpty(menuDoList)) {
            menuDoList.sort((m1, m2) -> (int) (m2.getSort() - m1.getSort()));
            Map<String, List<SimpleMenuDto>> subMenu = new HashMap<>();
            menuDoList.forEach(item -> {
                SimpleMenuDto menuDo = MenuConvert.convert
                        .doToSimpleDto(item);
                if (StringUtil.isEmpty(menuDo.getParentMenuId())) {
                    menuTree.add(menuDo);
                } else {
                    subMenu.computeIfAbsent(menuDo.getParentMenuId(), k -> new ArrayList<>())
                            .add(menuDo);
                }
            });
            formatTree(menuTree, subMenu);
        }

        return menuTree;
    }


    private void formatTree(List<SimpleMenuDto> topTree, Map<String, List<SimpleMenuDto>> subMenu) {
        topTree.forEach(item -> Optional.ofNullable(subMenu.get(item.getMenuId()))
                .ifPresent(v -> {
                    formatTree(v, subMenu);
                    item.setChildren(v);
                }));
    }

    /**
     * 验证菜单是否允许保存
     *
     * @param bo
     */
    private void checkMenuCanSave(MenuBo bo, MenuDo oldDo) {
        if (oldDo == null) {
            Assert.isFalse(has(new MenuSearchBo()
                    .setEq_systemCode(bo.getSystemCode())
                    .setEq_menuName(bo.getMenuName())
                    .setEq_menuType(bo.getMenuType())
                    .setEq_parentMenuId(Optional.ofNullable(bo.getParentMenuId())
                            .orElse("" ))
            ), ErrorCode.SYS_MENU_NAME_REPEAT);

            if (StringUtil.isNotEmpty(bo.getParentMenuId())) {
                Assert.isTrue(menuServer.hasById(bo.getParentMenuId()), ErrorCode.SYS_MENU_PARENT_NOTFOUND);
            }
        } else {
            Assert.isFalse(has(new MenuSearchBo()
                    .setEq_systemCode(oldDo.getSystemCode())
                    .setEq_menuName(bo.getMenuName())
                    .setEq_menuType(oldDo.getMenuType())
                    .setEq_parentMenuId(Optional.ofNullable(oldDo.getParentMenuId())
                            .orElse("" ))
                    .setNeq_menuId(oldDo.getMenuId())
            ), ErrorCode.SYS_MENU_NAME_REPEAT);
        }
    }

    /**
     * 判断是否存在符合条件的系统菜单
     *
     * @param bo 查询参数
     * @return 系统菜单
     */
    public boolean has(MenuSearchBo bo) {
        return menuServer.has(
                MenuWrapper.getQueryWrapper(bo)
        );
    }

}