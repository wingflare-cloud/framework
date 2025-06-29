package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.business.base.db.MenuDo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;

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
	public static LambdaQueryWrapper<MenuDo> getLambdaQueryWrapper(MenuSearchBo bo) {
        LambdaQueryWrapper<MenuDo> queryWrapper = new LambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, MenuDo::getIsDelete, bo.getDataScope());

        // menu_id 开始
        if (bo.getEq_menuId() != null) {
            queryWrapper.eq(MenuDo::getMenuId, bo.getEq_menuId());
        }
        if (bo.getNeq_menuId() != null) {
            queryWrapper.ne(MenuDo::getMenuId, bo.getNeq_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuId())) {
            queryWrapper.like(MenuDo::getMenuId, bo.getLike_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuId())) {
            queryWrapper.likeRight(MenuDo::getMenuId, bo.getLiker_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuId())) {
            queryWrapper.likeLeft(MenuDo::getMenuId, bo.getLikel_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuId())) {
            queryWrapper.notLike(MenuDo::getMenuId, bo.getNotlike_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuId())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuId, bo.getIn_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuId())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuId, bo.getNotin_menuId());
        }
        // parent_menu_id 开始
        if (bo.getEq_parentMenuId() != null) {
            queryWrapper.eq(MenuDo::getParentMenuId, bo.getEq_parentMenuId());
        }
        if (bo.getNeq_parentMenuId() != null) {
            queryWrapper.ne(MenuDo::getParentMenuId, bo.getNeq_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentMenuId())) {
            queryWrapper.like(MenuDo::getParentMenuId, bo.getLike_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentMenuId())) {
            queryWrapper.likeRight(MenuDo::getParentMenuId, bo.getLiker_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentMenuId())) {
            queryWrapper.likeLeft(MenuDo::getParentMenuId, bo.getLikel_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentMenuId())) {
            queryWrapper.notLike(MenuDo::getParentMenuId, bo.getNotlike_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentMenuId())) {
            WrapperUtil.in(queryWrapper, MenuDo::getParentMenuId, bo.getIn_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentMenuId())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getParentMenuId, bo.getNotin_parentMenuId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq(MenuDo::getState, bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne(MenuDo::getState, bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt(MenuDo::getState, bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt(MenuDo::getState, bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge(MenuDo::getState, bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le(MenuDo::getState, bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, MenuDo::getState, bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, MenuDo::getState, bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, MenuDo::getState, bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getState, bo.getNotin_state());
        }
        // hide 开始
        if (bo.getEq_hide() != null) {
            queryWrapper.eq(MenuDo::getHide, bo.getEq_hide());
        }
        if (bo.getNeq_hide() != null) {
            queryWrapper.ne(MenuDo::getHide, bo.getNeq_hide());
        }
        if (bo.getGt_hide() != null) {
            queryWrapper.gt(MenuDo::getHide, bo.getGt_hide());
        }
        if (bo.getLt_hide() != null) {
            queryWrapper.lt(MenuDo::getHide, bo.getLt_hide());
        }
        if (bo.getEgt_hide() != null) {
            queryWrapper.ge(MenuDo::getHide, bo.getEgt_hide());
        }
        if (bo.getElt_hide() != null) {
            queryWrapper.le(MenuDo::getHide, bo.getElt_hide());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_hide())) {
            WrapperUtil.between(queryWrapper, MenuDo::getHide, bo.getBetween_hide());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_hide())) {
            WrapperUtil.notBetween(queryWrapper, MenuDo::getHide, bo.getNotbetween_hide());
        }
        if (StringUtil.isNotEmpty(bo.getIn_hide())) {
            WrapperUtil.in(queryWrapper, MenuDo::getHide, bo.getIn_hide());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_hide())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getHide, bo.getNotin_hide());
        }
        // constant 开始
        if (bo.getEq_constant() != null) {
            queryWrapper.eq(MenuDo::getConstant, bo.getEq_constant());
        }
        if (bo.getNeq_constant() != null) {
            queryWrapper.ne(MenuDo::getConstant, bo.getNeq_component());
        }
        // multi_tab 开始
        if (bo.getEq_multiTab() != null) {
            queryWrapper.eq(MenuDo::getMultiTab, bo.getEq_multiTab());
        }
        if (bo.getNeq_multiTab() != null) {
            queryWrapper.ne(MenuDo::getMultiTab, bo.getNeq_multiTab());
        }
        if (bo.getGt_multiTab() != null) {
            queryWrapper.gt(MenuDo::getMultiTab, bo.getGt_multiTab());
        }
        if (bo.getLt_multiTab() != null) {
            queryWrapper.lt(MenuDo::getMultiTab, bo.getLt_multiTab());
        }
        if (bo.getEgt_multiTab() != null) {
            queryWrapper.ge(MenuDo::getMultiTab, bo.getEgt_multiTab());
        }
        if (bo.getElt_multiTab() != null) {
            queryWrapper.le(MenuDo::getMultiTab, bo.getElt_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_multiTab())) {
            WrapperUtil.between(queryWrapper, MenuDo::getMultiTab, bo.getBetween_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_multiTab())) {
            WrapperUtil.notBetween(queryWrapper, MenuDo::getMultiTab, bo.getNotbetween_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getIn_multiTab())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMultiTab, bo.getIn_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_multiTab())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMultiTab, bo.getNotin_multiTab());
        }
        // menu_type 开始
        if (bo.getEq_menuType() != null) {
            queryWrapper.eq(MenuDo::getMenuType, bo.getEq_menuType());
        }
        if (bo.getNeq_menuType() != null) {
            queryWrapper.ne(MenuDo::getMenuType, bo.getNeq_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuType())) {
            queryWrapper.like(MenuDo::getMenuType, bo.getLike_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuType())) {
            queryWrapper.likeRight(MenuDo::getMenuType, bo.getLiker_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuType())) {
            queryWrapper.likeLeft(MenuDo::getMenuType, bo.getLikel_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuType())) {
            queryWrapper.notLike(MenuDo::getMenuType, bo.getNotlike_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuType())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuType, bo.getIn_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuType())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuType, bo.getNotin_menuType());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq(MenuDo::getSystemCode, bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne(MenuDo::getSystemCode, bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like(MenuDo::getSystemCode, bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight(MenuDo::getSystemCode, bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft(MenuDo::getSystemCode, bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike(MenuDo::getSystemCode, bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, MenuDo::getSystemCode, bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getSystemCode, bo.getNotin_systemCode());
        }
        // permission_code 开始
        if (bo.getEq_permissionCode() != null) {
            queryWrapper.eq(MenuDo::getPermissionCode, bo.getEq_permissionCode());
        }
        if (bo.getNeq_permissionCode() != null) {
            queryWrapper.ne(MenuDo::getPermissionCode, bo.getNeq_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_permissionCode())) {
            queryWrapper.like(MenuDo::getPermissionCode, bo.getLike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_permissionCode())) {
            queryWrapper.likeRight(MenuDo::getPermissionCode, bo.getLiker_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_permissionCode())) {
            queryWrapper.likeLeft(MenuDo::getPermissionCode, bo.getLikel_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_permissionCode())) {
            queryWrapper.notLike(MenuDo::getPermissionCode, bo.getNotlike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_permissionCode())) {
            WrapperUtil.in(queryWrapper, MenuDo::getPermissionCode, bo.getIn_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_permissionCode())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getPermissionCode, bo.getNotin_permissionCode());
        }
        // menu_name 开始
        if (bo.getEq_menuName() != null) {
            queryWrapper.eq(MenuDo::getMenuName, bo.getEq_menuName());
        }
        if (bo.getNeq_menuName() != null) {
            queryWrapper.ne(MenuDo::getMenuName, bo.getNeq_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuName())) {
            queryWrapper.like(MenuDo::getMenuName, bo.getLike_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuName())) {
            queryWrapper.likeRight(MenuDo::getMenuName, bo.getLiker_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuName())) {
            queryWrapper.likeLeft(MenuDo::getMenuName, bo.getLikel_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuName())) {
            queryWrapper.notLike(MenuDo::getMenuName, bo.getNotlike_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuName())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuName, bo.getIn_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuName())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuName, bo.getNotin_menuName());
        }
        // i18n_key 开始
        if (bo.getEq_langKey() != null) {
            queryWrapper.eq(MenuDo::getLangKey, bo.getEq_langKey());
        }
        if (bo.getNeq_langKey() != null) {
            queryWrapper.ne(MenuDo::getLangKey, bo.getNeq_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLike_langKey())) {
            queryWrapper.like(MenuDo::getLangKey, bo.getLike_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_langKey())) {
            queryWrapper.likeRight(MenuDo::getLangKey, bo.getLiker_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_langKey())) {
            queryWrapper.likeLeft(MenuDo::getLangKey, bo.getLikel_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_langKey())) {
            queryWrapper.notLike(MenuDo::getLangKey, bo.getNotlike_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getIn_langKey())) {
            WrapperUtil.in(queryWrapper, MenuDo::getLangKey, bo.getIn_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_langKey())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getLangKey, bo.getNotin_langKey());
        }
        // menu_icon 开始
        if (bo.getEq_menuIcon() != null) {
            queryWrapper.eq(MenuDo::getMenuIcon, bo.getEq_menuIcon());
        }
        if (bo.getNeq_menuIcon() != null) {
            queryWrapper.ne(MenuDo::getMenuIcon, bo.getNeq_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuIcon())) {
            queryWrapper.like(MenuDo::getMenuIcon, bo.getLike_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuIcon())) {
            queryWrapper.likeRight(MenuDo::getMenuIcon, bo.getLiker_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuIcon())) {
            queryWrapper.likeLeft(MenuDo::getMenuIcon, bo.getLikel_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuIcon())) {
            queryWrapper.notLike(MenuDo::getMenuIcon, bo.getNotlike_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuIcon())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuIcon, bo.getIn_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuIcon())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuIcon, bo.getNotin_menuIcon());
        }
        // icon_type 开始
        if (bo.getEq_iconType() != null) {
            queryWrapper.eq(MenuDo::getIconType, bo.getEq_iconType());
        }
        if (bo.getNeq_iconType() != null) {
            queryWrapper.ne(MenuDo::getIconType, bo.getNeq_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_iconType())) {
            queryWrapper.like(MenuDo::getIconType, bo.getLike_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_iconType())) {
            queryWrapper.likeRight(MenuDo::getIconType, bo.getLiker_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_iconType())) {
            queryWrapper.likeLeft(MenuDo::getIconType, bo.getLikel_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_iconType())) {
            queryWrapper.notLike(MenuDo::getIconType, bo.getNotlike_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_iconType())) {
            WrapperUtil.in(queryWrapper, MenuDo::getIconType, bo.getIn_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_iconType())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getIconType, bo.getNotin_iconType());
        }
        // route_name 开始
        if (bo.getEq_routeName() != null) {
            queryWrapper.eq(MenuDo::getRouteName, bo.getEq_routeName());
        }
        if (bo.getNeq_routeName() != null) {
            queryWrapper.ne(MenuDo::getRouteName, bo.getNeq_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_routeName())) {
            queryWrapper.like(MenuDo::getRouteName, bo.getLike_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_routeName())) {
            queryWrapper.likeRight(MenuDo::getRouteName, bo.getLiker_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_routeName())) {
            queryWrapper.likeLeft(MenuDo::getRouteName, bo.getLikel_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_routeName())) {
            queryWrapper.notLike(MenuDo::getRouteName, bo.getNotlike_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_routeName())) {
            WrapperUtil.in(queryWrapper, MenuDo::getRouteName, bo.getIn_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_routeName())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getRouteName, bo.getNotin_routeName());
        }
        // route_path 开始
        if (bo.getEq_routePath() != null) {
            queryWrapper.eq(MenuDo::getRoutePath, bo.getEq_routePath());
        }
        if (bo.getNeq_routePath() != null) {
            queryWrapper.ne(MenuDo::getRoutePath, bo.getNeq_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLike_routePath())) {
            queryWrapper.like(MenuDo::getRoutePath, bo.getLike_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_routePath())) {
            queryWrapper.likeRight(MenuDo::getRoutePath, bo.getLiker_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_routePath())) {
            queryWrapper.likeLeft(MenuDo::getRoutePath, bo.getLikel_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_routePath())) {
            queryWrapper.notLike(MenuDo::getRoutePath, bo.getNotlike_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getIn_routePath())) {
            WrapperUtil.in(queryWrapper, MenuDo::getRoutePath, bo.getIn_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_routePath())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getRoutePath, bo.getNotin_routePath());
        }
        // component 开始
        if (bo.getEq_component() != null) {
            queryWrapper.eq(MenuDo::getComponent, bo.getEq_component());
        }
        if (bo.getNeq_component() != null) {
            queryWrapper.ne(MenuDo::getComponent, bo.getNeq_component());
        }
        if (StringUtil.isNotEmpty(bo.getLike_component())) {
            queryWrapper.like(MenuDo::getComponent, bo.getLike_component());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_component())) {
            queryWrapper.likeRight(MenuDo::getComponent, bo.getLiker_component());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_component())) {
            queryWrapper.likeLeft(MenuDo::getComponent, bo.getLikel_component());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_component())) {
            queryWrapper.notLike(MenuDo::getComponent, bo.getNotlike_component());
        }
        if (StringUtil.isNotEmpty(bo.getIn_component())) {
            WrapperUtil.in(queryWrapper, MenuDo::getComponent, bo.getIn_component());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_component())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getComponent, bo.getNotin_component());
        }
        // query 开始
        if (bo.getEq_query() != null) {
            queryWrapper.eq(MenuDo::getQuery, bo.getEq_query());
        }
        if (bo.getNeq_query() != null) {
            queryWrapper.ne(MenuDo::getQuery, bo.getNeq_query());
        }
        if (StringUtil.isNotEmpty(bo.getLike_query())) {
            queryWrapper.like(MenuDo::getQuery, bo.getLike_query());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_query())) {
            queryWrapper.likeRight(MenuDo::getQuery, bo.getLiker_query());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_query())) {
            queryWrapper.likeLeft(MenuDo::getQuery, bo.getLikel_query());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_query())) {
            queryWrapper.notLike(MenuDo::getQuery, bo.getNotlike_query());
        }
        if (StringUtil.isNotEmpty(bo.getIn_query())) {
            WrapperUtil.in(queryWrapper, MenuDo::getQuery, bo.getIn_query());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_query())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getQuery, bo.getNotin_query());
        }
        // href 开始
        if (bo.getEq_href() != null) {
            queryWrapper.eq(MenuDo::getHref, bo.getEq_href());
        }
        if (bo.getNeq_href() != null) {
            queryWrapper.ne(MenuDo::getHref, bo.getNeq_href());
        }
        if (StringUtil.isNotEmpty(bo.getLike_href())) {
            queryWrapper.like(MenuDo::getHref, bo.getLike_href());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_href())) {
            queryWrapper.likeRight(MenuDo::getHref, bo.getLiker_href());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_href())) {
            queryWrapper.likeLeft(MenuDo::getHref, bo.getLikel_href());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_href())) {
            queryWrapper.notLike(MenuDo::getHref, bo.getNotlike_href());
        }
        if (StringUtil.isNotEmpty(bo.getIn_href())) {
            WrapperUtil.in(queryWrapper, MenuDo::getHref, bo.getIn_href());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_href())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getHref, bo.getNotin_href());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq(MenuDo::getSort, bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne(MenuDo::getSort, bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt(MenuDo::getSort, bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt(MenuDo::getSort, bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge(MenuDo::getSort, bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le(MenuDo::getSort, bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, MenuDo::getSort, bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, MenuDo::getSort, bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, MenuDo::getSort, bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getSort, bo.getNotin_sort());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq(MenuDo::getCreatedTime, bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne(MenuDo::getCreatedTime, bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt(MenuDo::getCreatedTime, bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt(MenuDo::getCreatedTime, bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge(MenuDo::getCreatedTime, bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le(MenuDo::getCreatedTime, bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, MenuDo::getCreatedTime, bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, MenuDo::getCreatedTime, bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, MenuDo::getCreatedTime, bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getCreatedTime, bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq(MenuDo::getUpdatedTime, bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne(MenuDo::getUpdatedTime, bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt(MenuDo::getUpdatedTime, bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt(MenuDo::getUpdatedTime, bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge(MenuDo::getUpdatedTime, bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le(MenuDo::getUpdatedTime, bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, MenuDo::getUpdatedTime, bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, MenuDo::getUpdatedTime, bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, MenuDo::getUpdatedTime, bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getUpdatedTime, bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq(MenuDo::getCreateUser, bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne(MenuDo::getCreateUser, bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like(MenuDo::getCreateUser, bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight(MenuDo::getCreateUser, bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft(MenuDo::getCreateUser, bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike(MenuDo::getCreateUser, bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, MenuDo::getCreateUser, bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getCreateUser, bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq(MenuDo::getCreateUserId, bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne(MenuDo::getCreateUserId, bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like(MenuDo::getCreateUserId, bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight(MenuDo::getCreateUserId, bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft(MenuDo::getCreateUserId, bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike(MenuDo::getCreateUserId, bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, MenuDo::getCreateUserId, bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getCreateUserId, bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq(MenuDo::getUpdateUser, bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne(MenuDo::getUpdateUser, bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like(MenuDo::getUpdateUser, bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight(MenuDo::getUpdateUser, bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft(MenuDo::getUpdateUser, bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike(MenuDo::getUpdateUser, bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, MenuDo::getUpdateUser, bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getUpdateUser, bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq(MenuDo::getUpdateUserId, bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne(MenuDo::getUpdateUserId, bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like(MenuDo::getUpdateUserId, bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight(MenuDo::getUpdateUserId, bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft(MenuDo::getUpdateUserId, bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike(MenuDo::getUpdateUserId, bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, MenuDo::getUpdateUserId, bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getUpdateUserId, bo.getNotin_updateUserId());
        }


        return queryWrapper;
    }

	/**
     * 获取JoinLambdaQueryWrapper
     *
     * @param bo 查询条件
     */
	 public static JoinLambdaQueryWrapper<MenuDo> getJoinLambdaQueryWrapper(MenuSearchBo bo) {
        JoinLambdaQueryWrapper<MenuDo> queryWrapper = new JoinLambdaQueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, MenuDo::getIsDelete, bo.getDataScope());


        return queryWrapper;
    }

}