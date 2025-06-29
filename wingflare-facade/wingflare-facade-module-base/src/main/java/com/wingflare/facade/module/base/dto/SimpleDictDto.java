package com.wingflare.facade.module.base.dto;


import java.math.BigInteger;

/**
 * 系统字典Dto
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class SimpleDictDto
{
	
    private BigInteger dictId;
	
	/**
     * 系统代码
     */
    private String systemCode;
	
	/**
     * 字典类型
     */
    private String dictType;
	
	/**
     * 字典代码
     */
    private String dictCode;
	
	/**
     * 字典名称
     */
    private String dictName;
	
	/**
     * 字典值
     */
    private String dictValue;
	
	/**
     * 字典文本
     */
    private String dictText;

    /**
     * 排序权重
     */
    private Integer sort;
	
    public SimpleDictDto setDictId(BigInteger dictId)
    {
        this.dictId = dictId;
        return this;
    }

    public BigInteger getDictId()
    {
        return dictId;
    }
	
    public SimpleDictDto setSystemCode(String systemCode)
    {
        this.systemCode = systemCode;
        return this;
    }

    public String getSystemCode()
    {
        return systemCode;
    }
	
    public SimpleDictDto setDictType(String dictType)
    {
        this.dictType = dictType;
        return this;
    }

    public String getDictType()
    {
        return dictType;
    }
	
    public SimpleDictDto setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
        return this;
    }

    public String getDictCode()
    {
        return dictCode;
    }
	
    public SimpleDictDto setDictName(String dictName)
    {
        this.dictName = dictName;
        return this;
    }

    public String getDictName()
    {
        return dictName;
    }
	
    public SimpleDictDto setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
        return this;
    }

    public String getDictValue()
    {
        return dictValue;
    }
	
    public SimpleDictDto setDictText(String dictText)
    {
        this.dictText = dictText;
        return this;
    }

    public String getDictText()
    {
        return dictText;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
