package com.wingflare.lib.mybatis.plus.utils;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据表 -> 类字段映射读取工具
 *
 * @author naizui_ycx
 * @date 2025/03/11
 */
public class ColumnCacheReaderUtil {

    /**
     * 读取类及其父类的字段，生成 ColumnCache 映射
     */
    public static Map<String, ColumnCache> readColumnCaches(Class<?> clazz) {
        Map<String, ColumnCache> result = new HashMap<>();
        Class<?> currentClass = clazz;

        while (currentClass != null && currentClass != Object.class) {
            processFields(currentClass, clazz, result);
            currentClass = currentClass.getSuperclass();
        }

        return result;
    }

    /**
     * 处理单个类的字段
     */
    private static void processFields(Class<?> currentClass, Class<?> clazz, Map<String, ColumnCache> result) {
        Field[] fields = currentClass.getDeclaredFields();
        for (Field field : fields) {
            if (hasGetterAndSetter(field, clazz)) {
                String column = determineColumnName(field);
                if (column == null) continue; // 跳过不映射到数据库的字段
                String columnSelect = determineColumnSelect(field);
                String mapping = field.getName() + ":" + column;
                ColumnCache cache = new ColumnCache(column, columnSelect, mapping);
                result.put(LambdaUtils.formatKey(column.replaceAll("_+", "")), cache);
            }
        }
    }

    /**
     * 检查字段是否有对应的 getter 和 setter
     */
    private static boolean hasGetterAndSetter(Field field, Class<?> clazz) {
        String fieldName = field.getName();
        String capitalized = capitalize(fieldName);
        String getter = "get" + capitalized;
        String setter = "set" + capitalized;
        Class<?> fieldType = field.getType();

        if (fieldType == boolean.class || fieldType == Boolean.class) {
            getter = "is" + capitalized;
        }

        try {
            Method getMethod = clazz.getMethod(getter);
            Method setMethod = clazz.getMethod(setter, fieldType);
            return getMethod != null && setMethod != null;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * 确定数据库列名
     */
    private static String determineColumnName(Field field) {
        TableField tableField = field.getAnnotation(TableField.class);

        if (tableField != null) {
            if (!tableField.exist()) return null; // 不映射到数据库
            if (!tableField.value().isEmpty()) return tableField.value();
        }

        return camelToUnderline(field.getName());
    }

    /**
     * 确定 SELECT 语句中的列表达式
     */
    private static String determineColumnSelect(Field field) {
        TableField tableField = field.getAnnotation(TableField.class);
        if (tableField != null && tableField.exist() && !tableField.value().isEmpty() && tableField.select()) {
            return tableField.value();
        }

        return null;
    }

    /**
     * 驼峰转下划线命名
     */
    private static String camelToUnderline(String camel) {
        return camel.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    /**
     * 首字母大写
     */
    private static String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}