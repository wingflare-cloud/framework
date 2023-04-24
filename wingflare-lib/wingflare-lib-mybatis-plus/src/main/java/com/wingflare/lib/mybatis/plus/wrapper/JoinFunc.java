





































































































































































































































































































































































































package com.wingflare.lib.mybatis.plus.wrapper;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * 查询条件封装
 * 函数
 * <p>
 * 在 {@link com.baomidou.mybatisplus.core.conditions.interfaces.Func} 的基础上重写，主要是 MyBatis-Plus 的 Func 不支持连表查询
 * 将原来的泛型 R 修改为 SFunction<E, ?>
 * </p>
 *
 * @author shaoyuyao
 * @date 2022/8/24 12:40
 */
public interface JoinFunc<Children> extends Serializable {


    /**
     * ignore
     */
    default <E> Children isNull(SFunction<E, ?> column) {
        return isNull(true, column);
    }

    /**
     * 字段 IS NULL
     * <p>例: isNull("name")</p>
     *
     * @param condition 执行条件
     * @param column    字段
     * @return children
     */
    <E> Children isNull(boolean condition, SFunction<E, ?> column);

    /**
     * ignore
     */
    default <E> Children isNotNull(SFunction<E, ?> column) {
        return isNotNull(true, column);
    }

    /**
     * 字段 IS NOT NULL
     * <p>例: isNotNull("name")</p>
     *
     * @param condition 执行条件
     * @param column    字段
     * @return children
     */
    <E> Children isNotNull(boolean condition, SFunction<E, ?> column);

    /**
     * ignore
     */
    default <E> Children in(SFunction<E, ?> column, Collection<?> coll) {
        return in(true, column, coll);
    }

    /**
     * 字段 IN (value.get(0), value.get(1), ...)
     * <p>例: in("id", Arrays.asList(1, 2, 3, 4, 5))</p>
     *
     * <li> 注意！集合为空若存在逻辑错误，请在 condition 条件中判断 </li>
     * <li> 如果集合为 empty 则不会进行 sql 拼接 </li>
     *
     * @param condition 执行条件
     * @param column    字段
     * @param coll      数据集合
     * @return children
     */
    <E> Children in(boolean condition, SFunction<E, ?> column, Collection<?> coll);

    /**
     * ignore
     */
    default <E> Children in(SFunction<E, ?> column, Object... values) {
        return in(true, column, values);
    }

    /**
     * 字段 IN (v0, v1, ...)
     * <p>例: in("id", 1, 2, 3, 4, 5)</p>
     *
     * <li> 注意！数组为空若存在逻辑错误，请在 condition 条件中判断 </li>
     * <li> 如果动态数组为 empty 则不会进行 sql 拼接 </li>
     *
     * @param condition 执行条件
     * @param column    字段
     * @param values    数据数组
     * @return children
     */
    <E> Children in(boolean condition, SFunction<E, ?> column, Object... values);

    /**
     * ignore
     */
    default <E> Children notIn(SFunction<E, ?> column, Collection<?> coll) {
        return notIn(true, column, coll);
    }

    /**
     * 字段 NOT IN (value.get(0), value.get(1), ...)
     * <p>例: notIn("id", Arrays.asList(1, 2, 3, 4, 5))</p>
     *
     * @param condition 执行条件
     * @param column    字段
     * @param coll      数据集合
     * @return children
     */
    <E> Children notIn(boolean condition, SFunction<E, ?> column, Collection<?> coll);

    /**
     * ignore
     */
    default <E> Children notIn(SFunction<E, ?> column, Object... value) {
        return notIn(true, column, value);
    }

    /**
     * 字段 NOT IN (v0, v1, ...)
     * <p>例: notIn("id", 1, 2, 3, 4, 5)</p>
     *
     * @param condition 执行条件
     * @param column    字段
     * @param values    数据数组
     * @return children
     */
    <E> Children notIn(boolean condition, SFunction<E, ?> column, Object... values);

    /**
     * ignore
     */
    default <E> Children inSql(SFunction<E, ?> column, String inValue) {
        return inSql(true, column, inValue);
    }

    /**
     * 字段 IN ( sql语句 )
     * <p>!! sql 注入方式的 in 方法 !!</p>
     * <p>例1: inSql("id", "1, 2, 3, 4, 5, 6")</p>
     * <p>例2: inSql("id", "select id from table where id &lt; 3")</p>
     *
     * @param condition 执行条件
     * @param column    字段
     * @param inValue   sql语句
     * @return children
     */
    <E> Children inSql(boolean condition, SFunction<E, ?> column, String inValue);

