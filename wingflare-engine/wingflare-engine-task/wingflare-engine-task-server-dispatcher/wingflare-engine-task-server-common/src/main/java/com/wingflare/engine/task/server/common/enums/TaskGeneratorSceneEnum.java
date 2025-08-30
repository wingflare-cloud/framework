package com.wingflare.engine.task.server.common.enums;

import com.wingflare.engine.task.server.common.exception.SnailJobServerException;

/**
 * id生成模式
 *
 * @author opensnail
 * @date 2023-05-04
 * @since 2.0
 */
public enum TaskGeneratorSceneEnum {

    CLIENT_REPORT(1, "Client matching report"),
    MANA_BATCH(2, "Console manual batch addition"),
    MANA_SINGLE(3, "Console manual single addition"),
    ;

    private final int scene;

    private final String desc;

    TaskGeneratorSceneEnum(int scene, String desc) {
        this.scene = scene;
        this.desc = desc;
    }

    public int getScene() {
        return scene;
    }

    public String getDesc() {
        return desc;
    }

    public static TaskGeneratorSceneEnum modeOf(int scene) {
        for (TaskGeneratorSceneEnum value : TaskGeneratorSceneEnum.values()) {
            if (value.getScene() == scene) {
                return value;
            }
        }

        throw new SnailJobServerException("Unsupported task generation scenario [{}]", scene);
    }

}
