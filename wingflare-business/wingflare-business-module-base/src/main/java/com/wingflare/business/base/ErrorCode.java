package com.wingflare.business.base;

/**
 * @InterfaceName ErrorCode
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 错误代码
 */
public interface ErrorCode {

    /**
     * 字典刷新失败
     */
    String SYS_DICT_REFRESH_ERROR = "sys.dict.refreshError";

    /**
     * 字典创建失败
     */
    String SYS_DICT_CREATE_ERROR = "sys.dict.createError";

    /**
     * 字典删除失败
     */
    String SYS_DICT_DELETE_ERROR = "sys.dict.deleteError";

    /**
     * 字典更新失败
     */
    String SYS_DICT_UPDATE_ERROR = "sys.dict.updateError";

    /**
     * 字典重复
     */
    String SYS_DICT_REPEAT = "sys.dict.repeat";

    /**
     * 字典不存在重复
     */
    String SYS_DICT_NOTFOUND = "sys.dict.notFound";

    /**
     * 字典值重复
     */
    String SYS_DICT_NAME_REPEAT = "sys.dict.nameRepeat";

    /**
     * 当前字典不允许删除
     */
    String SYS_DICT_NOT_DELETE = "sys.dict.notDelete";

    /**
     * 菜单创建失败
     */
    String SYS_MENU_CREATE_ERROR = "sys.menu.createError";

    /**
     * 菜单删除失败
     */
    String SYS_MENU_DELETE_ERROR = "sys.menu.deleteError";

    /**
     * 菜单更新失败
     */
    String SYS_MENU_UPDATE_ERROR = "sys.menu.updateError";

    /**
     * 菜单名称重复
     */
    String SYS_MENU_NAME_REPEAT = "sys.menu.nameRepeat";

    /**
     * 父级菜单不存
     */
    String SYS_MENU_PARENT_NOTFOUND = "sys.menu.parentNotFound";

    /**
     * 当前菜单不允许删除
     */
    String SYS_MENU_NOT_DELETE = "sys.menu.notDelete";

}
