package com.wingflare.engine.task.datasource.template.enums;


import com.wingflare.engine.task.datasource.template.exception.TaskDatasourceException;
import com.baomidou.mybatisplus.annotation.DbType;

import java.util.Arrays;
import java.util.List;

/**
 * DB数据库类型
 *
 * @author opensnail
 * @date 2023-06-04
 * @since 2.1.0
 */
public enum DbTypeEnum {
    MYSQL("mysql", "MySql database", DbType.MYSQL),
    MARIADB("mariadb", "MariaDB database", DbType.MARIADB),
    POSTGRES("postgresql", "Postgres database", DbType.POSTGRE_SQL),
    ORACLE("oracle", "Oracle database", DbType.ORACLE_12C),
    SQLSERVER("sqlserver", "SQL Server database", DbType.SQL_SERVER),
    DM("dm", "Dameng database", DbType.DM),
    KINGBASE("kingbase", "Renmin University of China Golden Warehouse", DbType.KINGBASE_ES);

    private final String db;
    private final String desc;
    @Deprecated(since = "1.2.0-beta1")
    private final DbType mpDbType;

    DbTypeEnum(String db, String desc, DbType mpDbType) {
        this.db = db;
        this.desc = desc;
        this.mpDbType = mpDbType;
    }

    public String getDb() {
        return db;
    }

    public String getDesc() {
        return desc;
    }

    public DbType getMpDbType() {
        return mpDbType;
    }

    public static DbTypeEnum modeOf(String db) {
        for (DbTypeEnum value : DbTypeEnum.values()) {
            if (db.contains(value.getDb())) {
                return value;
            }
        }

        throw new TaskDatasourceException("This database is not supported yet [{}]", db);
    }

    public static List<DbTypeEnum> all() {
        return Arrays.asList(DbTypeEnum.values());
    }
}
