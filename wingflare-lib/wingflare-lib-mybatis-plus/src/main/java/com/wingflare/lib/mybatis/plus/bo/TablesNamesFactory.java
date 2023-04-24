package com.wingflare.lib.mybatis.plus.bo;

import com.wingflare.lib.mybatis.plus.utils.DynamicTableNameUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.Map;

/**
 * 动态表名工厂
 *
 * @author shaoyuyao
 * @date 2023/3/10 19:18
 */
public class TablesNamesFactory extends TablesNamesFinder {
    /**
     * Key为替换前的表名，Value为动态表名数据
     */
    private Map<String, DynamicTableNameData> replaceTableNameMap;

    public TablesNamesFactory(Map<String, DynamicTableNameData> replaceTableNameMap) {
        this.init(false);
        this.replaceTableNameMap = replaceTableNameMap;
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        if (plainSelect.getSelectItems() != null) {
            for (SelectItem item : plainSelect.getSelectItems()) {
                item.accept(this);
                // 替换表名
                DynamicTableNameUtil.replaceSelectTableName(item, replaceTableNameMap);
            }
        }

        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem != null) {
            fromItem.accept(this);

            // 替换表名
            DynamicTableNameUtil.replaceFromTableName(fromItem, replaceTableNameMap);
        }

        if (plainSelect.getJoins() != null) {
            for (Join join : plainSelect.getJoins()) {
                // 替换表名
                join.getOnExpressions().forEach(
                        x -> DynamicTableNameUtil.replaceExpressionTableName(x, replaceTableNameMap)
                );

                FromItem rightItem = join.getRightItem();
                rightItem.accept(this);

                // 替换表名
                DynamicTableNameUtil.replaceFromTableName(rightItem, replaceTableNameMap);
            }
        }

        Expression where = plainSelect.getWhere();
        if (where != null) {
            where.accept(this);

            // 替换表名
            DynamicTableNameUtil.replaceExpressionTableName(where, replaceTableNameMap);
        }

        if (plainSelect.getHaving() != null) {
            plainSelect.getHaving().accept(this);
        }

        if (plainSelect.getOracleHierarchical() != null) {
            plainSelect.getOracleHierarchical().accept(this);
        }
    }
}
