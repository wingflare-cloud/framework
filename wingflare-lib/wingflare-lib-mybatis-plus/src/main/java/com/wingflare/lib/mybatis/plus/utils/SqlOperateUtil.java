package com.wingflare.lib.mybatis.plus.utils;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.statement.update.UpdateSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * SQL操作工具类
 *
 * @author shaoyuyao
 * @date 2022/8/19 9:38
 */
public class SqlOperateUtil {
    /**
     * 添加表名前缀，例如 "student_name" => "student.student_name"
     *
     * @param tableAlias 表别名
     * @param columns    要查询的列
     * @return
     */
    public static String[] addTableNamePrefixBatch(String tableAlias, String... columns) {
        String[] processColumns = new String[columns.length];
        for (int i = 0; i < columns.length; i++) {
            processColumns[i] = tableAlias + Constant.DOT + columns[i];
        }
        return processColumns;
    }

    /**
     * 添加表名前缀，例如 "student_name" => "student.student_name"
     *
     * @param tableAlias 表别名
     * @param columns    要查询的列
     * @return
     */
    public static List<String> addTableNamePrefixBatch(String tableAlias, List<String> columns) {
        List<String> processColumns = new ArrayList<>(columns.size());
        for (String column : columns) {
            processColumns.add(addTableNamePrefix(tableAlias, column));
        }
        return processColumns;
    }

    /**
     * 添加表名前缀，例如 "student_name" => "student.student_name"
     *
     * @param tableAlias 表别名
     * @param column     要查询的列
     * @return
     */
    public static String addTableNamePrefix(String tableAlias, String column) {
        if (StringUtils.isBlank(tableAlias)) {
            return column;
        }
        return tableAlias + Constant.DOT + column;
    }

    /**
     * 修改更新SQL语句，移除不需要更新列，只更新配置列表包含的字段
     *
     * @param updateSql         更新SQL语句
     * @param needUpdateColumns 需要更新的列，变量名和字段名都支持
     * @return 处理之后的更新SQL语句
     */
    public static String removeUpdateColumn(String updateSql, Set<String> needUpdateColumns) {
        if (StringUtils.isEmpty(updateSql) || CollectionUtil.isEmpty(needUpdateColumns)) {
            return updateSql;
        }

        try {
            Statement statement = CCJSqlParserUtil.parse(updateSql);
            if (statement == null) {
                return updateSql;
            }

            if (statement instanceof Update) {
                Update update = (Update) statement;
                ArrayList<UpdateSet> updateSets = update.getUpdateSets();
                if (CollectionUtil.isEmpty(updateSets)) {
                    return updateSql;
                }

                List<UpdateSet> removeUpdateSets = new ArrayList<>();
                for (UpdateSet updateSet : updateSets) {
                    ArrayList<Column> columns = updateSet.getColumns();
                    if (CollectionUtil.isEmpty(columns) || columns.get(0) == null) {
                        continue;
                    }

                    String columnName = columns.get(0).getColumnName();
                    String fieldName = underlineToSmallHump(columnName);

                    if (!needUpdateColumns.contains(columnName) && !needUpdateColumns.contains(fieldName)) {
                        removeUpdateSets.add(updateSet);
                    }
                }

                updateSets.removeAll(removeUpdateSets);

                if (updateSets.size() > 0) {
                    return update.toString();
                }
            }
        } catch (JSQLParserException e) {
            throw new RuntimeException(e);
        }

        return updateSql;
    }

    /**
     * 驼峰转下划线
     */
    public static String humpToUnderline(String humpStr) {
        if (StringUtils.isEmpty(humpStr)) {
            return humpStr;
        }

        Matcher matcher = Constant.HUMP_PATTERN.matcher(humpStr);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, Constant.UNDERLINE + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);

        if (Constant.UNDERLINE_CHAR == sb.charAt(0)) {
            return sb.substring(1);
        }

        return sb.toString();
    }

    /**
     * 下划线转小驼峰
     */
    public static String underlineToSmallHump(String underlineStr) {
        return underlineToHump(underlineStr, true);
    }

    /**
     * 下划线转大驼峰
     */
    public static String underlineToBigHump(String underlineStr) {
        return underlineToHump(underlineStr, false);
    }

    /**
     * 下划线转驼峰
     *
     * @param isToSmallHump 是否转小驼峰，true：下划线转小驼峰；false：下划线转大驼峰
     */
    public static String underlineToHump(String underlineStr, boolean isToSmallHump) {
        if (StringUtils.isEmpty(underlineStr)) {
            return underlineStr;
        }

        Matcher matcher = Constant.UNDERLINE_PATTERN.matcher(underlineStr);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        if (isToSmallHump) {
            return String.valueOf(sb.charAt(0)).toLowerCase() + sb.substring(1);
        }

        return sb.toString();
    }
}
