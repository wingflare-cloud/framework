/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.wingflare.lib.datascope.parser.expression;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;

/**
 * 自定义And表达式
 *
 * @author shaoyuyao
 * @date 2022/8/29 11:27
 */
public class IAndExpression extends AndExpression {
    /**
     * 是否用括号包裹起来
     */
    private boolean isBracketed;

    public IAndExpression(Expression leftExpression, Expression rightExpression) {
        super(leftExpression, rightExpression);
        this.isBracketed = true;
    }

    public IAndExpression(Expression leftExpression, Expression rightExpression, boolean isBracketed) {
        super(leftExpression, rightExpression);
        this.isBracketed = isBracketed;
    }

    @Override
    public String toString() {
        if (isBracketed) {
            return "(" + super.toString() + ")";
        }
        return super.toString();
    }
}
