package com.wingflare.lib.mybatis.plus.injector;


import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.wingflare.lib.mybatis.plus.constants.Constant;

/**
 * 自定义抽象的注入方法类
 *
 * @author shaoyuyao
 * @date 2022/8/18 11:26
 */
public abstract class CustomAbstractMethod extends AbstractMethod {

    public CustomAbstractMethod(String methodName) {
        super(methodName);
    }

    /**
     * EntityWrapper方式获取select where
     *
     * @param newLine 是否提到下一行
     * @param table   表信息
     * @return String
     */
    @Override
    protected String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
        String sqlScript = table.getAllSqlWhere(false, true, WRAPPER_ENTITY_DOT);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
        sqlScript += NEWLINE;
        sqlScript += SqlScriptUtils.convertIf(String.format(SqlScriptUtils
                .convertIf(" AND", String
                        .format("%s and %s", WRAPPER_NONEMPTYOFENTITY, WRAPPER_NONEMPTYOFNORMAL), false)
                + " ${%s}", WRAPPER_SQLSEGMENT), String.format("%s != null and %s != '' and %s",
                WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT, WRAPPER_NONEMPTYOFWHERE), true);
        sqlScript = SqlScriptUtils.convertWhere(sqlScript) + NEWLINE;
        sqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT),
                String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT, WRAPPER_EMPTYOFWHERE),
                true);
        sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER), true);
        return newLine ? NEWLINE + sqlScript : sqlScript;
    }

    @Override
    protected String sqlWhereByMap(TableInfo table) {
        String sqlScript = SqlScriptUtils
                .convertChoose("v == null", " ${k} IS NULL ", " ${k} = #{v} ");
        sqlScript = SqlScriptUtils.convertForeach(sqlScript, COLUMN_MAP, "k", "v", "AND");
        sqlScript = SqlScriptUtils.convertWhere(sqlScript);
        sqlScript = SqlScriptUtils.convertIf(sqlScript,
                String.format("%s != null and !%s", COLUMN_MAP, COLUMN_MAP_IS_EMPTY), true);
        return sqlScript;
    }

    protected String sqlDistinct() {
        return SqlScriptUtils.convertIf(
                Constant.DISTINCT,
                String.format("%s != null and %s != null and %s", WRAPPER,
                        Constant.Q_WRAPPER_IS_DISTINCT, Constant.Q_WRAPPER_IS_DISTINCT),
                false
        );
    }

    /**
     * 主表别名
     *
     * @return
     */
    protected String sqlMasterTableAlias() {
        return SqlScriptUtils.convertChoose(
                String.format("%s != null and %s != null and %s != ''",
                        WRAPPER,
                        Constant.Q_WRAPPER_MASTER_TABLE_ALIAS,
                        Constant.Q_WRAPPER_MASTER_TABLE_ALIAS
                ),
                Constant.AS_AROUND_SPACE + "${ew.masterTableAlias}",
                Constant.MASTER_TABLE_ALIAS_PREF
        );
    }

    /**
     * 连表SQL，例如 LEFT JOIN `student` ON `student`.student_id = `teacher`.student_id
     *
     * @return
     */
    protected String sqlJoin() {
        return SqlScriptUtils.convertIf(
                "${ew.joinSql}",
                String.format("%s != null and %s != null and %s != ''",
                        WRAPPER,
                        Constant.Q_WRAPPER_MASTER_JOIN_SQL,
                        Constant.Q_WRAPPER_MASTER_JOIN_SQL
                ),
                false
        );
    }

    protected String convertChooseEwSelect(final String otherwise) {
        return SqlScriptUtils.convertChoose(String.format("%s != null and %s != null and %s != ''",
                        WRAPPER, Q_WRAPPER_SQL_SELECT, Q_WRAPPER_SQL_SELECT),
                SqlScriptUtils.unSafeParam(Q_WRAPPER_SQL_SELECT), otherwise);
    }
}
