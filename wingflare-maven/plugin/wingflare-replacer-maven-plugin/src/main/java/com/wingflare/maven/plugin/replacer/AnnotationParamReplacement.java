package com.wingflare.maven.plugin.replacer;

/**
 * 表示注解参数名的替换规则
 */
public class AnnotationParamReplacement {
    private String oldParamName;
    private String newParamName;

    public String getOldParamName() {
        return oldParamName;
    }

    public void setOldParamName(String oldParamName) {
        this.oldParamName = oldParamName;
    }

    public String getNewParamName() {
        return newParamName;
    }

    public void setNewParamName(String newParamName) {
        this.newParamName = newParamName;
    }
}