package com.wingflare.tool.generator.mbp;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Strings;

/**
 * 自定义各类名称转换的规则
 */
public interface NameConverter {

    /**
     * 自定义Entity.java的类名称（文件名等于类名加固定后缀）
     *
     * @param tableName 表名称
     * @return 转换后的实体类名称
     */
    default String doNameConvert(String tableName) {
        if (Strings.isNullOrEmpty(tableName)) {
            return "";
        }
        tableName = tableName.substring(tableName.indexOf(StrUtil.UNDERLINE) + 1);
        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName.toLowerCase())) + "Do";
    }

    /**
     * 表名转换
     * @param tableName
     * @return
     */
    default String tableNameConvert(String tableName) {
        if (Strings.isNullOrEmpty(tableName)) {
            return "";
        }
        tableName = tableName.substring(tableName.indexOf(StrUtil.UNDERLINE) + 1);
        return StrUtil.upperFirst(StrUtil.toCamelCase(tableName.toLowerCase()));
    }

    /**
     * 自定义表字段名到实体类属性名的转换规则
     *
     * @param fieldName 表字段名称
     * @return 转换的后属性名称
     */
    default String propertyNameConvert(String fieldName) {
        if (Strings.isNullOrEmpty(fieldName)) {
            return "";
        }
        if (fieldName.contains("_")) {
            return StrUtil.toCamelCase(fieldName.toLowerCase());
        }
        return fieldName;
    }

    /**
     * 自定义Mapper.java的类名称（文件名等于类名加固定后缀）
     */
    default String mapperNameConvert(String entityName) {
        return entityName + "Mapper";
    }

    /**
     * 自定义Mapper.xml的文件名称（无需添加后缀名）
     */
    default String mapperXmlNameConvert(String entityName) {
        return entityName + "Mapper";
    }

    /**
     * 自定义Service.java的类名称（文件名等于类名加固定后缀）
     */
    default String serviceNameConvert(String entityName) {
        return entityName + "Server";
    }

    /**
     * 自定义ServiceImpl.java的类名称（文件名等于类名加固定后缀）
     */
    default String serviceImplNameConvert(String entityName) {
        return entityName + "ServerImpl";
    }

    /**
     * 自定义Controller.java的类名称（文件名等于类名加固定后缀）
     */
    default String controllerNameConvert(String entityName) {
        return entityName + "Controller";
    }

}
