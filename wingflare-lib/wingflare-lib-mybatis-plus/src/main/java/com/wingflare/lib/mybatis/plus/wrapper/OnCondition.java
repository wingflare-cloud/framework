package com.wingflare.lib.mybatis.plus.wrapper;


import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.enums.Operator;

/**
 * @author shaoyuyao
 * @date 2022/8/24 14:28
 */
public class OnCondition {
    private SFunction left;

    private SFunction right;

    /**
     * AND / OR
     */
    private String logic;

    /**
     * 操作符，例如：=、!=、>...
     */
    private Operator operator;

    /**
     * 条件比较类型（VARIABLE：变量比较；CONSTANT：常量比较）
     */
    private String type;

    /**
     * 常量值
     */
    private Object value;

    public OnCondition(SFunction left, SFunction right, String logic, Operator operator, String type, Object value) {
        this.left = left;
        this.right = right;
        this.logic = logic;
        this.operator = operator;
        this.type = type;
        this.value = value;
    }

    public OnCondition(SFunction left, SFunction right, String logic, Operator operator, String type) {
        this.left = left;
        this.right = right;
        this.logic = logic;
        this.operator = operator;
        this.type = type;
    }

    public static <L, R> OnCondition of(SFunction<L, ?> left, SFunction<R, ?> right) {
        return new OnCondition(left, right, Constant.SPACE, Operator.EQ, Constant.CONDITION_TYPE_VARIABLE);
    }

    public static <L, R> OnCondition and(SFunction<L, ?> left, SFunction<R, ?> right, Operator operator, String type, Object value) {
        return new OnCondition(left, right, Constant.AND_AROUND_SPACE, operator, type, value);
    }

    public static <L, R> OnCondition and(SFunction<L, ?> left, SFunction<R, ?> right) {
        return and(left, right, Operator.EQ, Constant.CONDITION_TYPE_VARIABLE, null);
    }

    public static <L, R> OnCondition and(SFunction<L, ?> left, Operator operator, Object value) {
        return and(left, null, operator, Constant.CONDITION_TYPE_CONSTANT, value);
    }

    public static <L, R> OnCondition andEq(SFunction<L, ?> left, Object value) {
        return and(left, null, Operator.EQ, Constant.CONDITION_TYPE_CONSTANT, value);
    }

    public static <L, R> OnCondition andBetween(SFunction<L, ?> left, Object startValue, Object endValue) {
        return and(left, null, Operator.BETWEEN, Constant.CONDITION_TYPE_CONSTANT, startValue + Operator.AND.getValue() + endValue);
    }

    public static <L, R> OnCondition andNotBetween(SFunction<L, ?> left, Object startValue, Object endValue) {
        return and(left, null, Operator.NOT_BETWEEN, Constant.CONDITION_TYPE_CONSTANT, startValue + Operator.AND.getValue() + endValue);
    }

    public static <L, R> OnCondition andLike(SFunction<L, ?> left, Object value) {
        return and(left, null, Operator.LIKE, Constant.PERCENT + Constant.CONDITION_TYPE_CONSTANT + Constant.PERCENT, value);
    }

    public static <L, R> OnCondition andLikeLeft(SFunction<L, ?> left, Object value) {
        return and(left, null, Operator.LIKE, Constant.PERCENT + Constant.CONDITION_TYPE_CONSTANT, value);
    }

    public static <L, R> OnCondition andLikeRight(SFunction<L, ?> left, Object value) {
        return and(left, null, Operator.LIKE, Constant.CONDITION_TYPE_CONSTANT + Constant.PERCENT, value);
    }

    public static <L, R> OnCondition or(SFunction<L, ?> left, SFunction<R, ?> right, Operator operator, String type, Object value) {
        return new OnCondition(left, right, Constant.OR_AROUND_SPACE, operator, type, value);
    }

    public static <L, R> OnCondition or(SFunction<L, ?> left, SFunction<R, ?> right) {
        return or(left, right, Operator.EQ, Constant.CONDITION_TYPE_VARIABLE, null);
    }

    public static <L, R> OnCondition or(SFunction<L, ?> left, Operator operator, Object value) {
        return or(left, null, operator, Constant.CONDITION_TYPE_CONSTANT, value);
    }

    public static <L, R> OnCondition orEq(SFunction<L, ?> left, Object value) {
        return or(left, null, Operator.EQ, Constant.CONDITION_TYPE_CONSTANT, value);
    }

    public static <L, R> OnCondition orLike(SFunction<L, ?> left, Object value) {
        return or(left, null, Operator.LIKE, Constant.PERCENT + Constant.CONDITION_TYPE_CONSTANT + Constant.PERCENT, value);
    }

    public static <L, R> OnCondition orLikeLeft(SFunction<L, ?> left, Object value) {
        return or(left, null, Operator.LIKE, Constant.PERCENT + Constant.CONDITION_TYPE_CONSTANT, value);
    }

    public static <L, R> OnCondition orLikeRight(SFunction<L, ?> left, Object value) {
        return or(left, null, Operator.LIKE, Constant.CONDITION_TYPE_CONSTANT + Constant.PERCENT, value);
    }

    public SFunction getLeft() {
        return left;
    }

    public SFunction getRight() {
        return right;
    }

    public String getLogic() {
        return logic;
    }

    public Operator getOperator() {
        return operator;
    }

    public String getOperatorValue() {
        return operator.getValue();
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
