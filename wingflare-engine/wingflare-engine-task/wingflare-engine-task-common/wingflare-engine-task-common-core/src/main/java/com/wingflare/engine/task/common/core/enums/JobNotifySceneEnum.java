package com.wingflare.engine.task.common.core.enums;


import com.wingflare.lib.core.utils.StringUtil;

/**
 * 通知场景枚举
 *
 * @author: zuoJunLin
 * @date : 2023-12-02 18:18
 */
public enum JobNotifySceneEnum {

    /********************************Job****************************************/
    NONE(0, StringUtil.EMPTY, NodeTypeEnum.SERVER),
    JOB_TASK_ERROR(1, "JOB task execution failed", NodeTypeEnum.SERVER),
    JOB_CLIENT_ERROR(2, "Client execution failed", NodeTypeEnum.CLIENT),
    JOB_NO_CLIENT_NODES_ERROR(3, "No executable client nodes", NodeTypeEnum.SERVER),

    /********************************Workflow****************************************/
    WORKFLOW_TASK_ERROR(100, "Workflow task execution failed", NodeTypeEnum.SERVER);

    /**
     * 通知场景
     */
    private final int notifyScene;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 触发通知节点类型
     */
    private final NodeTypeEnum nodeType;

    JobNotifySceneEnum(int notifyScene, String desc, NodeTypeEnum nodeType) {
        this.notifyScene = notifyScene;
        this.desc = desc;
        this.nodeType = nodeType;
    }

    public int getNotifyScene() {
        return notifyScene;
    }

    public String getDesc() {
        return desc;
    }

    public NodeTypeEnum getNodeType() {
        return nodeType;
    }

    /**
     * 获取通知场景
     *
     * @param notifyScene 场景
     * @param nodeType    触发通知节点类型
     * @return this
     */
    public static JobNotifySceneEnum getJobNotifyScene(int notifyScene, NodeTypeEnum nodeType) {
        for (JobNotifySceneEnum sceneEnum : JobNotifySceneEnum.values()) {
            if (sceneEnum.getNotifyScene() == notifyScene && sceneEnum.nodeType.getType().equals(nodeType.getType())) {
                return sceneEnum;
            }
        }

        return NONE;
    }

    /**
     * 获取通知场景描述
     *
     * @param notifyScene
     * @return
     */
    public static JobNotifySceneEnum getJobNotifyScene(Integer notifyScene) {
        for (JobNotifySceneEnum sceneEnum : JobNotifySceneEnum.values()) {
            if (sceneEnum.getNotifyScene() == notifyScene) {
                return sceneEnum;
            }
        }

        return NONE;
    }
}
