package com.wingflare.lib.mybatis.plus.plugin.handler;


import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.utils.SqlUtil;
import com.wingflare.lib.mybatis.plus.utils.SqlOperateUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Map;

/**
 * DML操作处理器
 *
 * @author shaoyuyao
 * @date 2022/8/18 18:31
 */
@Component
public class DmlHandler implements SqlBeforePrepareInterface {
    /**
     * 修改操作处理
     */
    public void doUpdateHandler(BoundSql boundSql) {
        String sql = boundSql.getSql();
        Object parameterObject = boundSql.getParameterObject();

        if (parameterObject == null) {
            return;
        }

        Map parameter = parameterObject instanceof Map ? (Map) parameterObject : ObjectUtil.objectToMap(parameterObject);

        if (!parameter.containsKey(Constant.ENTITY)) {
            return;
        }

        Object entity = parameter.get(Constant.ENTITY);
        if (!(entity instanceof BaseDoAbstract)) {
            return;
        }

        BaseDoAbstract baseDoAbstract = (BaseDoAbstract) entity;

        String newSql = SqlOperateUtil.removeUpdateColumn(sql, baseDoAbstract.getNewField());

        SqlUtil.replaceSql(boundSql, newSql);
    }

    @Override
    public void handle(StatementHandler statementHandler, MappedStatement mappedStatement, Connection connection, Integer transactionTimeout) {
        if (SqlUtil.isUpdate(mappedStatement)) {
            BoundSql boundSql = statementHandler.getBoundSql();
            doUpdateHandler(boundSql);
        }
    }

}
