package com.wingflare.tool.generator.mbp;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.BaseBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.google.common.collect.Maps;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.tool.generator.ProjectPathResolver;
import com.wingflare.tool.generator.common.ServiceException;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.dto.Constant;
import com.wingflare.tool.generator.dto.GenSetting;
import com.wingflare.tool.generator.dto.OutputFileInfo;
import com.wingflare.tool.generator.dto.UserConfig;
import com.wingflare.tool.generator.service.UserConfigStore;
import com.wingflare.tool.generator.strategy.Strategy;
import com.wingflare.tool.generator.strategy.StrategyHandle;
import com.wingflare.tool.generator.util.PathUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class MbpGenerator implements ApplicationContextAware {

    @Autowired
    private DataSourceConfig ds;

    @Autowired
    private GeneratorConfig generatorConfig;

    @Autowired
    private UserConfigStore userConfigStore;

    @Autowired
    private ProjectPathResolver projectPathResolver;

    @Autowired
    private TemplateEngine templateEngine;

    private static ApplicationContext applicationContext;

    /**
     * 根据所选择的配置生成CRUD代码
     */
    public void genCodeBatch(GenSetting genSetting, List<String> tables) {
        checkGenSetting(genSetting);
        projectPathResolver.refreshBaseProjectPath(genSetting.getRootPath());
        //生成策略配置
        UserConfig userConfig = userConfigStore.getDefaultUserConfig();
        FastAutoGenerator
                .create(ds.getUrl(), ds.getUsername(), ds.getPassword())
                .dataSourceConfig(
                        builder -> {
                            builder.schema(generatorConfig.getSchemaName());
                            builder.typeConvert(generatorConfig.getTypeConvert());
                        }
                ).globalConfig(builder -> {
                    builder.dateType(generatorConfig.getDateType());
                    //指定所有生成文件的根目录
                    builder.outputDir(projectPathResolver.getSourcePath());
                    builder.author(genSetting.getAuthor());
                    if (generatorConfig.isEnableSwagger()) {
                        builder.enableSwagger();
                    }
                }).templateEngine(templateEngine).packageConfig(builder -> {
                    configPackage(builder, genSetting, genSetting.getModuleName(), userConfig);
                }).templateConfig(builder -> {
                    configTemplate(builder, genSetting.getChoosedOutputFiles(), userConfig);
                }).injectionConfig(builder -> {
                    configInjection(builder, userConfig, genSetting, tables);
                }).strategyConfig(builder -> {
                    builder.addInclude(String.join(",", tables))
                            .disableSqlFilter()
                            .enableSkipView();
                    // 配置entity的生成信息
                    configStrategy(builder.entityBuilder(), userConfig.getStrategies(), genSetting.isOverrideFile());
                    // 配置mapper和mapper-xml
                    configStrategy(builder.mapperBuilder(), userConfig.getStrategies(), genSetting.isOverrideFile());
                    // 配置service
                    configStrategy(builder.serviceBuilder(), userConfig.getStrategies(), genSetting.isOverrideFile());
                    // 配置Controller
                    configStrategy(builder.controllerBuilder(), userConfig.getStrategies(), genSetting.isOverrideFile());
                }).execute();
    }

    private void configPackage(PackageConfig.Builder builder, GenSetting genSetting, String moduleName, UserConfig userConfig) {
        String mapperXmlOutputPath = getOutputPathByFileType(Constant.FILE_TYPE_MAPPER_XML, userConfig);
        if (!StrUtil.isEmpty(moduleName)) {
            mapperXmlOutputPath = mapperXmlOutputPath + File.separator + moduleName;
        }

        String serverPkg = null;
        OutputFileInfo serverOutputFileInfo = userConfig.getOutputFiles().stream()
                .filter(item -> Constant.FILE_TYPE_SERVICE.equals(item.getFileType()))
                .findFirst()
                .orElse(null);

        if (serverOutputFileInfo != null) {
            serverPkg = PathUtil.joinPackage(genSetting.getBasePackage(), serverOutputFileInfo.getPkgSuffix(),
                    moduleName, serverOutputFileInfo.getOutputPackage());
        }

        for (OutputFileInfo outputFileInfo : userConfig.getOutputFiles()) {
            String pkg = PathUtil.joinPackage(genSetting.getBasePackage(), outputFileInfo.getPkgSuffix(),
                    moduleName, outputFileInfo.getOutputPackage());

            if (outputFileInfo.getFileType().equals(Constant.FILE_TYPE_DO)) {
                builder.entity(pkg);
            } else if (outputFileInfo.getFileType().equals(Constant.FILE_TYPE_MAPPER)) {
                builder.mapper(pkg);
            } else if (outputFileInfo.getFileType().equals(Constant.FILE_TYPE_SERVICE)) {
                if (generatorConfig.isGenServer()) {
                    builder.service(pkg);
                }
            } else if (outputFileInfo.getFileType().equals(Constant.FILE_TYPE_SERVICEIMPL)) {
                if (StrUtil.isNotEmpty(serverPkg) && !generatorConfig.isGenServer()) {
                    builder.serviceImpl(serverPkg);
                } else {
                    builder.serviceImpl(pkg);
                }
            } else if (outputFileInfo.getFileType().equals(Constant.FILE_TYPE_CONTROLLER)) {
                builder.controller(pkg);
            }

        }

        //子包名已经包含了完整路径
        builder.parent("")
                .moduleName("")
                .pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlOutputPath));
    }

    private void configTemplate(TemplateConfig.Builder builder, List<String> choosedFileTypes, UserConfig userConfig) {
        builder.entity(findTemplatePath(Constant.FILE_TYPE_DO, userConfig));
        builder.mapper(findTemplatePath(Constant.FILE_TYPE_MAPPER, userConfig));
        builder.xml(findTemplatePath(Constant.FILE_TYPE_MAPPER_XML, userConfig));
        builder.service(findTemplatePath(Constant.FILE_TYPE_SERVICE, userConfig));
        builder.serviceImpl(findTemplatePath(Constant.FILE_TYPE_SERVICEIMPL, userConfig));
        builder.controller(findTemplatePath(Constant.FILE_TYPE_CONTROLLER, userConfig));

        if (!choosedFileTypes.contains(Constant.FILE_TYPE_DO)) {
            builder.disable(TemplateType.ENTITY);
        }

        if (!choosedFileTypes.contains(Constant.FILE_TYPE_MAPPER)) {
            builder.disable(TemplateType.MAPPER);
        }

        if (!choosedFileTypes.contains(Constant.FILE_TYPE_MAPPER_XML)) {
            builder.disable(TemplateType.XML);
        }

        if (!choosedFileTypes.contains(Constant.FILE_TYPE_SERVICE) || !generatorConfig.isGenServer()) {
            builder.disable(TemplateType.SERVICE);
        }

        if (!choosedFileTypes.contains(Constant.FILE_TYPE_SERVICEIMPL)) {
            builder.disable(TemplateType.SERVICE_IMPL);
        }

        if (!choosedFileTypes.contains(Constant.FILE_TYPE_CONTROLLER)) {
            builder.disable(TemplateType.CONTROLLER);
        }
    }

    //自定义模板参数配置
    private void configInjection(InjectionConfig.Builder builder, UserConfig userConfig, GenSetting genSetting,
                                 List<String> tables) {
        Map<String, Object> baseInfoMap = new HashMap<>();
        Map<String, String> pkgMap = new HashMap<>();
        Map<String, List<String>> allBizPkg = new LinkedHashMap<>();
        baseInfoMap.put("allBizPkg", allBizPkg);
        baseInfoMap.put("pkg", pkgMap);
        baseInfoMap.put("isOverrideMethod", genSetting.isOverrideMethod());

        String serPkgSuffix = null;
        String serOutputPackage = null;

        if (generatorConfig.isGenServer()) {
            OutputFileInfo serOutputFileInfo = findFileConfigByType(Constant.FILE_TYPE_SERVICE, userConfig);

            if (serOutputFileInfo != null) {
                serPkgSuffix = serOutputFileInfo.getPkgSuffix();
                serOutputPackage = serOutputFileInfo.getOutputPackage();
            }
        } else {
            OutputFileInfo serOutputFileInfo = findFileConfigByType(Constant.FILE_TYPE_SERVICEIMPL, userConfig);

            if (serOutputFileInfo != null) {
                serPkgSuffix = serOutputFileInfo.getPkgSuffix();
                serOutputPackage = serOutputFileInfo.getOutputPackage();
            }
        }

        if (StrUtil.isNotEmpty(serPkgSuffix) && StrUtil.isNotEmpty(serOutputPackage)) {
            for (String table : tables) {
                String serName;
                String tableName = (new NameConverter() {
                }).tableNameConvert(table);
                if (generatorConfig.isGenServer()) {
                    serName = tableName + "Server";
                } else {
                    serName = tableName + "ServerImpl";
                }

                String pkg = PathUtil.joinPackage(genSetting.getBasePackage(), serPkgSuffix,
                        genSetting.getModuleName(), serOutputPackage, serName);
                allBizPkg.computeIfAbsent("serImpl", key -> new ArrayList<>()).add(pkg);
            }
        }

        OutputFileInfo bizOutputFileInfo = findFileConfigByType(Constant.FILE_TYPE_BIZIMPl, userConfig);

        if (bizOutputFileInfo != null) {
            for (String table : tables) {
                String tableName = (new NameConverter() {
                }).tableNameConvert(table);
                String bizName = tableName + "BizImpl";
                String pkg = PathUtil.joinPackage(genSetting.getBasePackage(), bizOutputFileInfo.getPkgSuffix(),
                        genSetting.getModuleName(), bizOutputFileInfo.getOutputPackage(), bizName);
                allBizPkg.computeIfAbsent("bizImpl", key -> new ArrayList<>()).add(pkg);
            }
        }

        List<String> hidePrefix = new ArrayList<>();
        baseInfoMap.put("hidePrefix", hidePrefix);
        //自定义文件生成
        for (OutputFileInfo outputFileInfo : userConfig.getOutputFiles()) {
            if (!outputFileInfo.isBuiltIn()
                    && genSetting.getChoosedOutputFiles().contains(outputFileInfo.getFileType())) {

                String pkg = PathUtil.joinPackage(genSetting.getBasePackage(), outputFileInfo.getPkgSuffix(),
                        genSetting.getModuleName(), outputFileInfo.getOutputPackage());

                CustomFile.Builder fileBuilder = new CustomFile.Builder();
                String fileName;
                if (StrUtil.isNotEmpty(outputFileInfo.getFileSuffix())) {
                    fileName = outputFileInfo.getFileSuffix();
                } else {
                    fileName = outputFileInfo.getFileType();
                }
                fileBuilder.fileName(fileName);
                fileBuilder.templatePath(outputFileInfo.getTemplatePath());
                fileBuilder.packageName(pkg);
                if (genSetting.isOverrideFile()) {
                    fileBuilder.enableFileOverride();
                }
                pkgMap.put(outputFileInfo.getFileType(), pkg);
                if (outputFileInfo.isHidePrefix()) {
                    hidePrefix.add(String.format("%s_%s", outputFileInfo.getTemplatePath(), fileName));
                }
                builder.customFile(fileBuilder.build());
            }
        }

        //自定义参数
        builder.beforeOutputFile((tableInfo, objectMap) -> {
            TemplateVaribleInjecter varibleInjecter = generatorConfig.getTemplateVaribleInjecter();
            Map<String, Object> vars = null;
            if (varibleInjecter != null) {
                vars = varibleInjecter.getCustomTemplateVaribles(tableInfo);
            }

            if (vars == null) {
                vars = Maps.newHashMap();
            }

            vars.putAll(baseInfoMap);
            vars.put("basePackage", genSetting.getBasePackage());
            vars.put("moduleNamePkg", genSetting.getModuleName());
            //用于控制controller中对应API是否展示的自定义参数
            Map<String, Object> controllerMethodsVar = Maps.newHashMap();

            for (String method : genSetting.getChoosedControllerMethods()) {
                controllerMethodsVar.put(method, true);
            }

            if (controllerMethodsVar.size() > 0) {
                controllerMethodsVar.put("hasMethod", true);
            }

            vars.put("controllerMethods", controllerMethodsVar);

            if (!StrUtil.isEmpty(generatorConfig.getSchemaName())) {
                vars.put("schemaName", generatorConfig.getSchemaName() + ".");
            }

            if (tableInfo.isHavePrimaryKey()) {
                for (TableField field : tableInfo.getFields()) {
                    if (field.isKeyFlag()) {
                        vars.put("primaryField", field);
                    }
                }
            }

            vars.put("setOnNewIgnoreFields",
                    CollectionUtils.isEmpty(generatorConfig.getSetOnNewIgnoreFields())
                            ? new ArrayList<>() : generatorConfig.getSetOnNewIgnoreFields());

            vars.put("tableBusinessName", (new NameConverter() {
                @Override
                public String tableNameConvert(String tableName) {
                    return StringUtil.uncapitalize(NameConverter.super.tableNameConvert(tableName));
                }
            }).tableNameConvert(tableInfo.getName()));

            vars.put("isGenServer", generatorConfig.isGenServer());

            objectMap.putAll(vars);
        });
    }

    private void checkGenSetting(GenSetting genSetting) {
        if (StrUtil.isEmpty(genSetting.getRootPath())) {
            throw new ServiceException("目标项目根目录不能为空");
        }

        genSetting.setRootPath(projectPathResolver.getUTF8String(genSetting.getRootPath()));

        if (!FileUtil.isDirectory(genSetting.getRootPath())) {
            throw new ServiceException("目标项目根目录错误，请确认目录有效且存在：" + genSetting.getRootPath());
        }

        if (!genSetting.getRootPath().endsWith(File.separator)) {
            genSetting.setRootPath(genSetting.getRootPath() + File.separator);
        }
    }

    private <T extends BaseBuilder> void configStrategy(T builder, List<Strategy> strategies, boolean fileOverride) {
        Collection<StrategyHandle> strategyHandles = applicationContext.getBeansOfType(StrategyHandle.class)
                .values();

        if (!CollectionUtils.isEmpty(strategyHandles)) {
            String clsName = builder.getClass().getName();
            StrategyHandle strategyHandle = strategyHandles.stream()
                    .filter(item -> clsName.equals(item.getBuildClassName()))
                    .findFirst()
                    .orElse(null);

            if (strategyHandle != null) {
                Strategy strategy = strategies.stream()
                        .filter(item -> strategyHandle.getFileType().equals(item.getFileType()))
                        .findFirst()
                        .orElse(new Strategy());
                strategyHandle.handle(builder, strategy, fileOverride);
            }
        }

    }

    private OutputFileInfo findFileConfigByType(String fileType, UserConfig userConfig) {
        for (OutputFileInfo outputFileInfo : userConfig.getOutputFiles()) {
            if (fileType.equals(outputFileInfo.getFileType())) {
                return outputFileInfo;
            }
        }
        return null;
    }

    private String getOutputPathByFileType(String fileType, UserConfig userConfig) {
        return projectPathResolver.convertPackageToPath(Objects.requireNonNull(findFileConfigByType(fileType, userConfig))
                .getOutputLocation());
    }

    private String findTemplatePath(String fileType, UserConfig userConfig) {
        return Objects.requireNonNull(findFileConfigByType(fileType, userConfig)).getAvailableTemplatePath();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MbpGenerator.applicationContext = applicationContext;
    }
}
