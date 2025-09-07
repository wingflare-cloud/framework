package com.wingflare.engine.task.server.common.enums;


import com.wingflare.engine.task.datasource.template.enums.DbTypeEnum;

/**
 * 年、月、日
 *
 * @author zhengweilin
 * @version 1.0.0
 * @date 2024/03/26
 */
public enum DashboardLineEnum {
    DAY("DAY"),
    WEEK("WEEK"),
    MONTH("MONTH"),
    YEAR("YEAR"),
    ;

    private final String unit;

    DashboardLineEnum(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public static DashboardLineEnum modeOf(String mode) {
        for (DashboardLineEnum value : DashboardLineEnum.values()) {
            if (value.getUnit().equals(mode)) {
                return value;
            }
        }

        return DashboardLineEnum.WEEK;
    }

    public static String dateFormat(String unit, DbTypeEnum dbType) {
        DashboardLineEnum mode = modeOf(unit);

        if (dbType.equals(DbTypeEnum.MYSQL)) {
            switch (mode) {
                case YEAR:
                    return "%Y-%m";
                case DAY:
                    return "%H";
                default:
                    return "%Y-%m-%d";
            }
        } else if (dbType.equals(DbTypeEnum.MARIADB)) {
            switch (mode) {
                case YEAR:
                    return "%Y-%m";
                case DAY:
                    return "%H";
                default:
                    return "%Y-%m-%d";
            }
        } else if (dbType.equals(DbTypeEnum.SQLSERVER)) {
            switch (mode) {
                case YEAR:
                    return "yyyy-MM";
                case DAY:
                    return "HH";
                default:
                    return "yyyy-MM-dd";
            }
        } else { // Oracle, Postgres, DM, Kingbase
            switch (mode) {
                case YEAR:
                    return "yyyy-MM";
                case DAY:
                    return "HH24";
                default:
                    return "yyyy-MM-dd";
            }
        }
    }

}
