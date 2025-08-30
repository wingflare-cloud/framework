package com.wingflare.engine.task.server.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author opensnail
 * @date 2024-07-01 21:53:45
 * @since sj_1.1.0
 */
public class CheckDecisionVO {

    /**
     * 表达式类型 1、SpEl、2、Aviator 3、QL
     */
    @NotNull
    private Integer expressionType;

    /**
     * 条件节点表达式
     */
    @NotBlank
    private String nodeExpression;

    /**
     * 决策节点校验内容
     */
    private String checkContent;

    public Integer getExpressionType() {
        return expressionType;
    }

    public void setExpressionType(Integer expressionType) {
        this.expressionType = expressionType;
    }

    public String getNodeExpression() {
        return nodeExpression;
    }

    public void setNodeExpression(String nodeExpression) {
        this.nodeExpression = nodeExpression;
    }

    public String getCheckContent() {
        return checkContent;
    }

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }
}
