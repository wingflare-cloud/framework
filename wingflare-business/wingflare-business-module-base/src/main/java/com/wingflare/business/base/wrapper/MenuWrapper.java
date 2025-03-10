package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
     * 获取QueryWrapper
     *
     * @param bo 查询条件
     */
    public static QueryWrapper<MenuDo> getQueryWrapper(MenuSearchBo bo) {
        QueryWrapper<MenuDo> queryWrapper = new QueryWrapper();

        if (bo == null) {
            return queryWrapper;
        }

        // 设置数据范围
        WrapperUtil.setDataScope(queryWrapper, bo.getDataScope());

        // menu_id 开始
        if (bo.getEq_menuId() != null) {
            queryWrapper.eq("menu_id", bo.getEq_menuId());
        }
        if (bo.getNeq_menuId() != null) {
            queryWrapper.ne("menu_id", bo.getNeq_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuId())) {
            queryWrapper.like("menu_id", bo.getLike_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuId())) {
            queryWrapper.likeRight("menu_id", bo.getLiker_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuId())) {
            queryWrapper.likeLeft("menu_id", bo.getLikel_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuId())) {
            queryWrapper.notLike("menu_id", bo.getNotlike_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuId())) {
            WrapperUtil.in(queryWrapper, "menu_id", bo.getIn_menuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuId())) {
            WrapperUtil.notIn(queryWrapper, "menu_id", bo.getNotin_menuId());
        }
        // parent_menu_id 开始
        if (bo.getEq_parentMenuId() != null) {
            queryWrapper.eq("parent_menu_id", bo.getEq_parentMenuId());
        }
        if (bo.getNeq_parentMenuId() != null) {
            queryWrapper.ne("parent_menu_id", bo.getNeq_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_parentMenuId())) {
            queryWrapper.like("parent_menu_id", bo.getLike_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_parentMenuId())) {
            queryWrapper.likeRight("parent_menu_id", bo.getLiker_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_parentMenuId())) {
            queryWrapper.likeLeft("parent_menu_id", bo.getLikel_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_parentMenuId())) {
            queryWrapper.notLike("parent_menu_id", bo.getNotlike_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_parentMenuId())) {
            WrapperUtil.in(queryWrapper, "parent_menu_id", bo.getIn_parentMenuId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_parentMenuId())) {
            WrapperUtil.notIn(queryWrapper, "parent_menu_id", bo.getNotin_parentMenuId());
        }
        // state 开始
        if (bo.getEq_state() != null) {
            queryWrapper.eq("state", bo.getEq_state());
        }
        if (bo.getNeq_state() != null) {
            queryWrapper.ne("state", bo.getNeq_state());
        }
        if (bo.getGt_state() != null) {
            queryWrapper.gt("state", bo.getGt_state());
        }
        if (bo.getLt_state() != null) {
            queryWrapper.lt("state", bo.getLt_state());
        }
        if (bo.getEgt_state() != null) {
            queryWrapper.ge("state", bo.getEgt_state());
        }
        if (bo.getElt_state() != null) {
            queryWrapper.le("state", bo.getElt_state());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_state())) {
            WrapperUtil.between(queryWrapper, "state", bo.getBetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_state())) {
            WrapperUtil.notBetween(queryWrapper, "state", bo.getNotbetween_state());
        }
        if (StringUtil.isNotEmpty(bo.getIn_state())) {
            WrapperUtil.in(queryWrapper, "state", bo.getIn_state());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_state())) {
            WrapperUtil.notIn(queryWrapper, "state", bo.getNotin_state());
        }
        // hide 开始
        if (bo.getEq_hide() != null) {
            queryWrapper.eq("hide", bo.getEq_hide());
        }
        if (bo.getNeq_hide() != null) {
            queryWrapper.ne("hide", bo.getNeq_hide());
        }
        if (bo.getGt_hide() != null) {
            queryWrapper.gt("hide", bo.getGt_hide());
        }
        if (bo.getLt_hide() != null) {
            queryWrapper.lt("hide", bo.getLt_hide());
        }
        if (bo.getEgt_hide() != null) {
            queryWrapper.ge("hide", bo.getEgt_hide());
        }
        if (bo.getElt_hide() != null) {
            queryWrapper.le("hide", bo.getElt_hide());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_hide())) {
            WrapperUtil.between(queryWrapper, "hide", bo.getBetween_hide());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_hide())) {
            WrapperUtil.notBetween(queryWrapper, "hide", bo.getNotbetween_hide());
        }
        if (StringUtil.isNotEmpty(bo.getIn_hide())) {
            WrapperUtil.in(queryWrapper, "hide", bo.getIn_hide());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_hide())) {
            WrapperUtil.notIn(queryWrapper, "hide", bo.getNotin_hide());
        }
        // constant 开始
        if (bo.getEq_constant() != null) {
            queryWrapper.eq("constant", bo.getEq_constant());
        }
        if (bo.getNeq_constant() != null) {
            queryWrapper.ne("constant", bo.getNeq_component());
        }
        // multi_tab 开始
        if (bo.getEq_multiTab() != null) {
            queryWrapper.eq("multi_tab", bo.getEq_multiTab());
        }
        if (bo.getNeq_multiTab() != null) {
            queryWrapper.ne("multi_tab", bo.getNeq_multiTab());
        }
        if (bo.getGt_multiTab() != null) {
            queryWrapper.gt("multi_tab", bo.getGt_multiTab());
        }
        if (bo.getLt_multiTab() != null) {
            queryWrapper.lt("multi_tab", bo.getLt_multiTab());
        }
        if (bo.getEgt_multiTab() != null) {
            queryWrapper.ge("multi_tab", bo.getEgt_multiTab());
        }
        if (bo.getElt_multiTab() != null) {
            queryWrapper.le("multi_tab", bo.getElt_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_multiTab())) {
            WrapperUtil.between(queryWrapper, "multi_tab", bo.getBetween_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_multiTab())) {
            WrapperUtil.notBetween(queryWrapper, "multi_tab", bo.getNotbetween_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getIn_multiTab())) {
            WrapperUtil.in(queryWrapper, "multi_tab", bo.getIn_multiTab());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_multiTab())) {
            WrapperUtil.notIn(queryWrapper, "multi_tab", bo.getNotin_multiTab());
        }
        // menu_type 开始
        if (bo.getEq_menuType() != null) {
            queryWrapper.eq("menu_type", bo.getEq_menuType());
        }
        if (bo.getNeq_menuType() != null) {
            queryWrapper.ne("menu_type", bo.getNeq_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuType())) {
            queryWrapper.like("menu_type", bo.getLike_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuType())) {
            queryWrapper.likeRight("menu_type", bo.getLiker_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuType())) {
            queryWrapper.likeLeft("menu_type", bo.getLikel_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuType())) {
            queryWrapper.notLike("menu_type", bo.getNotlike_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuType())) {
            WrapperUtil.in(queryWrapper, "menu_type", bo.getIn_menuType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuType())) {
            WrapperUtil.notIn(queryWrapper, "menu_type", bo.getNotin_menuType());
        }
        // system_code 开始
        if (bo.getEq_systemCode() != null) {
            queryWrapper.eq("system_code", bo.getEq_systemCode());
        }
        if (bo.getNeq_systemCode() != null) {
            queryWrapper.ne("system_code", bo.getNeq_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_systemCode())) {
            queryWrapper.like("system_code", bo.getLike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_systemCode())) {
            queryWrapper.likeRight("system_code", bo.getLiker_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_systemCode())) {
            queryWrapper.likeLeft("system_code", bo.getLikel_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_systemCode())) {
            queryWrapper.notLike("system_code", bo.getNotlike_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_systemCode())) {
            WrapperUtil.in(queryWrapper, "system_code", bo.getIn_systemCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_systemCode())) {
            WrapperUtil.notIn(queryWrapper, "system_code", bo.getNotin_systemCode());
        }
        // permission_code 开始
        if (bo.getEq_permissionCode() != null) {
            queryWrapper.eq("permission_code", bo.getEq_permissionCode());
        }
        if (bo.getNeq_permissionCode() != null) {
            queryWrapper.ne("permission_code", bo.getNeq_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_permissionCode())) {
            queryWrapper.like("permission_code", bo.getLike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_permissionCode())) {
            queryWrapper.likeRight("permission_code", bo.getLiker_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_permissionCode())) {
            queryWrapper.likeLeft("permission_code", bo.getLikel_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_permissionCode())) {
            queryWrapper.notLike("permission_code", bo.getNotlike_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_permissionCode())) {
            WrapperUtil.in(queryWrapper, "permission_code", bo.getIn_permissionCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_permissionCode())) {
            WrapperUtil.notIn(queryWrapper, "permission_code", bo.getNotin_permissionCode());
        }
        // menu_name 开始
        if (bo.getEq_menuName() != null) {
            queryWrapper.eq("menu_name", bo.getEq_menuName());
        }
        if (bo.getNeq_menuName() != null) {
            queryWrapper.ne("menu_name", bo.getNeq_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuName())) {
            queryWrapper.like("menu_name", bo.getLike_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuName())) {
            queryWrapper.likeRight("menu_name", bo.getLiker_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuName())) {
            queryWrapper.likeLeft("menu_name", bo.getLikel_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuName())) {
            queryWrapper.notLike("menu_name", bo.getNotlike_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuName())) {
            WrapperUtil.in(queryWrapper, "menu_name", bo.getIn_menuName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuName())) {
            WrapperUtil.notIn(queryWrapper, "menu_name", bo.getNotin_menuName());
        }
        // i18n_key 开始
        if (bo.getEq_langKey() != null) {
            queryWrapper.eq("i18n_key", bo.getEq_langKey());
        }
        if (bo.getNeq_langKey() != null) {
            queryWrapper.ne("i18n_key", bo.getNeq_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLike_langKey())) {
            queryWrapper.like("i18n_key", bo.getLike_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_langKey())) {
            queryWrapper.likeRight("i18n_key", bo.getLiker_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_langKey())) {
            queryWrapper.likeLeft("i18n_key", bo.getLikel_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_langKey())) {
            queryWrapper.notLike("i18n_key", bo.getNotlike_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getIn_langKey())) {
            WrapperUtil.in(queryWrapper, "i18n_key", bo.getIn_langKey());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_langKey())) {
            WrapperUtil.notIn(queryWrapper, "i18n_key", bo.getNotin_langKey());
        }
        // menu_icon 开始
        if (bo.getEq_menuIcon() != null) {
            queryWrapper.eq("menu_icon", bo.getEq_menuIcon());
        }
        if (bo.getNeq_menuIcon() != null) {
            queryWrapper.ne("menu_icon", bo.getNeq_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuIcon())) {
            queryWrapper.like("menu_icon", bo.getLike_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuIcon())) {
            queryWrapper.likeRight("menu_icon", bo.getLiker_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuIcon())) {
            queryWrapper.likeLeft("menu_icon", bo.getLikel_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuIcon())) {
            queryWrapper.notLike("menu_icon", bo.getNotlike_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuIcon())) {
            WrapperUtil.in(queryWrapper, "menu_icon", bo.getIn_menuIcon());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuIcon())) {
            WrapperUtil.notIn(queryWrapper, "menu_icon", bo.getNotin_menuIcon());
        }
        // icon_type 开始
        if (bo.getEq_iconType() != null) {
            queryWrapper.eq("icon_type", bo.getEq_iconType());
        }
        if (bo.getNeq_iconType() != null) {
            queryWrapper.ne("icon_type", bo.getNeq_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLike_iconType())) {
            queryWrapper.like("icon_type", bo.getLike_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_iconType())) {
            queryWrapper.likeRight("icon_type", bo.getLiker_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_iconType())) {
            queryWrapper.likeLeft("icon_type", bo.getLikel_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_iconType())) {
            queryWrapper.notLike("icon_type", bo.getNotlike_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getIn_iconType())) {
            WrapperUtil.in(queryWrapper, "icon_type", bo.getIn_iconType());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_iconType())) {
            WrapperUtil.notIn(queryWrapper, "icon_type", bo.getNotin_iconType());
        }
        // route_name 开始
        if (bo.getEq_routeName() != null) {
            queryWrapper.eq("route_name", bo.getEq_routeName());
        }
        if (bo.getNeq_routeName() != null) {
            queryWrapper.ne("route_name", bo.getNeq_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLike_routeName())) {
            queryWrapper.like("route_name", bo.getLike_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_routeName())) {
            queryWrapper.likeRight("route_name", bo.getLiker_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_routeName())) {
            queryWrapper.likeLeft("route_name", bo.getLikel_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_routeName())) {
            queryWrapper.notLike("route_name", bo.getNotlike_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getIn_routeName())) {
            WrapperUtil.in(queryWrapper, "route_name", bo.getIn_routeName());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_routeName())) {
            WrapperUtil.notIn(queryWrapper, "route_name", bo.getNotin_routeName());
        }
        // route_path 开始
        if (bo.getEq_routePath() != null) {
            queryWrapper.eq("route_path", bo.getEq_routePath());
        }
        if (bo.getNeq_routePath() != null) {
            queryWrapper.ne("route_path", bo.getNeq_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLike_routePath())) {
            queryWrapper.like("route_path", bo.getLike_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_routePath())) {
            queryWrapper.likeRight("route_path", bo.getLiker_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_routePath())) {
            queryWrapper.likeLeft("route_path", bo.getLikel_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_routePath())) {
            queryWrapper.notLike("route_path", bo.getNotlike_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getIn_routePath())) {
            WrapperUtil.in(queryWrapper, "route_path", bo.getIn_routePath());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_routePath())) {
            WrapperUtil.notIn(queryWrapper, "route_path", bo.getNotin_routePath());
        }
        // component 开始
        if (bo.getEq_component() != null) {
            queryWrapper.eq("component", bo.getEq_component());
        }
        if (bo.getNeq_component() != null) {
            queryWrapper.ne("component", bo.getNeq_component());
        }
        if (StringUtil.isNotEmpty(bo.getLike_component())) {
            queryWrapper.like("component", bo.getLike_component());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_component())) {
            queryWrapper.likeRight("component", bo.getLiker_component());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_component())) {
            queryWrapper.likeLeft("component", bo.getLikel_component());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_component())) {
            queryWrapper.notLike("component", bo.getNotlike_component());
        }
        if (StringUtil.isNotEmpty(bo.getIn_component())) {
            WrapperUtil.in(queryWrapper, "component", bo.getIn_component());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_component())) {
            WrapperUtil.notIn(queryWrapper, "component", bo.getNotin_component());
        }
        // query 开始
        if (bo.getEq_query() != null) {
            queryWrapper.eq("query", bo.getEq_query());
        }
        if (bo.getNeq_query() != null) {
            queryWrapper.ne("query", bo.getNeq_query());
        }
        if (StringUtil.isNotEmpty(bo.getLike_query())) {
            queryWrapper.like("query", bo.getLike_query());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_query())) {
            queryWrapper.likeRight("query", bo.getLiker_query());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_query())) {
            queryWrapper.likeLeft("query", bo.getLikel_query());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_query())) {
            queryWrapper.notLike("query", bo.getNotlike_query());
        }
        if (StringUtil.isNotEmpty(bo.getIn_query())) {
            WrapperUtil.in(queryWrapper, "query", bo.getIn_query());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_query())) {
            WrapperUtil.notIn(queryWrapper, "query", bo.getNotin_query());
        }
        // href 开始
        if (bo.getEq_href() != null) {
            queryWrapper.eq("href", bo.getEq_href());
        }
        if (bo.getNeq_href() != null) {
            queryWrapper.ne("href", bo.getNeq_href());
        }
        if (StringUtil.isNotEmpty(bo.getLike_href())) {
            queryWrapper.like("href", bo.getLike_href());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_href())) {
            queryWrapper.likeRight("href", bo.getLiker_href());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_href())) {
            queryWrapper.likeLeft("href", bo.getLikel_href());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_href())) {
            queryWrapper.notLike("href", bo.getNotlike_href());
        }
        if (StringUtil.isNotEmpty(bo.getIn_href())) {
            WrapperUtil.in(queryWrapper, "href", bo.getIn_href());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_href())) {
            WrapperUtil.notIn(queryWrapper, "href", bo.getNotin_href());
        }
        // sort 开始
        if (bo.getEq_sort() != null) {
            queryWrapper.eq("sort", bo.getEq_sort());
        }
        if (bo.getNeq_sort() != null) {
            queryWrapper.ne("sort", bo.getNeq_sort());
        }
        if (bo.getGt_sort() != null) {
            queryWrapper.gt("sort", bo.getGt_sort());
        }
        if (bo.getLt_sort() != null) {
            queryWrapper.lt("sort", bo.getLt_sort());
        }
        if (bo.getEgt_sort() != null) {
            queryWrapper.ge("sort", bo.getEgt_sort());
        }
        if (bo.getElt_sort() != null) {
            queryWrapper.le("sort", bo.getElt_sort());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_sort())) {
            WrapperUtil.between(queryWrapper, "sort", bo.getBetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_sort())) {
            WrapperUtil.notBetween(queryWrapper, "sort", bo.getNotbetween_sort());
        }
        if (StringUtil.isNotEmpty(bo.getIn_sort())) {
            WrapperUtil.in(queryWrapper, "sort", bo.getIn_sort());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_sort())) {
            WrapperUtil.notIn(queryWrapper, "sort", bo.getNotin_sort());
        }
        // created_time 开始
        if (bo.getEq_createdTime() != null) {
            queryWrapper.eq("created_time", bo.getEq_createdTime());
        }
        if (bo.getNeq_createdTime() != null) {
            queryWrapper.ne("created_time", bo.getNeq_createdTime());
        }
        if (bo.getGt_createdTime() != null) {
            queryWrapper.gt("created_time", bo.getGt_createdTime());
        }
        if (bo.getLt_createdTime() != null) {
            queryWrapper.lt("created_time", bo.getLt_createdTime());
        }
        if (bo.getEgt_createdTime() != null) {
            queryWrapper.ge("created_time", bo.getEgt_createdTime());
        }
        if (bo.getElt_createdTime() != null) {
            queryWrapper.le("created_time", bo.getElt_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_createdTime())) {
            WrapperUtil.between(queryWrapper, "created_time", bo.getBetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_createdTime())) {
            WrapperUtil.notBetween(queryWrapper, "created_time", bo.getNotbetween_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createdTime())) {
            WrapperUtil.in(queryWrapper, "created_time", bo.getIn_createdTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createdTime())) {
            WrapperUtil.notIn(queryWrapper, "created_time", bo.getNotin_createdTime());
        }
        // updated_time 开始
        if (bo.getEq_updatedTime() != null) {
            queryWrapper.eq("updated_time", bo.getEq_updatedTime());
        }
        if (bo.getNeq_updatedTime() != null) {
            queryWrapper.ne("updated_time", bo.getNeq_updatedTime());
        }
        if (bo.getGt_updatedTime() != null) {
            queryWrapper.gt("updated_time", bo.getGt_updatedTime());
        }
        if (bo.getLt_updatedTime() != null) {
            queryWrapper.lt("updated_time", bo.getLt_updatedTime());
        }
        if (bo.getEgt_updatedTime() != null) {
            queryWrapper.ge("updated_time", bo.getEgt_updatedTime());
        }
        if (bo.getElt_updatedTime() != null) {
            queryWrapper.le("updated_time", bo.getElt_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getBetween_updatedTime())) {
            WrapperUtil.between(queryWrapper, "updated_time", bo.getBetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotbetween_updatedTime())) {
            WrapperUtil.notBetween(queryWrapper, "updated_time", bo.getNotbetween_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updatedTime())) {
            WrapperUtil.in(queryWrapper, "updated_time", bo.getIn_updatedTime());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updatedTime())) {
            WrapperUtil.notIn(queryWrapper, "updated_time", bo.getNotin_updatedTime());
        }
        // create_user 开始
        if (bo.getEq_createUser() != null) {
            queryWrapper.eq("create_user", bo.getEq_createUser());
        }
        if (bo.getNeq_createUser() != null) {
            queryWrapper.ne("create_user", bo.getNeq_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUser())) {
            queryWrapper.like("create_user", bo.getLike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUser())) {
            queryWrapper.likeRight("create_user", bo.getLiker_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUser())) {
            queryWrapper.likeLeft("create_user", bo.getLikel_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUser())) {
            queryWrapper.notLike("create_user", bo.getNotlike_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUser())) {
            WrapperUtil.in(queryWrapper, "create_user", bo.getIn_createUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUser())) {
            WrapperUtil.notIn(queryWrapper, "create_user", bo.getNotin_createUser());
        }
        // create_user_id 开始
        if (bo.getEq_createUserId() != null) {
            queryWrapper.eq("create_user_id", bo.getEq_createUserId());
        }
        if (bo.getNeq_createUserId() != null) {
            queryWrapper.ne("create_user_id", bo.getNeq_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_createUserId())) {
            queryWrapper.like("create_user_id", bo.getLike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_createUserId())) {
            queryWrapper.likeRight("create_user_id", bo.getLiker_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_createUserId())) {
            queryWrapper.likeLeft("create_user_id", bo.getLikel_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_createUserId())) {
            queryWrapper.notLike("create_user_id", bo.getNotlike_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_createUserId())) {
            WrapperUtil.in(queryWrapper, "create_user_id", bo.getIn_createUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_createUserId())) {
            WrapperUtil.notIn(queryWrapper, "create_user_id", bo.getNotin_createUserId());
        }
        // update_user 开始
        if (bo.getEq_updateUser() != null) {
            queryWrapper.eq("update_user", bo.getEq_updateUser());
        }
        if (bo.getNeq_updateUser() != null) {
            queryWrapper.ne("update_user", bo.getNeq_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUser())) {
            queryWrapper.like("update_user", bo.getLike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUser())) {
            queryWrapper.likeRight("update_user", bo.getLiker_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUser())) {
            queryWrapper.likeLeft("update_user", bo.getLikel_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUser())) {
            queryWrapper.notLike("update_user", bo.getNotlike_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUser())) {
            WrapperUtil.in(queryWrapper, "update_user", bo.getIn_updateUser());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUser())) {
            WrapperUtil.notIn(queryWrapper, "update_user", bo.getNotin_updateUser());
        }
        // update_user_id 开始
        if (bo.getEq_updateUserId() != null) {
            queryWrapper.eq("update_user_id", bo.getEq_updateUserId());
        }
        if (bo.getNeq_updateUserId() != null) {
            queryWrapper.ne("update_user_id", bo.getNeq_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLike_updateUserId())) {
            queryWrapper.like("update_user_id", bo.getLike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_updateUserId())) {
            queryWrapper.likeRight("update_user_id", bo.getLiker_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_updateUserId())) {
            queryWrapper.likeLeft("update_user_id", bo.getLikel_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_updateUserId())) {
            queryWrapper.notLike("update_user_id", bo.getNotlike_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getIn_updateUserId())) {
            WrapperUtil.in(queryWrapper, "update_user_id", bo.getIn_updateUserId());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_updateUserId())) {
            WrapperUtil.notIn(queryWrapper, "update_user_id", bo.getNotin_updateUserId());
        }

        return queryWrapper;
    }
	
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