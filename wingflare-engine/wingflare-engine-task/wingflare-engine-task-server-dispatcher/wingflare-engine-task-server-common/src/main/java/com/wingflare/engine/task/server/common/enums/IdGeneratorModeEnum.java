package com.wingflare.engine.task.server.common.enums;


/**
 * id生成模式
 *
 * @author opensnail
 * @date 2023-05-04
 * @since 2.0
 */
public enum IdGeneratorModeEnum {

    SEGMENT(1, "Number segment mode"),
    SNOWFLAKE(2, "Snowflake algorithm mode");

    private final int mode;

    private final String desc;

    IdGeneratorModeEnum(int mode, String desc) {
        this.mode = mode;
        this.desc = desc;
    }

    public int getMode() {
        return mode;
    }

    public String getDesc() {
        return desc;
    }

    public static IdGeneratorModeEnum modeOf(int mode) {
        for (IdGeneratorModeEnum value : IdGeneratorModeEnum.values()) {
            if (value.getMode() == mode) {
                return value;
            }
        }

        return null;
    }

}
