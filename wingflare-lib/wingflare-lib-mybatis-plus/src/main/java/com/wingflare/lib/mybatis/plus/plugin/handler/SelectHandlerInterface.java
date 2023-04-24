package com.wingflare.lib.mybatis.plus.plugin.handler;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 查询语句处理器
 *
 * @author shaoyuyao
 * @date 2023/3/10
 */
public interface SelectHandlerInterface {
    /**
     * {@link Executor#query(MappedStatement, Object, RowBounds, ResultHandler, CacheKey, BoundSql)} 操作前置处理
     * <p>
     * 改改sql啥的
     *
     * @param executor      Executor(可能是代理对象)
     * @param ms            MappedStatement
     * @param parameter     parameter
     * @param rowBounds     rowBounds
     * @param resultHandler resultHandler
     * @param boundSql      boundSql
     */
    default void beforeQuery(Executor executor, MappedStatement ms,
                             Object parameter, RowBounds rowBounds,
                             ResultHandler resultHandler, BoundSql boundSql) {

    }

    /**
     * 修改SQL
     *
     * @param sql       修改前的SQL
     * @param parameter 方法参数
     * @return
     */
    default String sql(String sql, Object parameter) {
        return sql;
    }

}
