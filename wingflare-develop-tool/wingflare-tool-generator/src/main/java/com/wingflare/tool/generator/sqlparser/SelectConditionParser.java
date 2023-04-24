package com.wingflare.tool.generator.sqlparser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.ParenthesisFromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.TableFunction;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.values.ValuesStatement;

import java.util.List;

/**
 * 解析子查询以及自身的where条件部分
 */
public class SelectConditionParser implements SelectVisitor, FromItemVisitor {

    private WhereParser whereConditionParser = new WhereParser();

    public List<ConditionExpr> getParsedConditions() {
        return whereConditionParser.getConditions();
    }

    @Override
    public void visit(Table tableName) {
    }

    @Override
    public void visit(SubSelect subSelect) {
        subSelect.getSelectBody().accept(this);
    }

    @Override
    public void visit(SubJoin subjoin) {
        subjoin.getLeft().accept(this);
    }

    @Override
    public void visit(LateralSubSelect lateralSubSelect) {
    }

    @Override
    public void visit(ValuesList valuesList) {
    }

    @Override
    public void visit(TableFunction tableFunction) {
    }

    @Override
    public void visit(ParenthesisFromItem aThis) {
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        Expression where = plainSelect.getWhere();
        if (where != null) {
            where.accept(whereConditionParser);
        }
        plainSelect.getFromItem().accept(this);
    }

    @Override
    public void visit(SetOperationList setOpList) {
        for (SelectBody sb : setOpList.getSelects()) {
            sb.accept(this);
        }
    }

    @Override
    public void visit(WithItem withItem) {
    }

    @Override
    public void visit(ValuesStatement aThis) {
    }
}
