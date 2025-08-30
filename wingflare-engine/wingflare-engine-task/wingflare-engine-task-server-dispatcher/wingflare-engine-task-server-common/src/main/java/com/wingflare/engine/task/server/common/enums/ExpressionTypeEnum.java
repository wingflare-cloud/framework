package com.wingflare.engine.task.server.common.enums;

import com.wingflare.engine.task.common.core.expression.ExpressionEngine;
import com.wingflare.engine.task.common.core.expression.strategy.AviatorExpressionEngine;
import com.wingflare.engine.task.common.core.expression.strategy.QLExpressEngine;
import com.wingflare.engine.task.common.core.expression.strategy.SpELExpressionEngine;

/**
 * @author xiaowoniu
 * @date 2023-12-30 10:50:05
 * @since 2.6.0
 */
public enum ExpressionTypeEnum {
    SPEL(1, new SpELExpressionEngine()),
    AVIATOR(2, new AviatorExpressionEngine()),
    QL(3, new QLExpressEngine());

    private final Integer type;
    private final ExpressionEngine expressionEngine;

    ExpressionTypeEnum(Integer type, ExpressionEngine expressionEngine) {
        this.type = type;
        this.expressionEngine = expressionEngine;
    }

    public Integer getType() {
        return type;
    }

    public ExpressionEngine getExpressionEngine() {
        return expressionEngine;
    }

    public static ExpressionEngine valueOf(Integer type) {
        for (ExpressionTypeEnum expressionTypeEnum : ExpressionTypeEnum.values()) {
            if (expressionTypeEnum.getType().equals(type)) {
                return expressionTypeEnum.getExpressionEngine();
            }
        }

        return null;
    }

}
