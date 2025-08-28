package com.wingflare.lib.task.enums;


import java.util.Objects;

/**
 * @author: opensnail
 * @date : 2024-06-12
 * @since : sj_1.1.0
 */
public enum MapReduceStageEnum {
    MAP(1),
    REDUCE(2),
    MERGE_REDUCE(3);

    private final int stage;

    MapReduceStageEnum(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return stage;
    }

    public static MapReduceStageEnum ofStage(Integer stage) {
        if (Objects.isNull(stage)) {
            return null;
        }

        for (MapReduceStageEnum value : MapReduceStageEnum.values()) {
            if (value.getStage() == stage) {
                return value;
            }
        }

        return null;
    }



}
