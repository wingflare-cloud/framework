package com.wingflare.lib.mybatis.plus.wrapper;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.io.Serializable;
import java.util.Map;
import java.util.function.BiPredicate;

/**
 * 查询条件封装
 * 比较值
 * <p>
 * 在 {@link com.baomidou.mybatisplus.core.conditions.interfaces.Compare} 的基础上重写，主要是 MyBatis-Plus 的 Compare 不支持连表查询
 * 将原来的泛型 R 修改为 SFunction<E, ?>
 * </p>
 *
 * @author shaoyuyao
 * @date 2022/8/24 12:40
 */
public interface LambdaCompare<Children> extends Serializable {


    /**
     * ignore
     */
    default <E, V> Children allEq(Map<SFunction<E, ?>, V> params) {
        return allEq(params, true);
    }

    /**
     * ignore
     */
    default <E, V> Children allEq(Map<SFunction<E, ?>, V> params, boolean null2IsNull) {
        return allEq(true, params, null2IsNull);
    }

    /**
     * map 所有非空属性等于 =
     *
     * @param condition   执行条件
     * @param params      map 类型的参数, key 是字段名, value 是字段值
     * @param null2IsNull 是否参数为 null 自动执行 isNull 方法, false 则忽略这个字段\
     * @return children
     */
    <E, V> Children allEq(boolean condition, Map<SFunction<E, ?>, V> params, boolean null2IsNull);

    /**
     * ignore
     */
    default <E, V> Children allEq(BiPredicate<SFunction<E, ?>, V> filter, Map<SFunction<E, ?>, V> params) {
        return allEq(filter, params, true);
    }

    /**
     * ignore
     */
    default <E, V> Children allEq(BiPredicate<SFunction<E, ?>, V> filter, Map<SFunction<E, ?>, V> params, boolean null2IsNull) {
        return allEq(true, filter, params, null2IsNull);
    }

    /**
     * 字段过滤接口，传入多参数时允许对参数进行过滤
     *
     * @param condition   执行条件
     * @param filter      返回 true 来允许字段传入比对条件中
     * @param params      map 类型的参数, key 是字段名, value 是字段值
     * @param null2IsNull 是否参数为 null 自动执行 isNull 方法, false 则忽略这个字段
     * @return children
     */
    <E, V> Children allEq(boolean condition, BiPredicate<SFunction<E, ?>, V> filter, Map<SFunction<E, ?>, V> params, boolean null2IsNull);

    /**
     * ignore
     */
    default <E> Children eq(SFunction<E, ?> column, Object val) {
        return eq(true, column, val);
    }

    /**
     * 等于 =
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children eq(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children ne(SFunction<E, ?> column, Object val) {
        return ne(true, column, val);
    }

    /**
     * 不等于 &lt;&gt;
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children ne(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children gt(SFunction<E, ?> column, Object val) {
        return gt(true, column, val);
    }

    /**
     * 大于 &gt;
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children gt(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children ge(SFunction<E, ?> column, Object val) {
        return ge(true, column, val);
    }

    /**
     * 大于等于 &gt;=
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children ge(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children lt(SFunction<E, ?> column, Object val) {
        return lt(true, column, val);
    }

    /**
     * 小于 &lt;
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children lt(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children le(SFunction<E, ?> column, Object val) {
        return le(true, column, val);
    }

    /**
     * 小于等于 &lt;=
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children le(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children between(SFunction<E, ?> column, Object val1, Object val2) {
        return between(true, column, val1, val2);
    }

    /**
     * BETWEEN 值1 AND 值2
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val1      值1
     * @param val2      值2
     * @return children
     */
    <E> Children between(boolean condition, SFunction<E, ?> column, Object val1, Object val2);

    /**
     * ignore
     */
    default <E> Children notBetween(SFunction<E, ?> column, Object val1, Object val2) {
        return notBetween(true, column, val1, val2);
    }

    /**
     * NOT BETWEEN 值1 AND 值2
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val1      值1
     * @param val2      值2
     * @return children
     */
    <E> Children notBetween(boolean condition, SFunction<E, ?> column, Object val1, Object val2);

    /**
     * ignore
     */
    default <E> Children like(SFunction<E, ?> column, Object val) {
        return like(true, column, val);
    }

    /**
     * LIKE '%值%'
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children like(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children notLike(SFunction<E, ?> column, Object val) {
        return notLike(true, column, val);
    }

    /**
     * NOT LIKE '%值%'
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children notLike(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children likeLeft(SFunction<E, ?> column, Object val) {
        return likeLeft(true, column, val);
    }

    /**
     * LIKE '%值'
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children likeLeft(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children notLikeLeft(SFunction<E, ?> column, Object val) {
        return notLikeLeft(true, column, val);
    }

    /**
     * NOT LIKE '%值'
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children notLikeLeft(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children likeRight(SFunction<E, ?> column, Object val) {
        return likeRight(true, column, val);
    }

    /**
     * LIKE '值%'
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children likeRight(boolean condition, SFunction<E, ?> column, Object val);

    /**
     * ignore
     */
    default <E> Children notLikeRight(SFunction<E, ?> column, Object val) {
        return notLikeRight(true, column, val);
    }

    /**
     * NOT LIKE '值%'
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    <E> Children notLikeRight(boolean condition, SFunction<E, ?> column, Object val);
}
