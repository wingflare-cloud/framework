package com.wingflare.lib.mybatis.plus.parser;


import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.wingflare.lib.core.utils.ObjectUtil;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.utils.SqlUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;

import java.util.Map;

/**
 * 自定义SQL解析
 *
 * @author shaoyuyao
 * @date 2022/8/18 16:02
 */
public class CustomJsqlParserSupport extends JsqlParserSupport {

    public String parserSingle(String sql, Object obj, Object parameter) {
        Map parameterMap = null;
        if (parameter != null) {
            parameterMap = parameter instanceof Map ? (Map) parameter : ObjectUtil.objectToMap(parameter);
        }

        try {
            if (SqlUtil.isSelect(sql)) {
                // 处理模糊查询特殊表达式
                sql = likeExpressionReplace(sql);
            }
            Statement statement = CCJSqlParserUtil.parse(sql);
            return processParser(statement, 0, sql, obj, parameterMap);
        } catch (JSQLParserException e) {
            throw ExceptionUtils.mpe("Failed to process, Error SQL: %s" , e.getCause(), sql);
        }
    }

    /**
     * 将 "%"?"%", "%"?, ?"%" 进行替换处理
     * 由于 CCJSqlParserUtil.parse 方法解析SQL语句过程中，如果包含 "%"?"%", "%"?, ?"%" 这些字符串会报错，因此这里需要进行替换操作
     *
     * @param sql
     */
    private String likeExpressionReplace(String sql) {
        if (StringUtils.isEmpty(sql)) {
            return sql;
        }

        // "%"?"%" => CONCAT('%',?,'%')
        if (sql.contains(Constant.LIKE_SPLIT_EXP)) {
            sql = sql.replace(Constant.LIKE_SPLIT_EXP, Constant.LIKE_CONCAT_EXP);
        }

        // "%"? => CONCAT('%',?)
        if (sql.contains(Constant.LIKE_LEFT_SPLIT_EXP)) {
            sql = sql.replace(Constant.LIKE_LEFT_SPLIT_EXP, Constant.LIKE_LEFT_CONCAT_EXP);
        }

        // ?"%" => CONCAT(?,'%')
        if (sql.contains(Constant.LIKE_RIGHT_SPLIT_EXP)) {
            sql = sql.replace(Constant.LIKE_RIGHT_SPLIT_EXP, Constant.LIKE_RIGHT_CONCAT_EXP);
        }

        return sql;
    }

    protected String processParser(Statement statement, int index, String sql, Object obj, Map parameter) {
        if (statement instanceof Insert) {
            this.processInsert((Insert) statement, index, sql, obj);
        } else if (statement instanceof Select) {
            this.processSelect((Select) statement, index, sql, obj, parameter);
        } else if (statement instanceof Update) {
            this.processUpdate((Update) statement, index, sql, obj);
        } else if (statement instanceof Delete) {
            this.processDelete((Delete) statement, index, sql, obj);
        }
        sql = statement.toString();

        return sql;
    }

    /**
     * 查询
     */
    protected void processSelect(Select select, int index, String sql, Object obj, Map parameter) {
        throw new UnsupportedOperationException();
    }
}