    /**
     * 字段 &gt; ( sql语句 )
     * <p>例1: gtSql("id", "1, 2, 3, 4, 5, 6")</p>
     * <p>例1: gtSql("id", "select id from table where name = 'JunJun'")</p>
     *
     * @param condition
     * @param column
     * @param inValue
     * @return
     */
    <E> Children gtSql(boolean condition, SFunction<E, ?> column, String inValue);

    /**
     * ignore
     */
    default <E> Children gtSql(SFunction<E, ?> column, String inValue) {
        return gtSql(true, column, inValue);
    }

    /**
     * 字段 >= ( sql语句 )
     * <p>例1: geSql("id", "1, 2, 3, 4, 5, 6")</p>
     * <p>例1: geSql("id", "select id from table where name = 'JunJun'")</p>
     *
     * @param condition
     * @param column
     * @param inValue
     * @return
     */
    <E> Children geSql(boolean condition, SFunction<E, ?> column, String inValue);

    /**
     * ignore
     */
    default <E> Children geSql(SFunction<E, ?> column, String inValue) {
        return geSql(true, column, inValue);
    }

    /**
     * 字段 &lt; ( sql语句 )
     * <p>例1: ltSql("id", "1, 2, 3, 4, 5, 6")</p>
     * <p>例1: ltSql("id", "select id from table where name = 'JunJun'")</p>
     *
     * @param condition
     * @param column
     * @param inValue
     * @return
     */
    <E> Children ltSql(boolean condition, SFunction<E, ?> column, String inValue);

    /**
     * ignore
     */
    default <E> Children ltSql(SFunction<E, ?> column, String inValue) {
        return ltSql(true, column, inValue);
    }

    /**
     * 字段 <= ( sql语句 )
     * <p>例1: leSql("id", "1, 2, 3, 4, 5, 6")</p>
     * <p>例1: leSql("id", "select id from table where name = 'JunJun'")</p>
     *
     * @param condition
     * @param column
     * @param inValue
     * @return
     */
    <E> Children leSql(boolean condition, SFunction<E, ?> column, String inValue);

    /**
     * ignore
     */
    default <E> Children leSql(SFunction<E, ?> column, String inValue) {
        return leSql(true, column, inValue);
    }

    /**
     * ignore
     */
    default <E> Children notInSql(SFunction<E, ?> column, String inValue) {
        return notInSql(true, column, inValue);
    }

    /**
     * 字段 NOT IN ( sql语句 )
     * <p>!! sql 注入方式的 not in 方法 !!</p>
     * <p>例1: notInSql("id", "1, 2, 3, 4, 5, 6")</p>
     * <p>例2: notInSql("id", "select id from table where id &lt; 3")</p>
     *
     * @param condition 执行条件
     * @param column    字段
     * @param inValue   sql语句 ---&gt; 1,2,3,4,5,6 或者 select id from table where id &lt; 3
     * @return children
     */
    <E> Children notInSql(boolean condition, SFunction<E, ?> column, String inValue);

    /**
     * 分组：GSFunction<E, ?>OUP BY 字段, ...
     * <p>例: groupBy("id")</p>
     *
     * @param condition 执行条件
     * @param column    单个字段
     * @return children
     */
    <E> Children groupBy(boolean condition, SFunction<E, ?> column);

    default <E> Children groupBy(SFunction<E, ?> column) {
        return groupBy(true, column);
    }

    /**
     * 分组：GSFunction<E, ?>OUP BY 字段, ...
     * <p>例: groupBy(Arrays.asList("id", "name"))</p>
     *
     * @param condition 执行条件
     * @param columns   字段数组
     * @return children
     */
    <E> Children groupBy(boolean condition, List<SFunction<E, ?>> columns);

    default <E> Children groupBy(List<SFunction<E, ?>> columns) {
        return groupBy(true, columns);
    }

    default <E> Children groupBy(SFunction<E, ?> column, SFunction<E, ?>... columns) {
        return groupBy(true, column, columns);
    }

    /**
     * 分组：GSFunction<E, ?>OUP BY 字段, ...
     */
    <E> Children groupBy(boolean condition, SFunction<E, ?> column, SFunction<E, ?>... columns);

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ... ASC
     * <p>例: orderByAsc(true, "id")</p>
     *
     * @param condition 执行条件
     * @param column    单个字段
     * @return children
     */
    default <E> Children orderByAsc(boolean condition, SFunction<E, ?> column) {
        return orderBy(condition, true, column);
    }

