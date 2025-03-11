package com.wingflare.tool.generator.configurer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.wingflare.tool.generator.mbp.NameConverter;
import com.wingflare.tool.generator.mbp.TemplateVaribleInjecter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@ConfigurationProperties(prefix = "generator")
public class GeneratorConfig {

    /**
     * 生成的文件所保存的包路径
     */
    private String basePackage = "com.wingflare";

    /**
     * 数据库地址
     */
    private String jdbcUrl;

    /**
     * 数据库schema,POSTGRE_SQL,ORACLE,DB2，MSSQL类型的数据库需要指定
     */
    private String schemaName;

    /**
     * 数据库用户名
     */
    private String userName;

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 是否开启Swagger
     */
    private boolean enableSwagger = false;

    /**
     * 数据库驱动名
     */
    private String driverClassName = "com.mysql.cj.jdbc.Driver";

    /**
     * 数据库时间类型与java class的对应策略
     */
    private DateType dateType;

    /**
     * 注入自定义模板参数
     */
    private TemplateVaribleInjecter templateVaribleInjecter;

    /**
     * 自定义名称转换规则
     */
    private NameConverter nameConverter;

    /**
     * 自定义数据字段类型和实体类型映射
     */
    private ITypeConvert typeConvert;

    /**
     * 是否生成server接口
     */
    private boolean genServerInterface = true;

    /**
     * setOnNew中的忽略字段
     */
    private List<String> setOnNewIgnoreFields;

    /**
     * 全局指定数据表中ID的生成模式，影响自动生成的Entity中ID字段的注解
     */
    private IdType idType;

    public NameConverter getAvailableNameConverter() {
        if (nameConverter == null) {
            nameConverter = new NameConverter() {
            };
        }
        return nameConverter;
    }

    public DateType getDateType() {
        if (this.dateType == null) {
            return DateType.ONLY_DATE;
        }
        return dateType;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnableSwagger() {
        return enableSwagger;
    }

    public void setEnableSwagger(boolean enableSwagger) {
        this.enableSwagger = enableSwagger;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }

    public TemplateVaribleInjecter getTemplateVaribleInjecter() {
        return templateVaribleInjecter;
    }

    public void setTemplateVaribleInjecter(TemplateVaribleInjecter templateVaribleInjecter) {
        this.templateVaribleInjecter = templateVaribleInjecter;
    }

    public NameConverter getNameConverter() {
        return nameConverter;
    }

    public void setNameConverter(NameConverter nameConverter) {
        this.nameConverter = nameConverter;
    }

    public ITypeConvert getTypeConvert() {
        return typeConvert;
    }

    public void setTypeConvert(ITypeConvert typeConvert) {
        this.typeConvert = typeConvert;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public boolean isGenServerInterface() {
        return genServerInterface;
    }

    public void setGenServerInterface(boolean genServerInterface) {
        this.genServerInterface = genServerInterface;
    }

    public List<String> getSetOnNewIgnoreFields() {
        return setOnNewIgnoreFields;
    }

    public void setSetOnNewIgnoreFields(List<String> setOnNewIgnoreFields) {
        this.setOnNewIgnoreFields = setOnNewIgnoreFields;
    }
}
