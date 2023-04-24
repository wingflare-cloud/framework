package com.wingflare.lib.mybatis.plus.wrapper;


import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import org.apache.ibatis.reflection.property.PropertyNamer;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽象连表查询封装对象
 *
 * @author shaoyuyao
 * @date 2022/8/23 16:26
 */
public abstract class AbstractJoinLambdaWrapper<T, Children extends AbstractJoinLambdaWrapper<T, Children>>
        extends AbstractJoinWrapper<T, SFunction<T, ?>, Children> {

    /**
     * 表别名标记集合
     */
    protected final Map<Class<?>, Integer> tableAliasIndexMap = new HashMap<>();

    /**
     * 数据库字段缓存
     */
    private Map<Class<?>, Map<String, ColumnCache>> columnMap = new HashMap<>();

    /**
     * 获取Class
     *
     * @param column
     * @return
     */
    protected Class<?> getClazz(SFunction<?, ?> column) {
        LambdaMeta lambdaMeta = LambdaUtils.extract(column);
        Class<?> clazz = lambdaMeta.getInstantiatedClass();
        return clazz;
    }

    /**
     * 获取属性名
     *
     * @param column
     * @return
     */
    protected String getPropertyName(SFunction<?, ?> column) {
        LambdaMeta lambdaMeta = LambdaUtils.extract(column);
        return PropertyNamer.methodToProperty(lambdaMeta.getImplMethodName());
    }

    /**
     * 获取表名
     *
     * @param column
     * @return
     */
    protected String getTableName(SFunction<?, ?> column) {
        Class<?> clazz = getClazz(column);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        return tableInfo.getTableName();
    }

    /**
     * 获取列名
     *
     * @param column
     * @return
     */
    protected String getColumnName(SFunction<?, ?> column) {
        ColumnCache cache = getColumnCache(column);
        return cache.getColumn();
    }

    /**
     * 获取列缓存
     *
     * @param column
     * @return
     */
    protected ColumnCache getColumnCache(SFunction<?, ?> column) {
        LambdaMeta lambdaMeta = LambdaUtils.extract(column);
        Class<?> clazz = lambdaMeta.getInstantiatedClass();
        Map<String, ColumnCache> columnCacheMap = columnMap.get(clazz);
        if (columnCacheMap == null) {
            columnCacheMap = LambdaUtils.getColumnMap(clazz);
            columnMap.put(clazz, columnCacheMap);
        }
        String propertyName = PropertyNamer.methodToProperty(lambdaMeta.getImplMethodName());
        return columnCacheMap.get(LambdaUtils.formatKey(propertyName));
    }

    /**
     * 添加表名前缀
     *
     * @param tableAliasIndex 表别名标记
     * @param columnName      列名
     * @return
     */
    protected String addTableNamePrefix(Integer tableAliasIndex, String columnName) {
        if (tableAliasIndex == null) {
            return Constant.MASTER_TABLE_ALIAS_PREF + Constant.DOT + columnName;
        }
        return Constant.TABLE_ALIAS_PREF + tableAliasIndex + Constant.DOT + columnName;
    }
}