    default <E> Children orderByAsc(SFunction<E, ?> column) {
        return orderByAsc(true, column);
    }

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ... ASC
     * <p>例: orderByAsc(true, Arrays.asList("id", "name"))</p>
     *
     * @param condition 执行条件
     * @param columns   字段数组
     * @return children
     */
    default <E> Children orderByAsc(boolean condition, List<SFunction<E, ?>> columns) {
        return orderBy(condition, true, columns);
    }

    default <E> Children orderByAsc(List<SFunction<E, ?>> columns) {
        return orderByAsc(true, columns);
    }

    default <E> Children orderByAsc(SFunction<E, ?> column, SFunction<E, ?>... columns) {
        return orderByAsc(true, column, columns);
    }

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ... ASC
     */
    default <E> Children orderByAsc(boolean condition, SFunction<E, ?> column, SFunction<E, ?>... columns) {
        return orderBy(condition, true, column, columns);
    }

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ... DESC
     * <p>例: orderByDesc(true, "id")</p>
     *
     * @param condition 执行条件
     * @param column    字段
     * @return children
     */
    default <E> Children orderByDesc(boolean condition, SFunction<E, ?> column) {
        return orderBy(condition, false, column);
    }

    default <E> Children orderByDesc(SFunction<E, ?> column) {
        return orderByDesc(true, column);
    }

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ... DESC
     * <p>例: orderByDesc(true, Arrays.asList("id", "name"))</p>
     *
     * @param condition 执行条件
     * @param columns   字段列表
     * @return children
     */
    default <E> Children orderByDesc(boolean condition, List<SFunction<E, ?>> columns) {
        return orderBy(condition, false, columns);
    }

    default <E> Children orderByDesc(List<SFunction<E, ?>> columns) {
        return orderByDesc(true, columns);
    }

    default <E> Children orderByDesc(SFunction<E, ?> column, SFunction<E, ?>... columns) {
        return orderByDesc(true, column, columns);
    }

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ... DESC
     */
    default <E> Children orderByDesc(boolean condition, SFunction<E, ?> column, SFunction<E, ?>... columns) {
        return orderBy(condition, false, column, columns);
    }

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ...
     * <p>例: orderBy(true, "id")</p>
     *
     * @param condition 执行条件
     * @param isAsc     是否是 ASC 排序
     * @param column    单个字段
     * @return children
     */
    <E> Children orderBy(boolean condition, boolean isAsc, SFunction<E, ?> column);

    /**
     * 满足条件的数据进行排序
     *
     * @param isAsc      是否升序
     * @param column     单个字段
     * @param sqlKeyword SQL关键字
     * @param <E>
     * @return
     */
    default <E> Children orderByCondition(boolean isAsc, SFunction<E, ?> column, SqlKeyword sqlKeyword, ISqlSegment sqlSegment) {
        return orderByCondition(true, isAsc, column, sqlKeyword, sqlSegment);
    }

    /**
     * 满足条件的数据进行排序
     *
     * @param condition  过滤条件
     * @param isAsc      是否升序
     * @param column     单个字段
     * @param sqlKeyword SQL关键字
     * @param <E>
     * @return
     */
    <E> Children orderByCondition(boolean condition, boolean isAsc, SFunction<E, ?> column, SqlKeyword sqlKeyword, ISqlSegment sqlSegment);

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ...
     * <p>例: orderBy(true, Arrays.asList("id", "name"))</p>
     *
     * @param condition 执行条件
     * @param isAsc     是否是 ASC 排序
     * @param columns   字段列表
     * @return children
     */
    <E> Children orderBy(boolean condition, boolean isAsc, List<SFunction<E, ?>> columns);

    /**
     * 排序：OSFunction<E, ?>DESFunction<E, ?> BY 字段, ...
     */
    <E> Children orderBy(boolean condition, boolean isAsc, SFunction<E, ?> column, SFunction<E, ?>... columns);

    /**
     * ignore
     */
    default <E> Children having(String sqlHaving, Object... params) {
        return having(true, sqlHaving, params);
    }

    /**
     * HAVING ( sql语句 )
     * <p>例1: having("sum(age) &gt; 10")</p>
     * <p>例2: having("sum(age) &gt; {0}", 10)</p>
     *
     * @param condition 执行条件
     * @param sqlHaving sql 语句
     * @param params    参数数组
     * @return children
     */
    <E> Children having(boolean condition, String sqlHaving, Object... params);

    /**
     * ignore
     */
    default Children func(Consumer<Children> consumer) {
        return func(true, consumer);
    }

    /**
     * 消费函数
     *
     * @param consumer 消费函数
     * @return children
     * @since 3.3.1
     */
    Children func(boolean condition, Consumer<Children> consumer);
}