package com.wingflare.maven.plugin.replacer.annotation;

import java.util.List;

/**
 * 表示一个注解替换规则的配置类，包含参数名替换规则
 */
public class AnnotationReplacement {
    private String oldAnnotation;
    private String newAnnotation;
    private List<AnnotationParamReplacement> paramReplacements;

    public String getOldAnnotation() {
        return oldAnnotation;
    }

    public void setOldAnnotation(String oldAnnotation) {
        this.oldAnnotation = oldAnnotation;
    }

    public String getNewAnnotation() {
        return newAnnotation;
    }

    public void setNewAnnotation(String newAnnotation) {
        this.newAnnotation = newAnnotation;
    }

    public List<AnnotationParamReplacement> getParamReplacements() {
        return paramReplacements;
    }

    public void setParamReplacements(List<AnnotationParamReplacement> paramReplacements) {
        this.paramReplacements = paramReplacements;
    }
}
