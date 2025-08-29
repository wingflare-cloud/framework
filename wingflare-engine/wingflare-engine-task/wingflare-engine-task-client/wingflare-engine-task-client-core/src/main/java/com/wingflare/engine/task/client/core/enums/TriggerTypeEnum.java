package com.wingflare.engine.task.client.core.enums;


public enum TriggerTypeEnum {
    SCHEDULED_TIME(2),
    CRON(3),
    POINT_IN_TIME(5),
    WORK_FLOW(99);

    private final int type;

    TriggerTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
