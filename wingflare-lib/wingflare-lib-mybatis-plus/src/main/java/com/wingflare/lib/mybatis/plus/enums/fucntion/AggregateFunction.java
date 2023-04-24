package com.wingflare.lib.mybatis.plus.enums.fucntion;

/**
 * 数据库聚合函数
 *
 * @author shaoyuyao
 * @date 2022/8/23 17:54
 */
public enum AggregateFunction implements DbFunction {
    /**
     * 返回指定列的平均值
     */
    AVG("AVG(%s)"),

    /**
     * 返回指定列中非null值的个数
     */
    COUNT("COUNT(%s)"),

    /**
     * 返回指定列的最小值
     */
    MIN("MIN(%s)"),

    /**
     * 返回指定列的最大值
     */
    MAX("MAX(%s)"),

    /**
     * 返回指定列的所有值之和
     */
    SUM("SUM(%s)"),

    /**
     * 返回由属于一组的列值连接组合而成的结果
     */
    GROUP_CONCAT("GROUP_CONCAT(%s)");

    /**
     * SQL
     */
    private final String sql;

    AggregateFunction(String sql) {
        this.sql = sql;
    }


    @Override
    public String getSql() {
        return this.sql;
    }
}
