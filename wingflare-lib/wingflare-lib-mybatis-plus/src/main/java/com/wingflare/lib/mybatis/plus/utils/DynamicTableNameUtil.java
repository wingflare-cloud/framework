package com.wingflare.lib.mybatis.plus.utils;


import com.wingflare.api.core.Ctx;
import com.wingflare.lib.mybatis.plus.bo.DynamicTableNameBuffer;
import com.wingflare.lib.mybatis.plus.bo.DynamicTableNameData;
import com.wingflare.lib.mybatis.plus.bo.TablesNamesFactory;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.StringUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.logstash.logback.argument.StructuredArguments.e;

/**
 * 动态表名工具类
 *
 * @author shaoyuyao
 * @date 2023/3/10 18:41
 */
public class DynamicTableNameUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicTableNameUtil.class);

    /**
     * 添加替换表名配置
     *
     * @param beforeTableName 替换前的表名
     * @param afterTableName  替换后的表名
     */
    public static void addConfig(String beforeTableName, String afterTableName) {
        addConfig(DynamicTableNameBuffer.build(beforeTableName, afterTableName));
    }

    /**
     * 添加替换表名配置
     *
     * @param buffer 动态表名存储器
     */
    public static void addConfig(DynamicTableNameBuffer buffer) {
        ContextHolder.set(Ctx.DYNAMIC_TABLE_NAME_CONTEXT, buffer);
    }

    /**
     * 替换表名
     *
     * @param sql    替换后的SQL
     * @param buffer 动态表名存储器
     */
    public static String replaceTableName(String sql, DynamicTableNameBuffer buffer) {
        if (null == buffer) {
            return sql;
        }

        try {
            Statement statement = CCJSqlParserUtil.parse(sql);
            statement.accept(new TablesNamesFactory(buffer.getMap()));
            return statement.toString();
        } catch (Exception e) {
            LOGGER.error("替换表名失败", e(Map.of(
                    "sql", sql,
                    "buffer", buffer,
                    "exception", e
            )));
            return sql;
        }
    }

    /**
     * 替换Select部分中的表名
     *
     * @param item
     * @param map
     */
    public static void replaceSelectTableName(SelectItem item, Map<String, DynamicTableNameData> map) {
        if (item instanceof SelectExpressionItem) {
            SelectExpressionItem selectExpressionItem = (SelectExpressionItem) item;
            Expression expression = selectExpressionItem.getExpression();

            replaceColumnTableName(expression, map);
        }
    }

    /**
     * 替换From部分中的表名
     *
     * @param fromItem
     * @param map
     */
    public static void replaceFromTableName(FromItem fromItem, Map<String, DynamicTableNameData> map) {
        if (fromItem instanceof Table) {
            Table table = (Table) fromItem;
            String beforeTableName = table.getName();

            DynamicTableNameData data = getDynamicTableNameData(beforeTableName, map);
            if (null == data) {
                return;
            }

            // 替换表名
            String afterTableName = data.getAfterTableName();
            if (StringUtil.isNotEmpty(afterTableName)) {
                afterTableName = backQuoteAround(afterTableName);
                table.setName(afterTableName);
            }
        }
    }

    /**
     * 替换Expression部分的表名
     *
     * @param expression
     * @param map
     */
    public static void replaceExpressionTableName(Expression expression, Map<String, DynamicTableNameData> map) {
        if (expression instanceof EqualsTo) {
            EqualsTo equalsTo = (EqualsTo) expression;
            Expression leftExpression = equalsTo.getLeftExpression();
            Expression rightExpression = equalsTo.getRightExpression();

            replaceColumnTableName(leftExpression, map);
            replaceColumnTableName(rightExpression, map);
        } else if (expression instanceof AndExpression) {
            AndExpression andExpression = (AndExpression) expression;
            Expression leftExpression = andExpression.getLeftExpression();
            Expression rightExpression = andExpression.getRightExpression();

            replaceExpressionTableName(leftExpression, map);
            replaceExpressionTableName(rightExpression, map);
        } else if (expression instanceof GreaterThan) {
            GreaterThan greaterThan = (GreaterThan) expression;
            Expression leftExpression = greaterThan.getLeftExpression();
            Expression rightExpression = greaterThan.getRightExpression();

            replaceColumnTableName(leftExpression, map);
            replaceColumnTableName(rightExpression, map);
        }
    }

    /**
     * 替换Column部分的表名
     *
     * @param expression
     * @param map
     */
    private static void replaceColumnTableName(Expression expression, Map<String, DynamicTableNameData> map) {
        if (expression instanceof Column) {
            Column column = (Column) expression;
            Table table = column.getTable();

            if (null != table) {
                DynamicTableNameData data = DynamicTableNameUtil.getDynamicTableNameData(table.getName(), map);

                if (null != data) {
                    String afterTableName = data.getAfterTableName();
                    afterTableName = backQuoteAround(afterTableName);
                    table.setName(afterTableName);
                }

            }

        }
    }

    /**
     * 根据替换前的表名获取动态表名数据
     *
     * @param beforeTableName
     * @param map
     * @return
     */
    private static DynamicTableNameData getDynamicTableNameData(
            String beforeTableName, Map<String, DynamicTableNameData> map) {
        DynamicTableNameData data = map.get(beforeTableName);
        if (null == data) {
            // 去除反单引号，`tableName` -> tableName
            beforeTableName = beforeTableName.replace("`", "");
            data = map.get(beforeTableName);
        }

        return data;
    }

    /**
     * 添加反单引号，反单引号包围，student -> `student`
     *
     * @param str
     * @return
     */
    private static String backQuoteAround(String str) {
        if (null != str && str.startsWith("`") && str.endsWith("`")) {
            return str;
        }

        return "`" + str + "`";
    }

}
