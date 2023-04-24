package com.wingflare.tool.generator.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.Strings;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OutputFileInfo {

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    /**
     * 是否忽略文件前缀
     */
    private boolean hidePrefix = false;

    /**
     * 文件的输出包名
     */
    private String outputLocation;

    /**
     * 模板名
     */
    private String templateName;

    /**
     * 模板路径
     */
    @JsonIgnore
    private String templatePath;

    /**
     * 包后缀
     */
    private String pkgSuffix;


    private boolean builtIn;

    public String getOutputPackage() {
        if (Strings.isNullOrEmpty(outputLocation)) {
            return "";
        }

        if (outputLocation.startsWith(Constant.PACKAGE_RESOURCES_PREFIX)) {
            return outputLocation.replaceFirst(Constant.PACKAGE_RESOURCES_PREFIX, "");
        } else if (outputLocation.startsWith(Constant.PACKAGE_JAVA_PREFIX)) {
            return outputLocation.replaceFirst(Constant.PACKAGE_JAVA_PREFIX, "");
        }

        return outputLocation;
    }

    @JsonIgnore
    public String getAvailableTemplatePath() {
        if (!Strings.isNullOrEmpty(templatePath)) {
            return templatePath;
        }
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OutputFileInfo) {
            OutputFileInfo file = (OutputFileInfo) obj;
            if (file.getFileType() == null || this.getFileType() == null) {
                return false;
            }
            return file.getFileType().equalsIgnoreCase(this.getFileType());
        }
        return false;
    }

}
