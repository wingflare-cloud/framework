package com.wingflare.maven.plugin.replacer;


/**
 * 表示一个类替换规则的配置类，包含参数名替换规则
 */
public class ClassesReplacement {
    private String oldClass;
    private String newClass;

    public String getOldClass() {
        return oldClass;
    }

    public void setOldClass(String oldClass) {
        this.oldClass = oldClass;
    }

    public String getNewClass() {
        return newClass;
    }

    public void setNewClass(String newClass) {
        this.newClass = newClass;
    }
}
