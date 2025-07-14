package com.wingflare.business.base.biz;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wingflare.business.base.ErrorCode;
import com.wingflare.business.base.constants.BaseEventName;
import com.wingflare.business.base.convert.MenuConvert;
import com.wingflare.business.base.db.MenuDO;
import com.wingflare.business.base.service.MenuServer;
import com.wingflare.business.base.wrapper.MenuWrapper;
import com.wingflare.facade.module.base.biz.MenuBiz;
import com.wingflare.facade.module.base.bo.MenuBO;
import com.wingflare.facade.module.base.bo.MenuSearchBO;
import com.wingflare.facade.module.base.bo.PermissionCodesExistBO;
import com.wingflare.facade.module.base.dto.MenuDTO;
import com.wingflare.facade.module.base.dto.SimpleMenuDTO;
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
    public PageDto<MenuDTO> list(@Valid MenuSearchBO bo) {
        IPage<MenuDO> iPage = menuServer.page(
                menuServer.createPage(bo),
                MenuWrapper.getLambdaQueryWrapper(bo)
        );

        return PageUtil.convertIPage(iPage,
                MenuConvert.convert.doToDtoList(iPage.getRecords()));
    }

    /**
     * 查询系统菜单详情
     */
    @Override
    public MenuDTO get(@Valid @NotNull IdBo bo) {
        return MenuConvert.convert.doToDto(
                menuServer.getById(bo.getId()));
    }

    /**
     * 通过条件查询单个系统菜单详情
     */
    @Override
    public MenuDTO getOnlyOne(@Valid @NotNull MenuSearchBO searchBo) {
        return MenuConvert.convert.doToDto(
                menuServer.getOne(
                        MenuWrapper.getLambdaQueryWrapper(searchBo)
                ));
    }

    /**
     * 删除系统菜单
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(@Valid @NotNull IdBo bo) {
        MenuDTO ret = transactionTemplate.execute(status -> {
            MenuDO menuDo = menuServer.getById(bo.getId());
            MenuDTO menuDto = null;

            if (menuDo != null) {
                Assert.isFalse(
                        has(new MenuSearchBO()
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
    public MenuDTO create(@Valid @NotNull MenuBO bo) {
        MenuDTO ret = transactionTemplate.execute(status -> {
            checkMenuCanSave(bo, null);
            MenuDO menuDo = MenuConvert.convert.boToDo(bo);
            Assert.isTrue(menuServer.save(menuDo), ErrorCode.SYS_MENU_CREATE_ERROR);
            MenuDTO menuDto = MenuConvert.convert.doToDto(menuDo);
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
    public MenuDTO update(@Valid @NotNull MenuBO bo) {
        AtomicReference<MenuDTO> oldField = new AtomicReference<>(null);
        MenuDTO ret = transactionTemplate.execute(status -> {
            MenuDO oldMenuDO = menuServer.getById(bo.getMenuId());
            MenuDTO menuDto = null;

            if (oldMenuDO == null) {
                throw new DataNotFoundException("menu.data.notfound");
            }

            checkMenuCanSave(bo, oldMenuDO);
            Builder.of(() -> bo)
                    .with(MenuBO::setSystemCode, null)
                    .with(MenuBO::setParentMenuId, null)
                    .with(MenuBO::setMenuType, null)
                    .build();
            MenuDO menuDo = MenuConvert.convert.boToDo(bo);
            MenuDO oldFieldDo = oldMenuDO.setOnNew(menuDo);

            if (oldFieldDo != null) {
                oldField.set(MenuConvert.convert.doToDto(oldFieldDo));
                Assert.isTrue(menuServer.updateById(oldMenuDO), ErrorCode.SYS_MENU_UPDATE_ERROR);
                menuDto = MenuConvert.convert.doToDto(oldMenuDO);
                eventUtil.publishEvent(BaseEventName.MENU_UPDATE, oldField.get(), menuDto);
            } else {
                menuDto = MenuConvert.convert.doToDto(oldMenuDO);
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
     * @param searchBo
     * @return
     */
    @Override
    public List<SimpleMenuDTO> tree(@Valid @NotNull MenuSearchBO searchBo) {
        List<SimpleMenuDTO> menuTree = new ArrayList<>();
        List<MenuDO> menuDOList = menuServer
                .list(MenuWrapper.getLambdaQueryWrapper(searchBo));

        if (CollectionUtil.isNotEmpty(menuDOList)) {
            menuDOList.sort((m1, m2) -> (int) (m2.getSort() - m1.getSort()));
            Map<String, List<SimpleMenuDTO>> subMenu = new HashMap<>();
            menuDOList.forEach(item -> {
                SimpleMenuDTO menuDo = MenuConvert.convert
                        .doToSimpleDto(item);
                if (StringUtil.isEmpty(menuDo.getParentMenuId()) || menuDo.getParentMenuId().equals("0")) {
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

    /**
     * 判断代码权限是否存在
     *
     * @param existBo
     * @return
     */
    @Override
    public Boolean permissionCodesExist(@Valid @NotNull PermissionCodesExistBO existBo) {
        for (int i = 0; i < existBo.getCodes().size(); i++) {
            if (menuServer.count(MenuWrapper.getLambdaQueryWrapper(new MenuSearchBO().setEq_systemCode(existBo.getCodes().get(i).getSystemCode())
                    .setIn_permissionCode(StringUtil.join(existBo.getCodes().get(i).getCodes())))) != existBo.getCodes().get(i).getCodes().size()
            ) {
                return false;
            }
        }

        return true;
    }


    private void formatTree(List<SimpleMenuDTO> topTree, Map<String, List<SimpleMenuDTO>> subMenu) {
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
    private void checkMenuCanSave(MenuBO bo, MenuDO oldDo) {
        if (oldDo == null) {
            Assert.isFalse(has(new MenuSearchBO()
                    .setEq_systemCode(bo.getSystemCode())
                    .setEq_menuName(bo.getMenuName())
                    .setEq_menuType(bo.getMenuType())
                    .setEq_parentMenuId(Optional.ofNullable(bo.getParentMenuId())
                            .orElse(BigInteger.ZERO))
            ), ErrorCode.SYS_MENU_NAME_REPEAT);

            if (bo.getParentMenuId() != null && bo.getParentMenuId().compareTo(BigInteger.ZERO) > 0) {
                Assert.isTrue(menuServer.hasById(bo.getParentMenuId()), ErrorCode.SYS_MENU_PARENT_NOTFOUND);
            }
        } else {
            Assert.isFalse(has(new MenuSearchBO()
                    .setEq_systemCode(oldDo.getSystemCode())
                    .setEq_menuName(bo.getMenuName())
                    .setEq_menuType(oldDo.getMenuType())
                    .setEq_parentMenuId(Optional.ofNullable(oldDo.getParentMenuId())
                            .orElse(BigInteger.ZERO))
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
    public boolean has(MenuSearchBO bo) {
        return menuServer.has(
                MenuWrapper.getLambdaQueryWrapper(bo)
        );
    }

}