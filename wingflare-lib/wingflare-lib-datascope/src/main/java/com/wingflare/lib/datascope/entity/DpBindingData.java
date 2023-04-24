package com.wingflare.lib.datascope.entity;


import com.wingflare.lib.datascope.annotation.DpBinding;

/**
 * 数据权限标识和Mapper全限定方法名绑定关系
 *
 * @author shaoyuyao
 * @date 2022/8/27 16:59
 */
public class DpBindingData {
    /**
     * 数据权限标识
     */
    private String conditionName;

    /**
     * Mapper全限定方法名
     */
    private String mappedStatementId;

    public DpBindingData(String conditionName, String mappedStatementId) {
        this.conditionName = conditionName;
        this.mappedStatementId = mappedStatementId;
    }

    public static DpBindingData of(DpBinding dpBinding) {
        return new DpBindingData(dpBinding.conditionName(), dpBinding.mappedStatementId());
    }

    public static DpBindingData of(String conditionName, String mappedStatementId) {
        return new DpBindingData(conditionName, mappedStatementId);
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getMappedStatementId() {
        return mappedStatementId;
    }

    public void setMappedStatementId(String mappedStatementId) {
        this.mappedStatementId = mappedStatementId;
    }
}
