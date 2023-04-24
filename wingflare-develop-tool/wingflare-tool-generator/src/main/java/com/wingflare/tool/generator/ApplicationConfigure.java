package com.wingflare.tool.generator;


import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.wingflare.tool.generator.configurer.GeneratorConfig;
import com.wingflare.tool.generator.mbp.TemplateEngine;
import com.wingflare.tool.generator.mbp.NameConverter;
import com.wingflare.tool.generator.service.UserConfigStore;
import com.wingflare.tool.generator.sqlparser.DynamicParamSqlEnhancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigure {

    @Bean
    public ProjectPathResolver projectPathResolver(GeneratorConfig config) {
        return new ProjectPathResolver(config.getBasePackage());
    }

    @Bean
    public DataSourceConfig mbpDsConfig(GeneratorConfig config) {
        return new DataSourceConfig.Builder(config.getJdbcUrl(), config.getUserName(), config.getPassword())
                .schema(config.getSchemaName())
                .typeConvert(config.getTypeConvert())
                .build();
    }

    @Bean
    public TemplateEngine beetlTemplateEngine(UserConfigStore userConfigStore) {
        return new TemplateEngine(new NameConverter() {
            /**
             * 自定义Service类文件的名称规则
             */
            @Override
            public String serviceNameConvert(String tableName) {
                return this.tableNameConvert(tableName) + "Service";
            }

            /**
             * 自定义Controller类文件的名称规则
             */
            @Override
            public String controllerNameConvert(String tableName) {
                return this.tableNameConvert(tableName) + "Action";
            }
        }, userConfigStore.getTemplateStoreDir());
    }

    @Bean
    public DynamicParamSqlEnhancer dynamicParamSqlEnhancer(DataSourceConfig dsc) {
        return new DynamicParamSqlEnhancer(dsc.getDbType());
    }
}
