package com.wingflare.lib.mybatis.plus.plugin.inner;


import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.mybatis.plus.base.BaseDoAbstract;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.parser.CustomJsqlParserSupport;
import com.wingflare.lib.mybatis.plus.plugin.handler.SelectHandlerInterface;
import com.wingflare.lib.mybatis.plus.plugin.handler.SelectWhereHandlerInterface;
import com.wingflare.lib.mybatis.plus.plugin.handler.SqlBeforePrepareInterface;
import com.wingflare.lib.mybatis.plus.utils.SqlUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 自定义拦截器
 *
 * @author shaoyuyao
 * @date 2022/8/17 14:48
 */
public class CustomInterceptor extends CustomJsqlParserSupport implements InnerInterceptor {

    /**
     * 查询语句处理器
     */
    private Collection<SelectWhereHandlerInterface> selectWhereHandlerInterfaces;

    /**
     * 查询语句处理器
     */
    private Collection<SelectHandlerInterface> selectHandlerInterfaces;

    /**
     * sql执行前置处理器
     */
    private Collection<SqlBeforePrepareInterface> sqlBeforePrepareInterfaces;


    public CustomInterceptor(
            Collection<SelectWhereHandlerInterface> selectWhereHandlerInterfaces,
            Collection<SelectHandlerInterface> selectHandlerInterfaces,
            Collection<SqlBeforePrepareInterface> sqlBeforePrepareInterfaces
    ) {
        setSelectWhereHandlerInterfaces(selectWhereHandlerInterfaces);
        setSelectHandlerInterfaces(selectHandlerInterfaces);
        setSqlBeforePrepareInterfaces(sqlBeforePrepareInterfaces);
    }

    /**
     * 【查询】前置处理
     */
    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter,
                            RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        if (InterceptorIgnoreHelper.willIgnoreDataPermission(ms.getId())) {
            return;
        }
        PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        mpBs.sql(parserSingle(mpBs.sql(), ms.getId(), parameter));

        if (CollectionUtil.isNotEmpty(getSelectHandlerInterfaces())) {
            for (SelectHandlerInterface handler : getSelectHandlerInterfaces()) {
                handler.beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
                mpBs.sql(handler.sql(mpBs.sql(), parameter));
            }
        }
    }

    @Override
    protected void processSelect(Select select, int index, String sql, Object obj, Map parameter) {
        SelectBody selectBody = select.getSelectBody();
        if (selectBody instanceof PlainSelect) {
            this.setWhere((PlainSelect) selectBody, (String) obj, parameter);
        } else if (selectBody instanceof SetOperationList) {
            SetOperationList setOperationList = (SetOperationList) selectBody;
            List<SelectBody> selectBodyList = setOperationList.getSelects();
            selectBodyList.forEach(s -> this.setWhere((PlainSelect) s, (String) obj, parameter));
        }
    }

    protected void setWhere(PlainSelect plainSelect, String mappedStatementId, Map parameter) {
        if (CollectionUtil.isNotEmpty(getSelectWhereHandlerInterfaces())) {
            Expression whereExp = plainSelect.getWhere();

            for (SelectWhereHandlerInterface handler : getSelectWhereHandlerInterfaces()) {
                Expression expression = handler.setWhere(whereExp, mappedStatementId, parameter);
                whereExp = SqlUtil.spliceWhereExpression(whereExp, expression, handler.logic());
            }

            if (whereExp != null) {
                plainSelect.setWhere(whereExp);
            }
        }
    }

    /**
     * 决定是否要执行更新操作
     *
     * @param executor  Executor(可能是代理对象)
     * @param ms        MappedStatement
     * @param parameter parameter
     * @return
     * @throws SQLException
     */
    @Override
    public boolean willDoUpdate(Executor executor, MappedStatement ms, Object parameter) throws SQLException {
        if (parameter == null) {
            return true;
        }

        Map parameterMap = parameter instanceof Map ? (Map) parameter : ObjectUtil.objectToMap(parameter);

        if (!parameterMap.containsKey(Constant.ENTITY)) {
            return true;
        }

        Object entity = parameterMap.get(Constant.ENTITY);
        if (!(entity instanceof BaseDoAbstract)) {
            return true;
        }

        BaseDoAbstract baseDoAbstract = (BaseDoAbstract) entity;

        if (!baseDoAbstract.hasNewField()) {
            return false;
        }

        return true;
    }

    /**
     * 【增删改查】操作前置处理，这里用来处理【增删改】操作，由于beforeUpdate方法修改SQL无法生效，因此采用beforePrepare方法
     */
    @Override
    public void beforePrepare(StatementHandler statementHandler, Connection connection, Integer transactionTimeout) {
        if (CollectionUtil.isNotEmpty(getSqlBeforePrepareInterfaces())) {
            return;
        }

        MappedStatement mappedStatement = SqlUtil.getMappedStatement(statementHandler);

        for (SqlBeforePrepareInterface handler : getSqlBeforePrepareInterfaces()) {
            handler.handle(statementHandler, mappedStatement, connection, transactionTimeout);
        }
    }

    public Collection<SelectWhereHandlerInterface> getSelectWhereHandlerInterfaces() {
        return selectWhereHandlerInterfaces;
    }

    public void setSelectWhereHandlerInterfaces(Collection<SelectWhereHandlerInterface> selectWhereHandlerInterfaces) {
        this.selectWhereHandlerInterfaces = selectWhereHandlerInterfaces;
    }

    public Collection<SelectHandlerInterface> getSelectHandlerInterfaces() {
        return selectHandlerInterfaces;
    }

    public void setSelectHandlerInterfaces(Collection<SelectHandlerInterface> selectHandlerInterfaces) {
        this.selectHandlerInterfaces = selectHandlerInterfaces;
    }

    public Collection<SqlBeforePrepareInterface> getSqlBeforePrepareInterfaces() {
        return sqlBeforePrepareInterfaces;
    }

    public void setSqlBeforePrepareInterfaces(Collection<SqlBeforePrepareInterface> sqlBeforePrepareInterfaces) {
        this.sqlBeforePrepareInterfaces = sqlBeforePrepareInterfaces;
    }
}
