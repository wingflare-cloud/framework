package com.wingflare.tool.generator.strategy.handle;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.mbp.NameConverter;
import com.wingflare.tool.generator.strategy.Strategy;
import com.wingflare.tool.generator.strategy.StrategyHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class DoStrategyHandle implements StrategyHandle<Entity.Builder> {

    @Autowired
    private GeneratorConfig generatorConfig;

    @Override
    public void handle(Entity.Builder builder, Strategy strategy, boolean fileOverride) {
        if (fileOverride) {
            builder.enableFileOverride();
        }

        NameConverter nameConverter = generatorConfig.getAvailableNameConverter();
        builder.idType(generatorConfig.getIdType());
        builder.nameConvert(new INameConvert() {
            @Override
            @Nonnull
            public String entityNameConvert(@Nonnull TableInfo tableInfo) {
                return nameConverter.tableNameConvert(tableInfo.getName());
            }

            @Override
            @Nonnull
            public String propertyNameConvert(@Nonnull TableField field) {
                return nameConverter.propertyNameConvert(field.getName());
            }
        });

        builder.superClass(strategy.getStringProperty("superDoClass"));

        if (!strategy.getBooleanProperty("entitySerialVersionUID")) {
            builder.disableSerialVersionUID();
        }

        if (strategy.getBooleanProperty("entityBuilderModel")) {
            builder.enableChainModel();
        }

        if (strategy.getBooleanProperty("entityLombokModel")) {
            builder.enableLombok();
        }

        if (strategy.getBooleanProperty("entityBooleanColumnRemoveIsPrefix")) {
            builder.enableRemoveIsPrefix();
        }

        if (strategy.getBooleanProperty("entityTableFieldAnnotationEnable")) {
            builder.enableTableFieldAnnotation();
        }

        if (strategy.getBooleanProperty("activeRecord")) {
            builder.enableActiveRecord();
        }

        if (!StrUtil.isEmpty(strategy.getStringProperty("versionFieldName"))) {
            builder.versionColumnName(strategy.getStringProperty("versionFieldName"));
            builder.versionPropertyName(strategy.getStringProperty("versionFieldName"));
        }

        if (!StrUtil.isEmpty(strategy.getStringProperty("logicDeleteFieldName"))) {
            builder.logicDeleteColumnName(strategy.getStringProperty("logicDeleteFieldName"));
            builder.logicDeletePropertyName(strategy.getStringProperty("logicDeleteFieldName"));
        }

        if (strategy.containsKey("superDoColumns")) {
            Object o = strategy.getProperty("superDoColumns");
            if (o instanceof List) {
                builder.addSuperEntityColumns((List<String>) o);
            } else if (o instanceof String[]) {
                builder.addSuperEntityColumns((String[]) o);
            } else if (o instanceof String) {
                builder.addSuperEntityColumns((String) o);
            }
        }

        if (strategy.containsKey("tableFills")) {
            Object o = strategy.getProperty("tableFills");
            List<String> tableFills;
            if (o instanceof List) {
                tableFills = new ArrayList<>((List<String>) o);
            } else if (o instanceof String[]) {
                tableFills = Arrays.asList((String[]) o);
            } else if (o instanceof LinkedHashMap) {
                tableFills = new ArrayList<>(((LinkedHashMap)o).values());
            } else {
                tableFills = new ArrayList<>();
            }

            List<IFill> tableIFills = new ArrayList<>();

            for (String tableFillStr : tableFills) {
                if (!StrUtil.isEmpty(tableFillStr)) {
                    String[] tmp = tableFillStr.split(":");
                    IFill tableFill = new Column(tmp[0], FieldFill.valueOf(tmp[1].toUpperCase()));
                    tableIFills.add(tableFill);
                }
            }
            builder.addTableFills(tableIFills);
        }

        builder.idType(generatorConfig.getIdType());
        builder.convertFileName(nameConverter::doNameConvert);
    }

    @Override
    public String getBuildClassName() {
        return Entity.Builder.class.getName();
    }

    @Override
    public String getFileType() {
        return "Do";
    }
}
