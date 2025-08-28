package com.wingflare.lib.task.model;


/**
 * 幂等id上下文
 *
 * @author zy9567
 * @date 2023-06-28 19:03
 * @since 2.0.0
 */
public class IdempotentIdContext {

    /**
     * 场景名称
     */
    private String scene;

    /**
     * 执行器名称
     */
    private String targetClassName;

    /**
     * 参数列表
     */
    private Object[] args;

    /**
     * 序列化后的参数信息
     */
    private String serializeArgs;

    /**
     * 执行的方法名称
     */
    private String methodName;

    public IdempotentIdContext(String scene, String targetClassName, Object[] args, String serializeArgs, String methodName) {
        this.scene = scene;
        this.targetClassName = targetClassName;
        this.args = args;
        this.serializeArgs = serializeArgs;
        this.methodName = methodName;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getSerializeArgs() {
        return serializeArgs;
    }

    public void setSerializeArgs(String serializeArgs) {
        this.serializeArgs = serializeArgs;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
