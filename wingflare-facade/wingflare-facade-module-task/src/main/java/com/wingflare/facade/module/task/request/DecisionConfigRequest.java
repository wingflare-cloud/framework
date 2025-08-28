package com.wingflare.facade.module.task.request;


/**
 * 决策节点配置
 *
 * @author xiaowoniu
 * @date 2023-12-30 11:17:30
 * @since 2.6.0
 */
public class DecisionConfigRequest {

    /**
     * 表达式类型 1、SpEl、2、Aviator 3、QL
     */
    private Integer expressionType;

    /**
     * 条件节点表达式
     */
    private String nodeExpression;

    /**
     * 是否为其他情况
     */
    private Integer defaultDecision;


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

    public Integer getDefaultDecision() {
        return defaultDecision;
    }

    public void setDefaultDecision(Integer defaultDecision) {
        this.defaultDecision = defaultDecision;
    }
}
