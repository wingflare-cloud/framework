package com.wingflare.engine.task.server.common.enums;


/**
 * @since 1.5.0
 */
public enum WebSocketSceneEnum {
    JOB_LOG_SCENE(1, "JOB_LOG_SCENE"),
    WORKFLOW_LOG_SCENE(2, "WORKFLOW_LOG_SCENE"),
    RETRY_LOG_SCENE(3,"RETRY_LOG_SCENE");

    private final Integer type;
    private final String scene;

    WebSocketSceneEnum(Integer type, String scene) {
        this.type = type;
        this.scene = scene;
    }

    public Integer getType() {
        return type;
    }

    public String getScene() {
        return scene;
    }

    //    public static WebSocketSceneEnum valueOf(String scene) {
//        for (WebSocketSceneEnum expressionTypeEnum : WebSocketSceneEnum.values()) {
//            if (expressionTypeEnum.getScene().equals(scene)) {
//                return expressionTypeEnum;
//            }
//        }
//
//        return null;
//    }
}
