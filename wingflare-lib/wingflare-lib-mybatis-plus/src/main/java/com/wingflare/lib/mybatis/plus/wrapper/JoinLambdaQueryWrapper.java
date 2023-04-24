package com.wingflare.lib.mybatis.plus.wrapper;


import com.baomidou.mybatisplus.core.conditions.SharedString;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.enums.fucntion.AggregateFunction;
import com.wingflare.lib.mybatis.plus.enums.fucntion.DbFunction;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 连表查询封装对象，Lambda风格
 *
 * @author shaoyuyao
 */
public class JoinLambdaQueryWrapper<T> extends AbstractJoinLambdaWrapper<T, JoinLambdaQueryWrapper<T>>
        implements JoinWrapper {

    /**
     * 查询列名SQL，例如 "student.student_id, student.student_name"
     */
    private SharedString sqlSelect = new SharedString();

    /**
     * 查询列
     */
    private final List<SelectColumn> selectColumns = new ArrayList<>();

    /**
     * 排除列
     */
    private final List<SelectColumn> excludeColumns = new ArrayList<>();

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

    /**
     * 表别名标记
     */
    private Integer tableAliasIndex = 2;

    public JoinLambdaQueryWrapper() {
        this((T) null);
    }

    public JoinLambdaQueryWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public JoinLambdaQueryWrapper(Class<T> entityClass) {
        super.setEntityClass(entityClass);
        super.initNeed();
    }

    JoinLambdaQueryWrapper(T entity, Class<T> entityClass, SharedString sqlSelect, AtomicInteger paramNameSeq,
                           Map<String, Object> paramNameValuePairs, MergeSegments mergeSegments, SharedString paramAlias,
                           SharedString lastSql, SharedString sqlComment, SharedString sqlFirst) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.paramNameSeq = paramNameSeq;
        this.paramNameValuePairs = paramNameValuePairs;
        this.expression = mergeSegments;
        this.sqlSelect = sqlSelect;
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
    public JoinLambdaQueryWrapper<T> distinct() {
        this.isDistinct = true;
        return typedThis;
    }

    /**
     * 设置要查询指定表所有的列
     *
     * @param clazz 实体类Class
     */
    public final JoinLambdaQueryWrapper<T> selectAll(Class<?> clazz) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        Assert.notNull(tableInfo, "tableInfo can not be null");
        if (tableInfo.havePK()) {
            selectColumns.add(SelectColumn.of(clazz, tableInfo.getKeyColumn()));
        }
        for (TableFieldInfo tableFieldInfo : tableInfo.getFieldList()) {
            selectColumns.add(SelectColumn.of(clazz, tableFieldInfo.getColumn()));
        }
        return typedThis;
    }

    /**
     * 设置要查询的列
     *
     * @param columns 要查询的列
     */
    public <E> JoinLambdaQueryWrapper<T> select(SFunction<E, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            for (SFunction<E, ?> column : columns) {
                selectColumns.add(SelectColumn.of(getClazz(column), getColumnName(column)));
            }
        }
        return typedThis;
    }

    public <E, D> JoinLambdaQueryWrapper<T> selectAs(SFunction<E, ?> column, SFunction<D, ?> columnAlias) {
        return selectAs(column, getPropertyName(columnAlias));
    }

    public <E> JoinLambdaQueryWrapper<T> selectAs(SFunction<E, ?> column, String columnAlias) {
        selectColumns.add(SelectColumn.of(getClazz(column), getColumnName(column), columnAlias));
        return typedThis;
    }

    public <E> JoinLambdaQueryWrapper<T> exclude(SFunction<E, ?>... columns) {
        if (ArrayUtils.isNotEmpty(columns)) {
            for (SFunction<E, ?> column : columns) {
                excludeColumns.add(SelectColumn.of(getClazz(column), getColumnName(column)));
            }
        }
        return typedThis;
    }

    // region 函数

    // region 聚合函数
    public <E, D> JoinLambdaQueryWrapper<T> selectAvg(SFunction<E, ?> column, SFunction<D, ?> columnAlias) {
        return selectFunc(column, columnAlias, AggregateFunction.AVG);
    }

    public <E, D> JoinLambdaQueryWrapper<T> selectCount(SFunction<E, ?> column, SFunction<D, ?> columnAlias) {
        return selectFunc(column, columnAlias, AggregateFunction.COUNT);
    }

    public <E, D> JoinLambdaQueryWrapper<T> selectMin(SFunction<E, ?> column, SFunction<D, ?> columnAlias) {
        return selectFunc(column, columnAlias, AggregateFunction.MIN);
    }

    public <E, D> JoinLambdaQueryWrapper<T> selectMax(SFunction<E, ?> column, SFunction<D, ?> columnAlias) {
        return selectFunc(column, columnAlias, AggregateFunction.MAX);
    }

    public <E, D> JoinLambdaQueryWrapper<T> selectSum(SFunction<E, ?> column, SFunction<D, ?> columnAlias) {
        return selectFunc(column, columnAlias, AggregateFunction.SUM);
    }

    public <E, D> JoinLambdaQueryWrapper<T> selectGroupConcat(SFunction<E, ?> column) {
        return selectFunc(column, column, AggregateFunction.GROUP_CONCAT);
    }

    public <E, D> JoinLambdaQueryWrapper<T> selectGroupConcat(SFunction<E, ?> column, SFunction<D, ?> columnAlias) {
        return selectFunc(column, columnAlias, AggregateFunction.GROUP_CONCAT);
    }
    // endregion

    public <E, D> JoinLambdaQueryWrapper<T> selectFunc(SFunction<E, ?> column, SFunction<D, ?> columnAlias, DbFunction dbFunction) {
        return selectFunc(column, getPropertyName(columnAlias), dbFunction);
    }

    public <E> JoinLambdaQueryWrapper<T> selectFunc(SFunction<E, ?> column, String columnAlias, DbFunction dbFunction) {
        selectColumns.add(SelectColumn.of(getClazz(column), getColumnName(column), columnAlias, dbFunction));
        return typedThis;
    }
    // endregion

    public <L, R> JoinLambdaQueryWrapper<T> innerJoin(Class<L> clazz, SFunction<L, ?> left, SFunction<R, ?> right, OnCondition... onConditions) {
        return join(Constant.INNER_JOIN_AROUND_SPACE, clazz, left, right, onConditions);
    }

    public <L, R> JoinLambdaQueryWrapper<T> innerJoin(Class<L> clazz, OnCondition... onConditions) {
        return join(Constant.INNER_JOIN_AROUND_SPACE, clazz, null, null, onConditions);
    }

    public <L, R> JoinLambdaQueryWrapper<T> leftJoin(Class<L> clazz, SFunction<L, ?> left, SFunction<R, ?> right, OnCondition... onConditions) {
        return join(Constant.LEFT_JOIN_AROUND_SPACE, clazz, left, right, onConditions);
    }

    public <L, R> JoinLambdaQueryWrapper<T> leftJoin(Class<L> clazz, OnCondition... onConditions) {
        return join(Constant.LEFT_JOIN_AROUND_SPACE, clazz, null, null, onConditions);
    }

    public <L, R> JoinLambdaQueryWrapper<T> rightJoin(Class<L> clazz, SFunction<L, ?> left, SFunction<R, ?> right, OnCondition... onConditions) {
        return join(Constant.RIGHT_JOIN_AROUND_SPACE, clazz, left, right, onConditions);
    }

    public <L, R> JoinLambdaQueryWrapper<T> rightJoin(Class<L> clazz, OnCondition... onConditions) {
        return join(Constant.RIGHT_JOIN_AROUND_SPACE, clazz, null, null, onConditions);
    }

    private <L, R> JoinLambdaQueryWrapper<T> join(String joinType, Class<L> clazz, SFunction<L, ?> left, SFunction<R, ?> right, OnCondition... onConditions) {
        Assert.notNull(clazz, "clazz can not be null");

        tableAliasIndexMap.put(clazz, tableAliasIndex);

        StringBuilder joinSqlStr = new StringBuilder(joinType);

        boolean sfIsNull = (left == null || right == null);

        Assert.isFalse((sfIsNull) && ArrayUtils.isEmpty(onConditions),
                "join table condition can not be null");

        if (left == null) {
            left = onConditions[0].getLeft();
        }

        // 拼接被关联表名
        joinSqlStr
                .append(getTableName(left))
                .append(Constant.AS_AROUND_SPACE)
                .append(Constant.TABLE_ALIAS_PREF)
                .append(tableAliasIndex);

        // 拼接 ON 关键字
        joinSqlStr.append(Constant.ON_AROUND_SPACE);

        if (!sfIsNull) {
            // 拼接连表条件
            joinSqlStr
                    .append(addTableNamePrefix(tableAliasIndex, getColumnName(left)))
                    .append(Constant.EQUALS_AROUND_SPACE)
                    .append(addTableNamePrefix(tableAliasIndexMap.get(getClazz(right)), getColumnName(right)));
        }

        if (ArrayUtils.isNotEmpty(onConditions)) {
            // 拼接连表条件
            for (OnCondition onCondition : onConditions) {
                joinSqlStr
                        .append(onCondition.getLogic())
                        .append(addTableNamePrefix(tableAliasIndex, getColumnName(onCondition.getLeft())))
                        .append(onCondition.getOperatorValue());

                if (Constant.CONDITION_TYPE_VARIABLE.equals(onCondition.getType())) {
                    joinSqlStr.append(addTableNamePrefix(tableAliasIndexMap.get(getClazz(onCondition.getRight())), getColumnName(onCondition.getRight())));
                } else if (Constant.CONDITION_TYPE_CONSTANT.equals(onCondition.getType())) {
                    joinSqlStr.append(onCondition.getValue());
                }
            }
        }

        this.joinSql.setStringValue(this.joinSql.getStringValue() + joinSqlStr);

        tableAliasIndex++;

        return typedThis;
    }

    @Override
    public String getSqlSelect() {
        if (StringUtils.isBlank(sqlSelect.getStringValue())) {

            if (CollectionUtils.isNotEmpty(excludeColumns)) {
                selectColumns.removeIf(
                        select -> select.getDbFunction() == null && excludeColumns.stream().anyMatch(
                                exclude -> exclude.getClazz() == select.getClazz() && Objects.equals(select.getColumnName(), exclude.getColumnName())
                        )
                );
            }

            String sqlSelectStr = selectColumns.stream().map(item -> {
                // id => t1.id
                String columnName = addTableNamePrefix(tableAliasIndexMap.get(item.getClazz()), item.getColumnName());

                if (item.getDbFunction() != null) {
                    // t1.id => COUNT(t1.id)
                    columnName = String.format(item.getDbFunction().getSql(), columnName);
                }

                if (StringUtils.isNotBlank(item.getColumnAlias())) {
                    // COUNT(t1.id) => COUNT(t1.id) AS userId
                    columnName += Constant.AS_AROUND_SPACE + item.getColumnAlias();
                }

                return columnName;

            }).collect(Collectors.joining(Constant.COMMA));

            sqlSelect.setStringValue(sqlSelectStr);
        }

        return sqlSelect.getStringValue();
    }

    public String getMasterTableAlias() {
        return masterTableAlias.getStringValue();
    }

    public String getJoinSql() {
        return joinSql.getStringValue();
    }

    public List<SelectColumn> getSelectColumns() {
        return selectColumns;
    }

    public List<SelectColumn> getExcludeColumns() {
        return excludeColumns;
    }

    public boolean isDistinct() {
        return isDistinct;
    }

    /**
     * 用于生成嵌套 sql
     * <p>故 sqlSelect 不向下传递</p>
     */
    @Override
    protected JoinLambdaQueryWrapper<T> instance() {
        return new JoinLambdaQueryWrapper<>(getEntity(), getEntityClass(), null, paramNameSeq, paramNameValuePairs,
                new MergeSegments(), paramAlias, SharedString.emptyString(), SharedString.emptyString(), SharedString.emptyString());
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

    @Override
    protected <E> String columnToString(SFunction<E, ?> column) {
        return addTableNamePrefix(tableAliasIndexMap.get(getClazz(column)), getColumnName(column));
    }

    private static class SelectColumn {
        /**
         * 数据库列列所对应的实体类Class
         */
        private Class<?> clazz;

        /**
         * 数据库列名
         */
        private String columnName;

        /**
         * 数据库列别名
         */
        private String columnAlias;

        /**
         * 数据库函数
         */
        private DbFunction dbFunction;

        public SelectColumn(Class<?> clazz, String columnName, String columnAlias, DbFunction dbFunction) {
            this.clazz = clazz;
            this.columnName = columnName;
            this.columnAlias = columnAlias;
            this.dbFunction = dbFunction;
        }

        public static SelectColumn of(Class<?> clazz, String columnName, String columnAlias, DbFunction dbFunction) {
            return new SelectColumn(clazz, columnName, columnAlias, dbFunction);
        }

        public static SelectColumn of(Class<?> clazz, String columnName, String columnAlias) {
            return new SelectColumn(clazz, columnName, columnAlias, null);
        }

        public static SelectColumn of(Class<?> clazz, String columnName) {
            return new SelectColumn(clazz, columnName, null, null);
        }

        public Class<?> getClazz() {
            return clazz;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getColumnAlias() {
            return columnAlias;
        }

        public DbFunction getDbFunction() {
            return dbFunction;
        }
    }
}
