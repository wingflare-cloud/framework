package com.wingflare.business.base.wrapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.base.db.MenuDo;
import com.wingflare.facade.module.base.bo.MenuSearchBo;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.utils.WrapperUtil;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * 系统菜单Wrapper
 *
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
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
        // menu_path 开始
        if (bo.getEq_menuPath() != null) {
            queryWrapper.eq("menu_path", bo.getEq_menuPath());
        }
        if (bo.getNeq_menuPath() != null) {
            queryWrapper.ne("menu_path", bo.getNeq_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuPath())) {
            queryWrapper.like("menu_path", bo.getLike_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuPath())) {
            queryWrapper.likeRight("menu_path", bo.getLiker_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuPath())) {
            queryWrapper.likeLeft("menu_path", bo.getLikel_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuPath())) {
            queryWrapper.notLike("menu_path", bo.getNotlike_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuPath())) {
            WrapperUtil.in(queryWrapper, "menu_path", bo.getIn_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuPath())) {
            WrapperUtil.notIn(queryWrapper, "menu_path", bo.getNotin_menuPath());
        }
        // menu_code 开始
        if (bo.getEq_menuCode() != null) {
            queryWrapper.eq("menu_code", bo.getEq_menuCode());
        }
        if (bo.getNeq_menuCode() != null) {
            queryWrapper.ne("menu_code", bo.getNeq_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuCode())) {
            queryWrapper.like("menu_code", bo.getLike_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuCode())) {
            queryWrapper.likeRight("menu_code", bo.getLiker_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuCode())) {
            queryWrapper.likeLeft("menu_code", bo.getLikel_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuCode())) {
            queryWrapper.notLike("menu_code", bo.getNotlike_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuCode())) {
            WrapperUtil.in(queryWrapper, "menu_code", bo.getIn_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuCode())) {
            WrapperUtil.notIn(queryWrapper, "menu_code", bo.getNotin_menuCode());
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

        // menu_id 开始
        if (bo.getEq_menuId() != null) {
            queryWrapper.eq(MenuDo::getMenuId, bo.getEq_menuId());
        }
        if (bo.getNeq_menuId() != null) {
            queryWrapper.ne(MenuDo::getMenuId, bo.getNeq_menuId());
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
        // menu_path 开始
        if (bo.getEq_menuPath() != null) {
            queryWrapper.eq(MenuDo::getMenuPath, bo.getEq_menuPath());
        }
        if (bo.getNeq_menuPath() != null) {
            queryWrapper.ne(MenuDo::getMenuPath, bo.getNeq_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuPath())) {
            queryWrapper.like(MenuDo::getMenuPath, bo.getLike_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuPath())) {
            queryWrapper.likeRight(MenuDo::getMenuPath, bo.getLiker_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuPath())) {
            queryWrapper.likeLeft(MenuDo::getMenuPath, bo.getLikel_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuPath())) {
            queryWrapper.notLike(MenuDo::getMenuPath, bo.getNotlike_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuPath())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuPath, bo.getIn_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuPath())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuPath, bo.getNotin_menuPath());
        }
        // menu_code 开始
        if (bo.getEq_menuCode() != null) {
            queryWrapper.eq(MenuDo::getMenuCode, bo.getEq_menuCode());
        }
        if (bo.getNeq_menuCode() != null) {
            queryWrapper.ne(MenuDo::getMenuCode, bo.getNeq_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuCode())) {
            queryWrapper.like(MenuDo::getMenuCode, bo.getLike_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuCode())) {
            queryWrapper.likeRight(MenuDo::getMenuCode, bo.getLiker_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuCode())) {
            queryWrapper.likeLeft(MenuDo::getMenuCode, bo.getLikel_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuCode())) {
            queryWrapper.notLike(MenuDo::getMenuCode, bo.getNotlike_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuCode())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuCode, bo.getIn_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuCode())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuCode, bo.getNotin_menuCode());
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

        // menu_id 开始
        if (bo.getEq_menuId() != null) {
            queryWrapper.eq(MenuDo::getMenuId, bo.getEq_menuId());
        }
        if (bo.getNeq_menuId() != null) {
            queryWrapper.ne(MenuDo::getMenuId, bo.getNeq_menuId());
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
        // menu_path 开始
        if (bo.getEq_menuPath() != null) {
            queryWrapper.eq(MenuDo::getMenuPath, bo.getEq_menuPath());
        }
        if (bo.getNeq_menuPath() != null) {
            queryWrapper.ne(MenuDo::getMenuPath, bo.getNeq_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuPath())) {
            queryWrapper.like(MenuDo::getMenuPath, bo.getLike_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuPath())) {
            queryWrapper.likeRight(MenuDo::getMenuPath, bo.getLiker_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuPath())) {
            queryWrapper.likeLeft(MenuDo::getMenuPath, bo.getLikel_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuPath())) {
            queryWrapper.notLike(MenuDo::getMenuPath, bo.getNotlike_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuPath())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuPath, bo.getIn_menuPath());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuPath())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuPath, bo.getNotin_menuPath());
        }
        // menu_code 开始
        if (bo.getEq_menuCode() != null) {
            queryWrapper.eq(MenuDo::getMenuCode, bo.getEq_menuCode());
        }
        if (bo.getNeq_menuCode() != null) {
            queryWrapper.ne(MenuDo::getMenuCode, bo.getNeq_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLike_menuCode())) {
            queryWrapper.like(MenuDo::getMenuCode, bo.getLike_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLiker_menuCode())) {
            queryWrapper.likeRight(MenuDo::getMenuCode, bo.getLiker_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getLikel_menuCode())) {
            queryWrapper.likeLeft(MenuDo::getMenuCode, bo.getLikel_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotlike_menuCode())) {
            queryWrapper.notLike(MenuDo::getMenuCode, bo.getNotlike_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getIn_menuCode())) {
            WrapperUtil.in(queryWrapper, MenuDo::getMenuCode, bo.getIn_menuCode());
        }
        if (StringUtil.isNotEmpty(bo.getNotin_menuCode())) {
            WrapperUtil.notIn(queryWrapper, MenuDo::getMenuCode, bo.getNotin_menuCode());
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

}