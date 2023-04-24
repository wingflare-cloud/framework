package com.wingflare.lib.mybatis.plus.utils;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.enums.LogicOperator;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * MyBatis插件工具类
 *
 * @author shaoyuyao
 * @date 2022/8/18 20:21
 */
public class SqlUtil {
    /**
     * 判断SQL是否是查询操作
     */
    public static boolean isSelect(String sql) {
        if (StringUtils.isNotBlank(sql)) {
            return sql.toUpperCase(Locale.ENGLISH)
                    .startsWith(Constant.SELECT);
        }
        return false;
    }

    /**
     * 判断SQL是否是查询操作
     */
    public static boolean isSelect(MappedStatement mappedStatement) {
        return SqlCommandType.SELECT == mappedStatement.getSqlCommandType();
    }

    /**
     * 判断SQL是否是修改操作
     */
    public static boolean isUpdate(MappedStatement mappedStatement) {
        return SqlCommandType.UPDATE == mappedStatement.getSqlCommandType();
    }

    /**
     * 判断SQL是否是删除操作
     * @param mappedStatement
     */
    public static boolean isDelete(MappedStatement mappedStatement) {
        return SqlCommandType.DELETE == mappedStatement.getSqlCommandType();
    }

    /**
     * 判断SQL是否为数据修改操作
     * @param mappedStatement
     */
    public static boolean isModify(MappedStatement mappedStatement) {
        return isDelete(mappedStatement) || isUpdate(mappedStatement);
    }

    /**
     * 获取MappedStatement对象
     *
     * @param statementHandler
     * @return
     */
    public static MappedStatement getMappedStatement(StatementHandler statementHandler) {
        MetaObject metaObject = MetaObject.forObject(
                statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory()
        );

        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement" );

        return mappedStatement;
    }

    /**
     * 替换BoundSql对象中的SQL
     *
     * @param boundSql BoundSql对象
     * @param newSql   新的SQL
     */
    public static void replaceSql(BoundSql boundSql, String newSql) {
        try {
            Field field = boundSql.getClass().getDeclaredField("sql" );
            field.setAccessible(true);
            field.set(boundSql, newSql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 拼接Where表达式
     *
     * @param left  左边的Where表达式
     * @param right 右边的Where表达式
     * @param logic 逻辑运算符
     * @return
     */
    public static Expression spliceWhereExpression(Expression left, Expression right, LogicOperator logic) {
        if (null == left) {
            return right;
        }

        if (null == right) {
            return left;
        }

        if (LogicOperator.AND.equals(logic)) {
            return new AndExpression(left, right);
        } else {
            return new OrExpression(left, right);
        }
    }
}