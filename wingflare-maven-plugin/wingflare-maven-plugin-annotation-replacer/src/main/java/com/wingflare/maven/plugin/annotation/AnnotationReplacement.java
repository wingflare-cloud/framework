package com.wingflare.maven.plugin.annotation;

/**
 * 表示一个注解替换规则的配置类
 */
public class AnnotationReplacement {
    private String oldAnnotation;
    private String newAnnotation;

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
}