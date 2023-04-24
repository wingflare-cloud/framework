package com.wingflare.tool.generator.configurer;

import com.wingflare.tool.generator.dto.OutputFileInfo;
import com.wingflare.tool.generator.strategy.Strategy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "generator.default")
public class GeneratorDefaultConfig {

    private List<Strategy> strategies = new ArrayList<>();

    private List<OutputFileInfo> outputFileInfos = new ArrayList<>();

    public List<OutputFileInfo> getOutputFileInfos() {
        return outputFileInfos;
    }

    public void setOutputFileInfos(List<OutputFileInfo> outputFileInfos) {
        this.outputFileInfos = outputFileInfos;
    }

    public List<Strategy> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<Strategy> strategies) {
        this.strategies = strategies;
    }
}
