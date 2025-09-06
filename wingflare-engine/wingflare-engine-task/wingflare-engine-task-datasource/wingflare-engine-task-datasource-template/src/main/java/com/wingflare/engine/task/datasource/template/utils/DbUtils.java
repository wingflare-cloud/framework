package com.wingflare.engine.task.datasource.template.utils;


import com.wingflare.engine.task.datasource.template.enums.DbTypeEnum;
import com.wingflare.lib.container.Container;
import org.springframework.core.env.Environment;

/**
 * 数据库工具
 *
 * @author: 疯狂的狮子Li
 * @date : 2024-03-27 14:17
 */
public class DbUtils {

    public static DbTypeEnum getDbType() {
        Environment environment = Container.get(Environment.class);
        String url = environment.getProperty("spring.datasource.url");
        return DbTypeEnum.modeOf(url);
    }

    public static boolean isOracle() {
        return DbTypeEnum.ORACLE.equals(getDbType());
    }

    public static boolean notOracle() {
        return !DbTypeEnum.ORACLE.equals(getDbType());
    }
}
