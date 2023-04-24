package com.wingflare.tool.generator.strategy.handle;


import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.mbp.NameConverter;
import com.wingflare.tool.generator.strategy.Strategy;
import com.wingflare.tool.generator.strategy.StrategyHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceStrategyHandle implements StrategyHandle<Service.Builder> {

    @Autowired
    private GeneratorConfig generatorConfig;

    @Override
    public void handle(Service.Builder builder, Strategy strategy, boolean fileOverride) {
        NameConverter nameConverter = generatorConfig.getAvailableNameConverter();

        if (fileOverride) {
            builder.enableFileOverride();
        }

        if (generatorConfig.isGenServer()) {
            if (strategy.getStringProperty("superServiceClass") != null) {
                builder.superServiceClass(strategy.getStringProperty("superServiceClass"));
            }

            builder.convertServiceFileName(nameConverter::serviceNameConvert);
            builder.convertServiceImplFileName(nameConverter::serviceImplNameConvert);
        } else {
            builder.convertServiceImplFileName(nameConverter::serviceNameConvert);
        }

        if (strategy.getStringProperty("superServiceImplClass") != null) {
            builder.superServiceImplClass(strategy.getStringProperty("superServiceImplClass"));
        }
    }

    @Override
    public String getBuildClassName() {
        return Service.Builder.class.getName();
    }

    @Override
    public String getFileType() {
        return "Service";
    }
}
