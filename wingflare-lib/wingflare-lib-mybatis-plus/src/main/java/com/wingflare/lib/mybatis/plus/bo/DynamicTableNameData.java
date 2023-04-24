package com.wingflare.lib.mybatis.plus.bo;

/**
 * 动态表名数据
 *
 * @author shaoyuyao
 * @date 2023/3/10 17:54
 */
public class DynamicTableNameData {
    /**
     * 替换前的表名
     */
    private String beforeTableName;

    /**
     * 替换后的表名
     */
    private String afterTableName;

    public DynamicTableNameData() {
    }

    public DynamicTableNameData(String beforeTableName, String afterTableName) {
        this.beforeTableName = beforeTableName;
        this.afterTableName = afterTableName;
    }

    public static DynamicTableNameData of(String beforeTableName, String afterTableName) {
        return new DynamicTableNameData(beforeTableName, afterTableName);
    }

    public String getBeforeTableName() {
        return beforeTableName;
    }

    public void setBeforeTableName(String beforeTableName) {
        this.beforeTableName = beforeTableName;
    }

    public String getAfterTableName() {
        return afterTableName;
    }

    public void setAfterTableName(String afterTableName) {
        this.afterTableName = afterTableName;
    }
}
