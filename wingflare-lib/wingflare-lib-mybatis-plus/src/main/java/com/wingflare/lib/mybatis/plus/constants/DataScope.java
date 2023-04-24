package com.wingflare.lib.mybatis.plus.constants;

/**
 * 数据范围标识常量类
 *
 * @author shaoyuyao
 * @date 2022/8/10 10:57
 */
public class DataScope {
    /**
     * 正常（is_delete = 0）
     */
    public static final String NORMAL = "normal";
    /**
     * 逻辑删除（is_delete = 1）
     */
    public static final String DELETE = "delete";
    /**
     * 全部（正常 + 逻辑删除）
     */
    public static final String ALL = "all";
}
