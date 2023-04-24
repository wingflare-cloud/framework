package com.wingflare.tool.generator.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wingflare.tool.generator.strategy.*;
import com.google.common.base.Strings;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserConfig {

    private List<OutputFileInfo> outputFiles;

    private List<Strategy> strategies;

    /**
     * 从另一个项目配置中合并可修改的配置项
     *
     * @param sourceUserConfig
     * @param sourceProjectConfigPath
     * @param targetProjectConfigPath
     */
    public void merge(UserConfig sourceUserConfig, String sourceProjectConfigPath, String targetProjectConfigPath) {
        changeTplPath(sourceUserConfig, sourceProjectConfigPath, targetProjectConfigPath);
    }

    private void changeTplPath(UserConfig sourceUserConfig, String sourceProjectConfigPath,
                               String targetProjectConfigPath) {
        Map<String, OutputFileInfo> sourceOutputFileInfoMap = new HashMap<>();

        for (OutputFileInfo source : sourceUserConfig.getOutputFiles()) {
            sourceOutputFileInfoMap.put(source.getFileType(), source);
        }

        for (OutputFileInfo dist : getOutputFiles()) {
            OutputFileInfo source = sourceOutputFileInfoMap.get(dist.getFileType());
            if (source == null || Strings.isNullOrEmpty(source.getTemplatePath())) {
                continue;
            }

            dist.setTemplatePath(source.getTemplatePath().replace(sourceProjectConfigPath, targetProjectConfigPath));
        }
    }


}
