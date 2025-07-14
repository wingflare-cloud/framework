package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.business.base.db.MenuDO;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.facade.module.base.bo.MenuSearchBO;

/**
 * 系统菜单表 Wrapper
 *
 * @author naizui_ycx
 * @date ${datetime}
 */
public class MenuWrapper
{

	
	/**
     * 获取LambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	public static LambdaQueryWrapper<MenuDO> getLambdaQueryWrapper(MenuSearchBO bo) {
        LambdaQueryWrapper<MenuDO> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, MenuDO::getIsDelete, bo.getDataScope());

        // menu_id 开始
        if (bo.getEq_menuId() != null) {
            queryWrapper.eq(MenuDO::getMenuId, bo.getEq_menuId());
        }
        if (bo.getNeq_menuId() != null) {
            queryWrapper.ne(MenuDO::getMenuId, bo.getNeq_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuId())) {
            queryWrapper.like(MenuDO::getMenuId, bo.getLike_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuId())) {
            queryWrapper.likeRight(MenuDO::getMenuId, bo.getLiker_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuId())) {
            queryWrapper.likeLeft(MenuDO::getMenuId, bo.getLikel_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuId())) {
            queryWrapper.notLike(MenuDO::getMenuId, bo.getNotlike_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuId())) {
            WrapperUtil.in(queryWrapper, MenuDO::getMenuId, bo.getIn_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuId())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getMenuId, bo.getNotin_menuId());
        }
        // parent_menu_id 开始
        if (bo.getEq_parentMenuId() != null) {
            queryWrapper.eq(MenuDO::getParentMenuId, bo.getEq_parentMenuId());
        }
        if (bo.getNeq_parentMenuId() != null) {
            queryWrapper.ne(MenuDO::getParentMenuId, bo.getNeq_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentMenuId())) {
            queryWrapper.like(MenuDO::getParentMenuId, bo.getLike_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentMenuId())) {
            queryWrapper.likeRight(MenuDO::getParentMenuId, bo.getLiker_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentMenuId())) {
            queryWrapper.likeLeft(MenuDO::getParentMenuId, bo.getLikel_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentMenuId())) {
            queryWrapper.notLike(MenuDO::getParentMenuId, bo.getNotlike_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentMenuId())) {
            WrapperUtil.in(queryWrapper, MenuDO::getParentMenuId, bo.getIn_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentMenuId())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getParentMenuId, bo.getNotin_parentMenuId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(MenuDO::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(MenuDO::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(MenuDO::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(MenuDO::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(MenuDO::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(MenuDO::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, MenuDO::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, MenuDO::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, MenuDO::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getState, bo.getNotin_state());
        }
        // hide 开始
        if (bo.getEq_hide() != null) {
            queryWrapper.eq(MenuDO::getHide, bo.getEq_hide());
        }
        if (bo.getNeq_hide() != null) {
            queryWrapper.ne(MenuDO::getHide, bo.getNeq_hide());
        }
        if (bo.getGt_hide() != null) {
            queryWrapper.gt(MenuDO::getHide, bo.getGt_hide());
        }
        if (bo.getLt_hide() != null) {
            queryWrapper.lt(MenuDO::getHide, bo.getLt_hide());
        }
        if (bo.getEgt_hide() != null) {
            queryWrapper.ge(MenuDO::getHide, bo.getEgt_hide());
        }
        if (bo.getElt_hide() != null) {
            queryWrapper.le(MenuDO::getHide, bo.getElt_hide());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_hide())) {
            WrapperUtil.between(queryWrapper, MenuDO::getHide, bo.getBetween_hide());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_hide())) {
            WrapperUtil.notBetween(queryWrapper, MenuDO::getHide, bo.getNotbetween_hide());
        }
        if (StringUtil.isNotEmpty(bo.getIn_hide())) {
            WrapperUtil.in(queryWrapper, MenuDO::getHide, bo.getIn_hide());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_hide())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getHide, bo.getNotin_hide());
        }
        // constant 开始
        if (bo.getEq_constant() != null) {
            queryWrapper.eq(MenuDO::getConstant, bo.getEq_constant());
        }
        if (bo.getNeq_constant() != null) {
            queryWrapper.ne(MenuDO::getConstant, bo.getNeq_component());
        }
        // multi_tab 开始
        if (bo.getEq_multiTab() != null) {
            queryWrapper.eq(MenuDO::getMultiTab, bo.getEq_multiTab());
        }
        if (bo.getNeq_multiTab() != null) {
            queryWrapper.ne(MenuDO::getMultiTab, bo.getNeq_multiTab());
        }
        if (bo.getGt_multiTab() != null) {
            queryWrapper.gt(MenuDO::getMultiTab, bo.getGt_multiTab());
        }
        if (bo.getLt_multiTab() != null) {
            queryWrapper.lt(MenuDO::getMultiTab, bo.getLt_multiTab());
        }
        if (bo.getEgt_multiTab() != null) {
            queryWrapper.ge(MenuDO::getMultiTab, bo.getEgt_multiTab());
        }
        if (bo.getElt_multiTab() != null) {
            queryWrapper.le(MenuDO::getMultiTab, bo.getElt_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_multiTab())) {
            WrapperUtil.between(queryWrapper, MenuDO::getMultiTab, bo.getBetween_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_multiTab())) {
            WrapperUtil.notBetween(queryWrapper, MenuDO::getMultiTab, bo.getNotbetween_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getIn_multiTab())) {
            WrapperUtil.in(queryWrapper, MenuDO::getMultiTab, bo.getIn_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_multiTab())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getMultiTab, bo.getNotin_multiTab());
        }
        // menu_type 开始
        if (bo.getEq_menuType() != null) {
            queryWrapper.eq(MenuDO::getMenuType, bo.getEq_menuType());
        }
        if (bo.getNeq_menuType() != null) {
            queryWrapper.ne(MenuDO::getMenuType, bo.getNeq_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuType())) {
            queryWrapper.like(MenuDO::getMenuType, bo.getLike_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuType())) {
            queryWrapper.likeRight(MenuDO::getMenuType, bo.getLiker_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuType())) {
            queryWrapper.likeLeft(MenuDO::getMenuType, bo.getLikel_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuType())) {
            queryWrapper.notLike(MenuDO::getMenuType, bo.getNotlike_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuType())) {
            WrapperUtil.in(queryWrapper, MenuDO::getMenuType, bo.getIn_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuType())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getMenuType, bo.getNotin_menuType());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(MenuDO::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(MenuDO::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(MenuDO::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(MenuDO::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(MenuDO::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(MenuDO::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, MenuDO::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getSystemCode, bo.getNotin_systemCode());
        }
        // permission_code 开始
        if (bo.getEq_permissionCode() != null) {
            queryWrapper.eq(MenuDO::getPermissionCode, bo.getEq_permissionCode());
        }
        if (bo.getNeq_permissionCode() != null) {
            queryWrapper.ne(MenuDO::getPermissionCode, bo.getNeq_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_permissionCode())) {
            queryWrapper.like(MenuDO::getPermissionCode, bo.getLike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_permissionCode())) {
            queryWrapper.likeRight(MenuDO::getPermissionCode, bo.getLiker_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_permissionCode())) {
            queryWrapper.likeLeft(MenuDO::getPermissionCode, bo.getLikel_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_permissionCode())) {
            queryWrapper.notLike(MenuDO::getPermissionCode, bo.getNotlike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_permissionCode())) {
            WrapperUtil.in(queryWrapper, MenuDO::getPermissionCode, bo.getIn_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_permissionCode())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getPermissionCode, bo.getNotin_permissionCode());
        }
        // menu_name 开始
        if (bo.getEq_menuName() != null) {
            queryWrapper.eq(MenuDO::getMenuName, bo.getEq_menuName());
        }
        if (bo.getNeq_menuName() != null) {
            queryWrapper.ne(MenuDO::getMenuName, bo.getNeq_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuName())) {
            queryWrapper.like(MenuDO::getMenuName, bo.getLike_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuName())) {
            queryWrapper.likeRight(MenuDO::getMenuName, bo.getLiker_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuName())) {
            queryWrapper.likeLeft(MenuDO::getMenuName, bo.getLikel_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuName())) {
            queryWrapper.notLike(MenuDO::getMenuName, bo.getNotlike_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuName())) {
            WrapperUtil.in(queryWrapper, MenuDO::getMenuName, bo.getIn_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuName())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getMenuName, bo.getNotin_menuName());
        }
        // i18n_key 开始
        if (bo.getEq_langKey() != null) {
            queryWrapper.eq(MenuDO::getLangKey, bo.getEq_langKey());
        }
        if (bo.getNeq_langKey() != null) {
            queryWrapper.ne(MenuDO::getLangKey, bo.getNeq_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLike_langKey())) {
            queryWrapper.like(MenuDO::getLangKey, bo.getLike_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_langKey())) {
            queryWrapper.likeRight(MenuDO::getLangKey, bo.getLiker_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_langKey())) {
            queryWrapper.likeLeft(MenuDO::getLangKey, bo.getLikel_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_langKey())) {
            queryWrapper.notLike(MenuDO::getLangKey, bo.getNotlike_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getIn_langKey())) {
            WrapperUtil.in(queryWrapper, MenuDO::getLangKey, bo.getIn_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_langKey())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getLangKey, bo.getNotin_langKey());
        }
        // menu_icon 开始
        if (bo.getEq_menuIcon() != null) {
            queryWrapper.eq(MenuDO::getMenuIcon, bo.getEq_menuIcon());
        }
        if (bo.getNeq_menuIcon() != null) {
            queryWrapper.ne(MenuDO::getMenuIcon, bo.getNeq_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuIcon())) {
            queryWrapper.like(MenuDO::getMenuIcon, bo.getLike_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuIcon())) {
            queryWrapper.likeRight(MenuDO::getMenuIcon, bo.getLiker_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuIcon())) {
            queryWrapper.likeLeft(MenuDO::getMenuIcon, bo.getLikel_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuIcon())) {
            queryWrapper.notLike(MenuDO::getMenuIcon, bo.getNotlike_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuIcon())) {
            WrapperUtil.in(queryWrapper, MenuDO::getMenuIcon, bo.getIn_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuIcon())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getMenuIcon, bo.getNotin_menuIcon());
        }
        // icon_type 开始
        if (bo.getEq_iconType() != null) {
            queryWrapper.eq(MenuDO::getIconType, bo.getEq_iconType());
        }
        if (bo.getNeq_iconType() != null) {
            queryWrapper.ne(MenuDO::getIconType, bo.getNeq_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_iconType())) {
            queryWrapper.like(MenuDO::getIconType, bo.getLike_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_iconType())) {
            queryWrapper.likeRight(MenuDO::getIconType, bo.getLiker_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_iconType())) {
            queryWrapper.likeLeft(MenuDO::getIconType, bo.getLikel_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_iconType())) {
            queryWrapper.notLike(MenuDO::getIconType, bo.getNotlike_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_iconType())) {
            WrapperUtil.in(queryWrapper, MenuDO::getIconType, bo.getIn_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_iconType())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getIconType, bo.getNotin_iconType());
        }
        // route_name 开始
        if (bo.getEq_routeName() != null) {
            queryWrapper.eq(MenuDO::getRouteName, bo.getEq_routeName());
        }
        if (bo.getNeq_routeName() != null) {
            queryWrapper.ne(MenuDO::getRouteName, bo.getNeq_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_routeName())) {
            queryWrapper.like(MenuDO::getRouteName, bo.getLike_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_routeName())) {
            queryWrapper.likeRight(MenuDO::getRouteName, bo.getLiker_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_routeName())) {
            queryWrapper.likeLeft(MenuDO::getRouteName, bo.getLikel_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_routeName())) {
            queryWrapper.notLike(MenuDO::getRouteName, bo.getNotlike_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_routeName())) {
            WrapperUtil.in(queryWrapper, MenuDO::getRouteName, bo.getIn_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_routeName())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getRouteName, bo.getNotin_routeName());
        }
        // route_path 开始
        if (bo.getEq_routePath() != null) {
            queryWrapper.eq(MenuDO::getRoutePath, bo.getEq_routePath());
        }
        if (bo.getNeq_routePath() != null) {
            queryWrapper.ne(MenuDO::getRoutePath, bo.getNeq_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLike_routePath())) {
            queryWrapper.like(MenuDO::getRoutePath, bo.getLike_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_routePath())) {
            queryWrapper.likeRight(MenuDO::getRoutePath, bo.getLiker_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_routePath())) {
            queryWrapper.likeLeft(MenuDO::getRoutePath, bo.getLikel_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_routePath())) {
            queryWrapper.notLike(MenuDO::getRoutePath, bo.getNotlike_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getIn_routePath())) {
            WrapperUtil.in(queryWrapper, MenuDO::getRoutePath, bo.getIn_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_routePath())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getRoutePath, bo.getNotin_routePath());
        }
        // component 开始
        if (bo.getEq_component() != null) {
            queryWrapper.eq(MenuDO::getComponent, bo.getEq_component());
        }
        if (bo.getNeq_component() != null) {
            queryWrapper.ne(MenuDO::getComponent, bo.getNeq_component());
        }
        if (StringUtil.isNotEmpty(bo.getLike_component())) {
            queryWrapper.like(MenuDO::getComponent, bo.getLike_component());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_component())) {
            queryWrapper.likeRight(MenuDO::getComponent, bo.getLiker_component());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_component())) {
            queryWrapper.likeLeft(MenuDO::getComponent, bo.getLikel_component());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_component())) {
            queryWrapper.notLike(MenuDO::getComponent, bo.getNotlike_component());
        }
        if (StringUtil.isNotEmpty(bo.getIn_component())) {
            WrapperUtil.in(queryWrapper, MenuDO::getComponent, bo.getIn_component());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_component())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getComponent, bo.getNotin_component());
        }
        // query 开始
        if (bo.getEq_query() != null) {
            queryWrapper.eq(MenuDO::getQuery, bo.getEq_query());
        }
        if (bo.getNeq_query() != null) {
            queryWrapper.ne(MenuDO::getQuery, bo.getNeq_query());
        }
        if (StringUtil.isNotEmpty(bo.getLike_query())) {
            queryWrapper.like(MenuDO::getQuery, bo.getLike_query());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_query())) {
            queryWrapper.likeRight(MenuDO::getQuery, bo.getLiker_query());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_query())) {
            queryWrapper.likeLeft(MenuDO::getQuery, bo.getLikel_query());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_query())) {
            queryWrapper.notLike(MenuDO::getQuery, bo.getNotlike_query());
        }
        if (StringUtil.isNotEmpty(bo.getIn_query())) {
            WrapperUtil.in(queryWrapper, MenuDO::getQuery, bo.getIn_query());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_query())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getQuery, bo.getNotin_query());
        }
        // href 开始
        if (bo.getEq_href() != null) {
            queryWrapper.eq(MenuDO::getHref, bo.getEq_href());
        }
        if (bo.getNeq_href() != null) {
            queryWrapper.ne(MenuDO::getHref, bo.getNeq_href());
        }
        if (StringUtil.isNotEmpty(bo.getLike_href())) {
            queryWrapper.like(MenuDO::getHref, bo.getLike_href());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_href())) {
            queryWrapper.likeRight(MenuDO::getHref, bo.getLiker_href());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_href())) {
            queryWrapper.likeLeft(MenuDO::getHref, bo.getLikel_href());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_href())) {
            queryWrapper.notLike(MenuDO::getHref, bo.getNotlike_href());
        }
        if (StringUtil.isNotEmpty(bo.getIn_href())) {
            WrapperUtil.in(queryWrapper, MenuDO::getHref, bo.getIn_href());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_href())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getHref, bo.getNotin_href());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq(MenuDO::getSort, bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne(MenuDO::getSort, bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt(MenuDO::getSort, bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt(MenuDO::getSort, bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge(MenuDO::getSort, bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le(MenuDO::getSort, bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, MenuDO::getSort, bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, MenuDO::getSort, bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, MenuDO::getSort, bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getSort, bo.getNotin_sort());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(MenuDO::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(MenuDO::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(MenuDO::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(MenuDO::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(MenuDO::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(MenuDO::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, MenuDO::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, MenuDO::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, MenuDO::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(MenuDO::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(MenuDO::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(MenuDO::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(MenuDO::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(MenuDO::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(MenuDO::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, MenuDO::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, MenuDO::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, MenuDO::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(MenuDO::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(MenuDO::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(MenuDO::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(MenuDO::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(MenuDO::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(MenuDO::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, MenuDO::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(MenuDO::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(MenuDO::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(MenuDO::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(MenuDO::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(MenuDO::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(MenuDO::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, MenuDO::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(MenuDO::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(MenuDO::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(MenuDO::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(MenuDO::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(MenuDO::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(MenuDO::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, MenuDO::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(MenuDO::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(MenuDO::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(MenuDO::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(MenuDO::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(MenuDO::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(MenuDO::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, MenuDO::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, MenuDO::getUpdateUserId, bo.getNotin_updateUserId());
        }


        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<MenuDO> getJoinLambdaQueryWrapper(MenuSearchBO bo) {
        JoinLambdaQueryWrapper<MenuDO> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, MenuDO::getIsDelete, bo.getDataScope());


        return queryWrapper;
    }

}