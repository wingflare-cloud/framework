package com.wingflare.engine.task.common.core.enums;


/**
 * 1、任务节点 2、条件节点 3、回调节点
 *
 * @author xiaowoniu
 * @date 2023-12-24 08:13:43
 * @since 2.6.0
 */
public enum WorkflowNodeTypeEnum {
    JOB_TASK(1, "JOB task"),
    DECISION(2, "Decision node"),
    CALLBACK(3, "Callback node"),
    ;

    private final int type;
    private final String desc;

    WorkflowNodeTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static WorkflowNodeTypeEnum valueOf(int type) {
        for (WorkflowNodeTypeEnum workflowNodeTypeEnum : WorkflowNodeTypeEnum.values()) {
            if (workflowNodeTypeEnum.getType() == type) {
                return workflowNodeTypeEnum;
            }
        }
        return null;
    }
}
