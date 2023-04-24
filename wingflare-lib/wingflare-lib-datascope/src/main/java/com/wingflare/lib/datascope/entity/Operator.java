package com.wingflare.lib.datascope.entity;

/**
 * @author naizui_ycx
 * @date {2022/1/19}
 * @description
 */
public class Operator {

    public static final String IS = "is";

    public static final String NOT = "not";

    public static final String LIKE = "like";

    public static final String NO_LIKE = "not_like";

    private String name;

    private String fieldName;

    private String contextName;

    private String operator;

    public Operator(String operator) {
       setOperator(operator);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
