package com.wingflare.facade.module.base.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigInteger;
import java.util.Date;

/**
 * 系统字典Dto
 * 
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
public class DictDTO
{
	
    private BigInteger dictId;
	
    private Integer state;
	
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
     * 排序值
     */
    private Integer sort;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
	
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
	
    private String createUser;
	
    private BigInteger createUserId;
	
    private String updateUser;
	
    private BigInteger updateUserId;
	
    private Integer version;
	
    public DictDTO setDictId(BigInteger dictId)
    {
        this.dictId = dictId;
        return this;
    }

    public BigInteger getDictId()
    {
        return dictId;
    }
	
    public DictDTO setState(Integer state)
    {
        this.state = state;
        return this;
    }

    public Integer getState()
    {
        return state;
    }
	
    public DictDTO setDictType(String dictType)
    {
        this.dictType = dictType;
        return this;
    }

    public String getDictType()
    {
        return dictType;
    }
	
    public DictDTO setDictCode(String dictCode)
    {
        this.dictCode = dictCode;
        return this;
    }

    public String getDictCode()
    {
        return dictCode;
    }
	
    public DictDTO setDictName(String dictName)
    {
        this.dictName = dictName;
        return this;
    }

    public String getDictName()
    {
        return dictName;
    }
	
    public DictDTO setDictValue(String dictValue)
    {
        this.dictValue = dictValue;
        return this;
    }

    public String getDictValue()
    {
        return dictValue;
    }
	
    public DictDTO setDictText(String dictText)
    {
        this.dictText = dictText;
        return this;
    }

    public String getDictText()
    {
        return dictText;
    }
	
    public DictDTO setSort(Integer sort)
    {
        this.sort = sort;
        return this;
    }

    public Integer getSort()
    {
        return sort;
    }
	
    public DictDTO setCreatedTime(Date createdTime)
    {
        this.createdTime = createdTime;
        return this;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }
	
    public DictDTO setUpdatedTime(Date updatedTime)
    {
        this.updatedTime = updatedTime;
        return this;
    }

    public Date getUpdatedTime()
    {
        return updatedTime;
    }
	
    public DictDTO setCreateUser(String createUser)
    {
        this.createUser = createUser;
        return this;
    }

    public String getCreateUser()
    {
        return createUser;
    }
	
    public DictDTO setCreateUserId(BigInteger createUserId)
    {
        this.createUserId = createUserId;
        return this;
    }

    public BigInteger getCreateUserId()
    {
        return createUserId;
    }
	
    public DictDTO setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
        return this;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }
	
    public DictDTO setUpdateUserId(BigInteger updateUserId)
    {
        this.updateUserId = updateUserId;
        return this;
    }

    public BigInteger getUpdateUserId()
    {
        return updateUserId;
    }
	
    public DictDTO setVersion(Integer version)
    {
        this.version = version;
        return this;
    }

    public Integer getVersion()
    {
        return version;
    }
	
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
            .append("state", getState())
            .append("dictType", getDictType())
            .append("dictCode", getDictCode())
            .append("dictName", getDictName())
            .append("dictValue", getDictValue())
            .append("dictText", getDictText())
            .append("sort", getSort())
            .append("createdTime", getCreatedTime())
            .append("updatedTime", getUpdatedTime())
            .append("createUser", getCreateUser())
            .append("createUserId", getCreateUserId())
            .append("updateUser", getUpdateUser())
            .append("updateUserId", getUpdateUserId())
            .append("version", getVersion())
            .toString();
    }
	
}
