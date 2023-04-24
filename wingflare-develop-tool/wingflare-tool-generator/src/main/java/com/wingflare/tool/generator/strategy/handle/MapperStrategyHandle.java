package com.wingflare.tool.generator.strategy.handle;


import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.mbp.NameConverter;
import com.wingflare.tool.generator.strategy.Strategy;
import com.wingflare.tool.generator.strategy.StrategyHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperStrategyHandle implements StrategyHandle<Mapper.Builder> {

    @Autowired
    private GeneratorConfig generatorConfig;

    @Override
    public void handle(Mapper.Builder builder, Strategy strategy, boolean fileOverride) {
        NameConverter nameConverter = generatorConfig.getAvailableNameConverter();

        if (strategy.getStringProperty("superMapperClass") != null) {
            builder.superClass(strategy.getStringProperty("superMapperClass"));
        }

        if (strategy.getBooleanProperty("baseResultMap")) {
            builder.enableBaseResultMap();
        }

        if (strategy.getBooleanProperty("baseColumnList")) {
            builder.enableBaseColumnList();
        }

        builder.convertMapperFileName(nameConverter::mapperNameConvert);
        builder.convertXmlFileName(nameConverter::mapperXmlNameConvert);

        if (fileOverride) {
            builder.enableFileOverride();
        }
    }

    @Override
    public String getBuildClassName() {
        return Mapper.Builder.class.getName();
    }

    @Override
    public String getFileType() {
        return "Mapper.java";
    }
}
