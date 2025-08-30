package com.wingflare.engine.task.server.common.enums;

/**
 * @author xiaowoniu
 * @date 2023-12-15 22:42:17
 * @since 2.6.0
 */
public enum WorkflowNodeTypeEnum {
    TASK(1, "Task node"),
    CONDITION(2, "Condition node"),
    CALLBACK(3, "Callback node");

    private final Integer nodeType;
    private final String desc;

    WorkflowNodeTypeEnum(Integer nodeType, String desc) {
        this.nodeType = nodeType;
        this.desc = desc;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public String getDesc() {
        return desc;
    }

    public static WorkflowNodeTypeEnum get(Integer nodeType) {
        for (WorkflowNodeTypeEnum workflowNodeTypeEnum : WorkflowNodeTypeEnum.values()) {
            if (workflowNodeTypeEnum.nodeType.equals(nodeType)) {
                return workflowNodeTypeEnum;
            }
        }
        return null;
    }
}
