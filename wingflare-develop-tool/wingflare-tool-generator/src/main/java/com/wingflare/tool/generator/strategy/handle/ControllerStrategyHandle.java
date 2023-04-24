package com.wingflare.tool.generator.strategy.handle;


import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.mbp.NameConverter;
import com.wingflare.tool.generator.strategy.Strategy;
import com.wingflare.tool.generator.strategy.StrategyHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerStrategyHandle implements StrategyHandle<Controller.Builder> {

    @Autowired
    private GeneratorConfig generatorConfig;

    @Override
    public void handle(Controller.Builder builder, Strategy strategy, boolean fileOverride) {
        NameConverter nameConverter = generatorConfig.getAvailableNameConverter();
        if (strategy.getBooleanProperty("restControllerStyle")) {
            builder.enableRestStyle();
        }

        if (strategy.getBooleanProperty("controllerMappingHyphenStyle")) {
            builder.enableHyphenStyle();
        }

        if (strategy.getStringProperty("superControllerClass") != null) {
            builder.superClass(strategy.getStringProperty("superControllerClass"));
        }

        builder.convertFileName(nameConverter::controllerNameConvert);
    }

    @Override
    public String getBuildClassName() {
        return Controller.Builder.class.getName();
    }

    @Override
    public String getFileType() {
        return "Controller";
    }
}
