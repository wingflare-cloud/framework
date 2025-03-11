package com.wingflare.lib.mybatis.plus.wrapper;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.utils.SqlOperateUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

/**
 * 连表查询封装对象
 *
 * @author shaoyuyao
 */
public class JoinQueryWrapper<T> extends AbstractWrapper<T, String, JoinQueryWrapper<T>>
        implements Query<JoinQueryWrapper<T>, T, String>, JoinWrapper {

    /**
     * 查询列名SQL，例如 "student.student_id, student.student_name"
     */
    private final SharedString sqlSelect = new SharedString();

    /**
     * 查询列
     */
    private final List<String> selectColumns = new ArrayList<>();

    /**
     * 排除列
     */
    private final List<String> excludeColumns = new ArrayList<>();

    /**
     * 主表别名
     */
    private final SharedString masterTableAlias = SharedString.emptyString();

    /**
     * 连表SQL，例如 LEFT JOIN `student` ON `student`.student_id = `teacher`.student_id
     */
    private final SharedString joinSql = SharedString.emptyString();

    /**
     * 是否去重
     */
    private boolean isDistinct = false;

    public JoinQueryWrapper() {
        this(null);
    }

    public JoinQueryWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public JoinQueryWrapper(T entity, String... columns) {
        super.setEntity(entity);
        super.initNeed();
        this.select(columns);
    }

    /**
     * 非对外公开的构造方法,只用于生产嵌套 sql
     *
     * @param entityClass 本不应该需要的
     */
    private JoinQueryWrapper(T entity, Class<T> entityClass, AtomicInteger paramNameSeq, Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString paramAlias, SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.paramAlias = paramAlias;
        this.lastSql = lastSql;
        this.sqlComment = sqlComment;
        this.sqlFirst = sqlFirst;
    }

    /**
     * 查询结果去重
     *
     * @return
     */
    public JoinQueryWrapper<T> distinct() {
        this.isDistinct = true;
        return typedThis;
    }

    /**
     * 设置要查询指定表所有的列
     *
     * @param clazz 数据库实体类Class
     * @return
     */
    public JoinQueryWrapper<T> selectAll(Class<?> clazz) {
        selectAll(null, clazz);
        return typedThis;
    }

    /**
     * 设置要查询指定表所有的列
     *
     * @param tableAlias 表别名
     * @param clazz      数据库实体类Class
     * @return
     */
    public JoinQueryWrapper<T> selectAll(String tableAlias, Class<?> clazz) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        selectAll(tableAlias, tableInfo);
        return typedThis;
    }

    /**
     * 设置要查询指定表所有的列
     *
     * @param tableName 数据库表名
     * @return
     */
    public JoinQueryWrapper<T> selectAll(String tableName) {
        selectAll(null, tableName);
        return typedThis;
    }

    /**
     * 设置要查询指定表所有的列
     *
     * @param tableAlias 表别名
     * @param tableName  数据库表名
     * @return
     */
    public JoinQueryWrapper<T> selectAll(String tableAlias, String tableName) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(tableName);
        selectAll(tableAlias, tableInfo);
        return typedThis;
    }

    /**
     * 设置要查询指定表所有的列
     *
     * @param tableAlias 表别名
     * @param tableInfo  数据库表反射信息
     * @return
     */
    private JoinQueryWrapper<T> selectAll(String tableAlias, TableInfo tableInfo) {
        Assert.notNull(tableInfo, "tableInfo can not be null");
        if (StringUtils.isBlank(tableAlias)) {
            tableAlias = tableInfo.getTableName();
        }
        if (tableInfo.havePK()) {
            selectColumns.add(SqlOperateUtil.addTableNamePrefix(tableAlias, tableInfo.getKeyColumn()));
        }
        for (TableFieldInfo tableFieldInfo : tableInfo.getFieldList()) {
            selectColumns.add(SqlOperateUtil.addTableNamePrefix(tableAlias, tableFieldInfo.getColumn()));
        }
        return typedThis;
    }

    /**
     * 设置要查询的列名
     *
     * @param tableAlias 表别名，例如 "student"
     * @param columns    查询列名，例如 "student_name"...
     * @return
     */
    public JoinQueryWrapper<T> selectAlias(String tableAlias, String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            String[] processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
            selectColumns.addAll(Arrays.asList(processColumns));
        }
        return typedThis;
    }

    /**
     * 设置要查询的列名
     *
     * @param columnSql 查询列名SQL，例如 "student.student_id, student.student_name"
     * @return
     */
    public JoinQueryWrapper<T> select(String columnSql) {
        String[] columns = columnSql.split(Constant.COMMA);
        selectColumns.addAll(Arrays.asList(columns));
        return typedThis;
    }

    /**
     * 设置要查询的列名
     *
     * @param columns 表别名.查询列名，支持多个，例如 "student.student_name"...
     * @return
     */
    @Override
    public JoinQueryWrapper<T> select(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            selectColumns.addAll(Arrays.asList(columns));
        }
        return typedThis;
    }


    @Override
    public JoinQueryWrapper<T> select(boolean condition, List<String> columns) {
        if (CollectionUtils.isNotEmpty(columns)) {
            selectColumns.addAll(columns);
        }
        return typedThis;
    }

    /**
     * 设置要查询的列名和列别名
     *
     * @param column      查询列名
     * @param columnAlias 列别名
     * @return
     */
    public JoinQueryWrapper<T> selectAs(String column, String columnAlias) {
        selectColumns.add(column + Constant.AS_AROUND_SPACE + columnAlias);
        return typedThis;
    }

    /**
     * 设置要查询的列名和列别名
     *
     * @param tableAlias  表别名
     * @param column      查询列名
     * @param columnAlias 列别名
     * @return
     */
    public JoinQueryWrapper<T> selectAs(String tableAlias, String column, String columnAlias) {
        column = tableAlias + Constant.DOT + column;
        return selectAs(column, columnAlias);
    }

    /**
     * 此方法已作废，无法适配连表查询，禁止使用
     *
     * @param entityClass
     * @param predicate   过滤方式
     * @return
     */
    @Override
    @Deprecated
    public JoinQueryWrapper<T> select(Class<T> entityClass, Predicate<TableFieldInfo> predicate) {
        super.setEntityClass(entityClass);
        sqlSelect.setStringValue(TableInfoHelper.getTableInfo(getEntityClass()).chooseSelect(predicate));
        return typedThis;
    }

    /**
     * 设置要排除的列名
     *
     * @param tableAlias 表别名，例如 "student"
     * @param columns    排除列名，例如 "student_name"...
     * @return
     */
    public JoinQueryWrapper<T> excludeAlias(String tableAlias, String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            String[] processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
            excludeColumns.addAll(Arrays.asList(processColumns));
        }
        return typedThis;
    }

    /**
     * 设置要排除的列名
     *
     * @param columnSql 排除列名SQL，例如 "student.student_id, student.student_name"
     * @return
     */
    public JoinQueryWrapper<T> exclude(String columnSql) {
        String[] columns = columnSql.split(Constant.COMMA);
        excludeColumns.addAll(Arrays.asList(columns));
        return typedThis;
    }

    /**
     * 设置要排除的列名
     *
     * @param columns 表别名.排除列名，支持多个，例如 "student.student_name"...
     * @return
     */
    public JoinQueryWrapper<T> exclude(String... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            excludeColumns.addAll(Arrays.asList(columns));
        }
        return typedThis;
    }

    /**
     * 设置主表别名
     *
     * @param masterTableAlias 主表别名
     * @return
     */
    public JoinQueryWrapper<T> from(String masterTableAlias) {
        this.masterTableAlias.setStringValue(masterTableAlias);
        return typedThis;
    }

    /**
     * 设置内连接SQL
     *
     * @param tableName 表名
     * @param joinOnSql 连表条件SQL
     * @return
     */
    public JoinQueryWrapper<T> innerJoin(String tableName, String joinOnSql) {
        String joinSql = tableName + Constant.ON_AROUND_SPACE + joinOnSql;
        return innerJoin(joinSql);
    }

    /**
     * 设置内连接SQL
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     * @param joinOnSql  连表条件SQL
     * @return
     */
    public JoinQueryWrapper<T> innerJoin(String tableName, String tableAlias, String joinOnSql) {
        String joinSql = tableName + Constant.AS_AROUND_SPACE + tableAlias + Constant.ON_AROUND_SPACE + joinOnSql;
        return innerJoin(joinSql);
    }

    /**
     * 设置内连接SQL
     *
     * @param joinSql 连接SQL，例如 ”t_user on t_user.id = t_order.user_id“
     * @return
     */
    public JoinQueryWrapper<T> innerJoin(String joinSql) {
        return join(Constant.INNER_JOIN_AROUND_SPACE, joinSql);
    }

    /**
     * 设置左连接SQL
     *
     * @param tableName 表名
     * @param joinOnSql 连表条件SQL，例如 “t_user.id = t_order.user_id”
     * @return
     */
    public JoinQueryWrapper<T> leftJoin(String tableName, String joinOnSql) {
        String joinSql = tableName + Constant.ON_AROUND_SPACE + joinOnSql;
        return leftJoin(joinSql);
    }

    /**
     * 设置左连接SQL
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     * @param joinOnSql  连表条件SQL
     * @return
     */
    public JoinQueryWrapper<T> leftJoin(String tableName, String tableAlias, String joinOnSql) {
        String joinSql = tableName + Constant.AS_AROUND_SPACE + tableAlias + Constant.ON_AROUND_SPACE + joinOnSql;
        return leftJoin(joinSql);
    }

    /**
     * 设置左连接SQL
     *
     * @param joinSql 连接SQL，例如 ”t_user on t_user.id = t_order.user_id“
     * @return
     */
    public JoinQueryWrapper<T> leftJoin(String joinSql) {
        return join(Constant.LEFT_JOIN_AROUND_SPACE, joinSql);
    }

    /**
     * 设置右连接SQL
     *
     * @param tableAlias 表别名
     * @param joinOnSql  连表条件SQL
     * @return
     */
    public JoinQueryWrapper<T> rightJoin(String tableAlias, String joinOnSql) {
        String joinSql = tableAlias + Constant.ON_AROUND_SPACE + joinOnSql;
        return rightJoin(joinSql);
    }

    /**
     * 设置右连接SQL
     *
     * @param tableName  表名
     * @param tableAlias 表别名
     * @param joinOnSql  连表条件SQL
     * @return
     */
    public JoinQueryWrapper<T> rightJoin(String tableName, String tableAlias, String joinOnSql) {
        String joinSql = tableName + Constant.AS_AROUND_SPACE + tableAlias + Constant.ON_AROUND_SPACE + joinOnSql;
        return rightJoin(joinSql);
    }

    /**
     * 设置右连接SQL
     *
     * @param joinSql 连接SQL，例如 ”t_user on t_user.id = t_order.user_id“
     * @return
     */
    public JoinQueryWrapper<T> rightJoin(String joinSql) {
        return join(Constant.RIGHT_JOIN_AROUND_SPACE, joinSql);
    }

    /**
     * 设置连接SQL
     *
     * @param joinType 连接类型
     * @param joinSql  连接SQL
     * @return
     */
    private JoinQueryWrapper<T> join(String joinType, String joinSql) {
        joinSql = this.joinSql.getStringValue() + Constant.SPACE + joinType + joinSql;
        this.joinSql.setStringValue(joinSql);
        return typedThis;
    }

    // region
    // 条件构造器：https://baomidou.com/pages/10c804/#abstractwrapper
    public JoinQueryWrapper<T> eq(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return eq(column, val);
    }

    @Override
    public JoinQueryWrapper<T> eq(String column, Object val) {
        return super.eq(column, val);
    }

    public JoinQueryWrapper<T> ne(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return ne(column, val);
    }

    @Override
    public JoinQueryWrapper<T> ne(String column, Object val) {
        return super.ne(column, val);
    }

    public JoinQueryWrapper<T> gt(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return gt(column, val);
    }

    @Override
    public JoinQueryWrapper<T> gt(String column, Object val) {
        return super.gt(column, val);
    }

    public JoinQueryWrapper<T> ge(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return ge(column, val);
    }

    @Override
    public JoinQueryWrapper<T> ge(String column, Object val) {
        return super.ge(column, val);
    }

    public JoinQueryWrapper<T> lt(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return lt(column, val);
    }

    @Override
    public JoinQueryWrapper<T> lt(String column, Object val) {
        return super.lt(column, val);
    }

    public JoinQueryWrapper<T> le(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return le(column, val);
    }

    @Override
    public JoinQueryWrapper<T> le(String column, Object val) {
        return super.le(column, val);
    }

    public JoinQueryWrapper<T> between(String tableAlias, String column, Object val1, Object val2) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return between(column, val1, val2);
    }

    @Override
    public JoinQueryWrapper<T> between(String column, Object val1, Object val2) {
        return super.between(column, val1, val2);
    }

    public JoinQueryWrapper<T> notBetween(String tableAlias, String column, Object val1, Object val2) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return notBetween(column, val1, val2);
    }

    @Override
    public JoinQueryWrapper<T> notBetween(String column, Object val1, Object val2) {
        return super.notBetween(column, val1, val2);
    }

    public JoinQueryWrapper<T> like(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return like(column, val);
    }

    @Override
    public JoinQueryWrapper<T> like(String column, Object val) {
        return super.like(column, val);
    }

    public JoinQueryWrapper<T> notLike(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return notLike(column, val);
    }

    @Override
    public JoinQueryWrapper<T> notLike(String column, Object val) {
        return super.notLike(column, val);
    }

    public JoinQueryWrapper<T> likeLeft(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return likeLeft(column, val);
    }

    @Override
    public JoinQueryWrapper<T> likeLeft(String column, Object val) {
        return super.likeLeft(column, val);
    }

    public JoinQueryWrapper<T> likeRight(String tableAlias, String column, Object val) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return likeRight(column, val);
    }

    @Override
    public JoinQueryWrapper<T> likeRight(String column, Object val) {
        return super.likeRight(column, val);
    }

    public JoinQueryWrapper<T> isNull(String tableAlias, String column) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return isNull(column);
    }

    @Override
    public JoinQueryWrapper<T> isNull(String column) {
        return super.isNull(column);
    }

    public JoinQueryWrapper<T> isNotNull(String tableAlias, String column) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return isNotNull(column);
    }

    @Override
    public JoinQueryWrapper<T> isNotNull(String column) {
        return super.isNotNull(column);
    }

    public JoinQueryWrapper<T> in(String tableAlias, String column, Collection<?> coll) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return in(column, coll);
    }

    @Override
    public JoinQueryWrapper<T> in(String column, Collection<?> coll) {
        return super.in(column, coll);
    }

    public JoinQueryWrapper<T> in(String tableAlias, String column, Object... values) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return in(column, values);
    }

    @Override
    public JoinQueryWrapper<T> in(String column, Object... values) {
        return super.in(column, values);
    }

    public JoinQueryWrapper<T> notIn(String tableAlias, String column, Collection<?> coll) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return notIn(column, coll);
    }

    @Override
    public JoinQueryWrapper<T> notIn(String column, Collection<?> coll) {
        return super.notIn(column, coll);
    }

    public JoinQueryWrapper<T> notIn(String tableAlias, String column, Object... values) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return notIn(column, values);
    }

    @Override
    public JoinQueryWrapper<T> notIn(String column, Object... value) {
        return super.notIn(column, value);
    }

    public JoinQueryWrapper<T> inSql(String tableAlias, String column, String subSql) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return inSql(column, subSql);
    }

    @Override
    public JoinQueryWrapper<T> inSql(String column, String inValue) {
        return super.inSql(column, inValue);
    }

    public JoinQueryWrapper<T> notInSql(String tableAlias, String column, String subSql) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return notInSql(column, subSql);
    }

    @Override
    public JoinQueryWrapper<T> notInSql(String column, String inValue) {
        return super.notInSql(column, inValue);
    }

    public JoinQueryWrapper<T> groupByAlias(String tableAlias, String column) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return groupBy(column);
    }

    @Override
    public JoinQueryWrapper<T> groupBy(String column) {
        return super.groupBy(column);
    }

    public JoinQueryWrapper<T> groupByAlias(String tableAlias, String column, String... columns) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        String[] processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return groupBy(column, processColumns);
    }

    @Override
    public JoinQueryWrapper<T> groupBy(String column, String... columns) {
        return super.groupBy(column, columns);
    }

    public JoinQueryWrapper<T> groupByAlias(String tableAlias, List<String> columns) {
        List<String> processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return groupBy(processColumns);
    }

    @Override
    public JoinQueryWrapper<T> groupBy(List<String> columns) {
        return super.groupBy(columns);
    }

    public JoinQueryWrapper<T> orderByAsc(String tableAlias, String column) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return orderByAsc(column);
    }

    @Override
    public JoinQueryWrapper<T> orderByAsc(String column) {
        return super.orderByAsc(column);
    }

    public JoinQueryWrapper<T> orderByAscAlias(String tableAlias, String column, String... columns) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        String[] processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return orderByAsc(column, processColumns);
    }

    @Override
    public JoinQueryWrapper<T> orderByAsc(String column, String... columns) {
        return super.orderByAsc(column, columns);
    }

    public JoinQueryWrapper<T> orderByAscAlias(String tableAlias, List<String> columns) {
        List<String> processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return orderByAsc(processColumns);
    }

    @Override
    public JoinQueryWrapper<T> orderByAsc(List<String> columns) {
        return super.orderByAsc(columns);
    }

    public JoinQueryWrapper<T> orderByDescAlias(String tableAlias, String column) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return orderByDesc(column);
    }

    @Override
    public JoinQueryWrapper<T> orderByDesc(String column) {
        return super.orderByDesc(column);
    }

    public JoinQueryWrapper<T> orderByDescAlias(String tableAlias, String column, String... columns) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        String[] processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return orderByDesc(column, processColumns);
    }

    @Override
    public JoinQueryWrapper<T> orderByDesc(String column, String... columns) {
        return super.orderByDesc(column, columns);
    }

    public JoinQueryWrapper<T> orderByDescAlias(String tableAlias, List<String> columns) {
        List<String> processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return orderByDesc(processColumns);
    }

    @Override
    public JoinQueryWrapper<T> orderByDesc(List<String> columns) {
        return super.orderByDesc(columns);
    }

    public JoinQueryWrapper<T> orderByAlias(boolean isAsc, String tableAlias, String column) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        return orderBy(isAsc, column);
    }

    public JoinQueryWrapper<T> orderBy(boolean isAsc, String column) {
        super.orderBy(true, isAsc, column);
        return typedThis;
    }

    public JoinQueryWrapper<T> orderByAlias(boolean isAsc, String tableAlias, String column, String... columns) {
        column = SqlOperateUtil.addTableNamePrefix(tableAlias, column);
        String[] processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return orderBy(isAsc, column, processColumns);
    }

    public JoinQueryWrapper<T> orderBy(boolean isAsc, String column, String... columns) {
        super.orderBy(true, isAsc, column, columns);
        return typedThis;
    }

    public JoinQueryWrapper<T> orderByAlias(boolean isAsc, String tableAlias, List<String> columns) {
        List<String> processColumns = SqlOperateUtil.addTableNamePrefixBatch(tableAlias, columns);
        return orderBy(isAsc, processColumns);
    }

    public JoinQueryWrapper<T> orderBy(boolean isAsc, List<String> columns) {
        super.orderBy(true, isAsc, columns);
        return typedThis;
    }
    // endregion

    @Override
    public String getSqlSelect() {
        if (StringUtils.isBlank(sqlSelect.getStringValue())) {
            if (CollectionUtils.isNotEmpty(excludeColumns)) {
                selectColumns.removeIf(excludeColumns::contains);
            }
            sqlSelect.setStringValue(String.join(Constant.COMMA, selectColumns));
        }
        return sqlSelect.getStringValue();
    }

    public String getMasterTableAlias() {
        return masterTableAlias.getStringValue();
    }

    public String getJoinSql() {
        return joinSql.getStringValue();
    }

    public List<String> getSelectColumns() {
        return selectColumns;
    }

    public List<String> getExcludeColumns() {
        return excludeColumns;
    }

    public boolean isDistinct() {
        return isDistinct;
    }


    protected String columnSqlInjectFilter(String column) {
        return StringUtils.sqlInjectionReplaceBlank(column);
    }

    /**
     * 返回一个支持 lambda 函数写法的 wrapper
     */
    public JoinLambdaQueryWrapper<T> lambda() {
        return new JoinLambdaQueryWrapper<>(getEntity(), getEntityClass(), sqlSelect, paramNameSeq, paramNameValuePairs, expression, paramAlias, lastSql, sqlComment, sqlFirst);
    }

    /**
     * 用于生成嵌套 sql
     * <p>
     * 故 sqlSelect 不向下传递
     * </p>
     */
    @Override
    protected JoinQueryWrapper<T> instance() {
        return new JoinQueryWrapper<>(getEntity(), getEntityClass(), paramNameSeq, paramNameValuePairs, new MergeSegments(), paramAlias, SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
    }

    @Override
    public void clear() {
        super.clear();
        sqlSelect.toNull();
        masterTableAlias.toNull();
        joinSql.toNull();
        selectColumns.clear();
        excludeColumns.clear();
    }

}
