package com.wingflare.facade.lib.task;

/**
 * 定时任务状态
 */
public enum ScheduleStatus {

    /**
     * 正常
     */
    NORMAL("0"),
    /**
     * 暂停
     */
    PAUSE("1");

    private final String value;

    private ScheduleStatus(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

}
