package com.wingflare.lib.mybatis.plus.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.wrapper.JoinLambdaQueryWrapper;

/**
 * Wrapper工具类
 *
 * @author shaoyuyao
 * @date 2022/8/9 20:42
 */
public class WrapperUtil {
    /**
     * 设置数据范围
     */
    public static void setDataScope(QueryWrapper queryWrapper, String dataScope) {
        if (SoftDeleteUtil.isAll(dataScope)) {
            return;
        }

        Integer value = SoftDeleteUtil.getValue(dataScope);

        if (value != null) {
            queryWrapper.eq(Constant.IS_DELETE, value);
        }
    }

    /**
     * 设置数据范围
     */
    public static <T> void setDataScope(LambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String dataScope) {
        if (SoftDeleteUtil.isAll(dataScope)) {
            return;
        }

        Integer value = SoftDeleteUtil.getValue(dataScope);

        if (value != null) {
            queryWrapper.eq(columnName, value);
        }
    }

    /**
     * 设置数据范围
     */
    public static <T> void setDataScope(JoinLambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String dataScope) {
        if (SoftDeleteUtil.isAll(dataScope)) {
            return;
        }

        Integer value = SoftDeleteUtil.getValue(dataScope);

        if (value != null) {
            queryWrapper.eq(columnName, value);
        }
    }

    /**
     * 给QueryWrapper对象添加in条件
     *
     * @param queryWrapper QueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static void in(QueryWrapper queryWrapper, String columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.in(columnName, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给LambdaQueryWrapper对象添加in条件
     *
     * @param queryWrapper LambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void in(LambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.in(columnName, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给JoinLambdaQueryWrapper对象添加in条件
     *
     * @param queryWrapper JoinLambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void in(JoinLambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.in(columnName, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给QueryWrapper对象添加notIn条件
     *
     * @param queryWrapper QueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static void notIn(QueryWrapper queryWrapper, String columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.notIn(columnName, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给LambdaQueryWrapper对象添加notIn条件
     *
     * @param queryWrapper LambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void notIn(LambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.notIn(columnName, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给JoinLambdaQueryWrapper对象添加notIn条件
     *
     * @param queryWrapper JoinLambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void notIn(JoinLambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.notIn(columnName, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给QueryWrapper对象添加between条件
     *
     * @param queryWrapper QueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static void between(QueryWrapper queryWrapper, String columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.between(columnName, values[0], values[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给LambdaQueryWrapper对象添加between条件
     *
     * @param queryWrapper LambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void between(LambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.between(columnName, values[0], values[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给JoinLambdaQueryWrapper对象添加between条件
     *
     * @param queryWrapper JoinLambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void between(JoinLambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.between(columnName, values[0], values[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给QueryWrapper对象添加notBetween条件
     *
     * @param queryWrapper QueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static void notBetween(QueryWrapper queryWrapper, String columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.notBetween(columnName, values[0], values[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给LambdaQueryWrapper对象添加notBetween条件
     *
     * @param queryWrapper LambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void notBetween(LambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.notBetween(columnName, values[0], values[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给JoinLambdaQueryWrapper对象添加notBetween条件
     *
     * @param queryWrapper JoinLambdaQueryWrapper对象
     * @param columnName   字段名
     * @param value        查询条件值
     * @return
     * @author shaoyuyao
     * @date 2022/8/9 21:08
     */
    public static <T> void notBetween(JoinLambdaQueryWrapper queryWrapper, SFunction<T, ?> columnName, String value) {
        try {
            String[] values = value.split(",");
            queryWrapper.notBetween(columnName, values[0], values[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
