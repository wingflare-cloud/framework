package com.wingflare.lib.task.enums;


import com.wingflare.lib.task.exception.SnailJobCommonException;

/**
 * @author opensnail
 * @date 2023-10-02 10:39:22
 * @since 2.4.0
 */
public enum JobTaskTypeEnum {

    CLUSTER(1),
    BROADCAST(2),
    SHARDING(3),
    MAP(4),
    MAP_REDUCE(5),
    ;

    private final int type;

    JobTaskTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static JobTaskTypeEnum valueOf(int type) {
        for (JobTaskTypeEnum value : JobTaskTypeEnum.values()) {
            if (value.getType() == type) {
                return value;
            }
        }

        throw new SnailJobCommonException("Unknown type");
    }

}
